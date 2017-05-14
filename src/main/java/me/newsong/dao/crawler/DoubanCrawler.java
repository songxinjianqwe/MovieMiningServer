package me.newsong.dao.crawler;

import com.csvreader.CsvReader;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.newsong.dao.*;
import me.newsong.domain.entity.*;
import me.newsong.util.BaseSpringTester;
import me.newsong.util.DateTimeUtil;
import me.newsong.util.PythonUtil;
import me.newsong.util.movie.MovieTag;
import me.newsong.util.movie.RemoteMovieInfoDTO;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SinjinSong on 2017/5/12.
 */
public class DoubanCrawler extends BaseSpringTester {

    @Autowired
    private RemoteMovieInfoDOMapper remoteMovieInfoDOMapper;
    private PythonUtil pythonUtil = PythonUtil.getInstance();
    private static String DOUBAN_URL = "https://movie.douban.com/tag/2016";
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; LCTE; rv:11.0) like Gecko";
    private static ObjectMapper mapper = new ObjectMapper();
    private static int MIN_REVIRE_TIME = 5000;
    private static Map<String, String> COOKIE = new HashMap<>();
    private static int PAUSE = 1000;
    private static String IMDB_MOVIE_REVIEW_URL = "http://www.imdb.com/title/%s/reviews?ref_=tt_urv";

    private static String NUMBER_REG = "([0-9]*)";
    private static String TWO_NUMBER_REG = "([0-9]*)([0-9]*)";

    private static String USER_URL = "http://www.imdb.com/user/%s/";


    @Autowired
    private DirectorDOMapper directorDOMapper;
    @Autowired
    private WriterDOMapper writerDOMapper;
    @Autowired
    private ActorDOMapper actorDOMapper;
    @Autowired
    private CountryDOMapper countryDOMapper;
    @Autowired
    private LanguageDOMapper languageDOMapper;
    @Autowired
    private MovieTagDOMapper movieTagDOMapper;
    @Autowired
    private MovieReviewDOMapper movieReviewDOMapper;
    @Autowired
    private MovieActorMapper movieActorMapper;
    @Autowired
    private MovieCountryMapper movieCountryMapper;
    @Autowired
    private MovieDirectorMapper movieDirectorMapper;
    @Autowired
    private MovieLanguageMapper movieLanguageMapper;
    @Autowired
    private MovieTagMapper movieTagMapper;
    @Autowired
    private MovieWriterMapper movieWriterMapper;
    @Autowired
    private UserDOMapper userDOMapper;

    private static Map<String, Integer> months = new HashMap<>();
    static {
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);
    }

    @BeforeClass
    public static void init() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        COOKIE.put("ll", "118159");
        COOKIE.put("gr_user_id", "a6ea507c-dc79-4214-ac1e-0c5b9246408e");
        COOKIE.put("ap", "1");
        COOKIE.put("__yadk_uid", "jlwWZzSCtzNDgXqqwJCn2xWm6yxf0hhw");
        COOKIE.put("viewed", "24250054_26593466_10470970_25866350_4199483_1080511_3288908");
        COOKIE.put("ps", "y");
        COOKIE.put("_pk_ref.100001.4cf6", "%5B%22%22%2C%22%22%2C1494650634%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DOmmNgRKm_H4wbKs1z5hkXQaTY1-PDjHjyUe8kXnJ5jESsyOLAFP38nTEQvjGrKjMmOwNOcj-Ddy5QioPxvuS7F8BAH3wcX74uFB-ahzZ8kS%26wd%3D%26eqid%3D90651dd6000c77350000000358fad91c%22%5D");
        COOKIE.put("ue", "songxinjianzx@163.com");
        COOKIE.put("dbcl2", "158407811:CpSF262azOs");
        COOKIE.put("ck", "KmbD");
        COOKIE.put("__utmt", "1");
        COOKIE.put("_pk_id.100001.4cf6", "318f4af4591e4864.1489738240.44.1494654109.1494607104.");
        COOKIE.put("_pk_ses.100001.4cf6", "*");
        COOKIE.put("__utma", "223695111.1583004744.1489738240.1494607101.1494650634.44");
        COOKIE.put("__utmb", "223695111.0.10.1494650634");
        COOKIE.put("__utmc", "223695111");
        COOKIE.put("__utmz", "30149280.1493910451.48.15.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
        COOKIE.put("__utmv", "30149280.15840");
        COOKIE.put("bid", "hxJ0ucxP-Qw");
    }


    public static Map<String, String> generateCookie() {
        return COOKIE;
    }

    /**
     * 爬取2000部电影，AJAX获取电影列表，得到URLs
     */
    @Test
    public void crawl() throws InterruptedException {
        System.out.println("start crawling...");
        int movieCount = 2460;
        int successMovieCount = 594;
        try {
            for (int pageStart = 2460; ; pageStart += 20) {
                Document doc = Jsoup.connect(DOUBAN_URL)
                        .userAgent(USER_AGENT)
                        .data("start", String.valueOf(pageStart))
                        .data("type", "")
                        .timeout(10000)
                        .cookies(generateCookie())
                        .get();
                Elements pl2 = doc.getElementsByClass("pl2");
                for (Element e : pl2) {
                    Elements a = e.getElementsByTag("a");
                    if (a.attr("href").contains("subject")) {
                        movieCount++;
                        System.out.printf("正在爬取第%d部电影", movieCount);
                        Thread.sleep(PAUSE);
                        if (crawlDoubanMoviePage(a.attr("href"))) {
                            successMovieCount++;
                            System.out.printf("第%d部有效电影GET!\n", successMovieCount);
                            if (successMovieCount >= 2000) {
                                System.out.println("爬取完毕");
                                return;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取单部电影信息
     *
     * @param movieURL
     * @return
     */
    public boolean crawlDoubanMoviePage(String movieURL) throws IOException {
        System.out.println(movieURL);
        RemoteMovieInfoDO remoteMovieInfo = new RemoteMovieInfoDO();
        Document doc = Jsoup.connect(movieURL).timeout(10000).cookies(generateCookie()).get();
        //爬imdb链接
        Elements infoDiv = doc.getElementById("info").getElementsByTag("a");
        String imdbURL = null;
        for (Element ele : infoDiv) {
            imdbURL = ele.attr("href");
            if (imdbURL.contains("imdb")) {
                break;
            }
        }
        if (imdbURL == null || !imdbURL.contains("imdb")) {
            //                System.out.println("无IMDBURL");
            return false;
        }

        //爬豆瓣评分
        String score = doc.getElementsByClass("ll rating_num").text();
        if (StringUtils.isEmpty(score)) {
            //                System.out.println("无豆瓣评分");
            return false;
        }

        //爬豆瓣评论人数
        Elements reviewDiv = doc.getElementById("interest_sectl").getElementsByTag("a");
        String review = null;
        for (Element ele : reviewDiv) {
            review = ele.attr("href");
            if (review.equals("collections")) {
                review = ele.getElementsByTag("span").text();
                if (StringUtils.isNotEmpty(review)) {
                    break;
                }
            }
        }
        if (StringUtils.isEmpty(review) || Integer.parseInt(review) < MIN_REVIRE_TIME) {
            System.out.printf("评论人数为%s ,不符要求\n", review);
            return false;
        }

        //到这里，这部电影是我们想要的
        String[] splitedURL = imdbURL.split("/");
        String movieId = null;
        for (String slice : splitedURL) {
            if (slice.contains("tt")) {
                movieId = slice;
            }
        }
        if (StringUtils.isEmpty(movieId)) {
            throw new RuntimeException(imdbURL);
        }
        remoteMovieInfo.setMovieId(movieId);
        remoteMovieInfo.setDoubanReviewTime(Integer.valueOf(review));
        remoteMovieInfo.setDoubanScore(Double.valueOf(score));
        System.out.println(remoteMovieInfo);
        if (remoteMovieInfoDOMapper.findByMovieId(movieId) != null) {
            return false;
        }
        remoteMovieInfoDOMapper.insert(remoteMovieInfo);
        return true;
    }

    @Test
    public void test() {
        System.out.println(generateCookie());
        //        crawlDoubanMoviePage("https://movie.douban.com/subject/26831711/?tag=%E7%83%AD%E9%97%A8&from=gaia_video");
        //        crawlDoubanMoviePage("https://movie.douban.com/subject/26874872/?tag=%E7%83%AD%E9%97%A8&from=gaia");
        //        crawlDoubanMoviePage("https://movie.douban.com/subject/1303004/");
//        crawlDoubanMoviePage("https://movie.douban.com/subject/26844922/?tag=%E7%83%AD%E9%97%A8&from=gaia");
    }

    @Test
    public void testEmptyObj() {
        RemoteMovieInfoDTO remoteMovieInfoDTO = pythonUtil.call("test", null, RemoteMovieInfoDTO.class);
        System.out.println(remoteMovieInfoDTO);
    }

    @Test
    public void crawlIMDB() {
        for (RemoteMovieInfoDO movie : remoteMovieInfoDOMapper.findAllDisplayMovies()) {

            RemoteMovieInfoDTO remoteMovieInfoDTO = pythonUtil.call("getMovieInfo", Arrays.asList(movie.getMovieId()), RemoteMovieInfoDTO.class);
            if (remoteMovieInfoDTO == null || remoteMovieInfoDTO.getPosterURL() == null) {
                remoteMovieInfoDOMapper.deleteByPrimaryKey(movie.getId());
                continue;
            }
            movie.setPosterUrl(remoteMovieInfoDTO.getPosterURL());
            movie.setName(remoteMovieInfoDTO.getName());
            movie.setReleaseTime(DateTimeUtil.toLocalDate(remoteMovieInfoDTO.getReleaseTime().getTime()));
            movie.setSummary(remoteMovieInfoDTO.getSummary());
            movie.setImdbScore(remoteMovieInfoDTO.getImdbScore());
            movie.setImdbReviewTime(remoteMovieInfoDTO.getImdbReviewTime());
            movie.setDuration(remoteMovieInfoDTO.getDuration());
            movie.setGross(remoteMovieInfoDTO.getGross());


            String[] directors = remoteMovieInfoDTO.getDirectors();
            List<DirectorDO> directorDOS = new ArrayList<>();
            if (directors != null) {
                for (String director : directors) {
                    DirectorDO directorDO = directorDOMapper.findByName(director);
                    if (directorDO == null) {
                        directorDO = new DirectorDO(null, director);
                        directorDOMapper.insert(directorDO);
                    }
                    directorDOS.add(directorDO);
                }
            }


            String[] actors = remoteMovieInfoDTO.getActors();
            List<ActorDO> actorDOS = new ArrayList<>();
            if (actors != null) {
                for (String actor : actors) {
                    ActorDO actorDO = actorDOMapper.findByName(actor);
                    if (actorDO == null) {
                        actorDO = new ActorDO(null, actor);
                        actorDOMapper.insert(actorDO);
                    }
                    actorDOS.add(actorDO);
                }
            }


            String[] countries = remoteMovieInfoDTO.getCountries();
            List<CountryDO> countryDOS = new ArrayList<>();
            if (countries != null) {
                for (String country : countries) {
                    CountryDO countryDO = countryDOMapper.findByName(country);
                    if (countryDO == null) {
                        countryDO = new CountryDO(null, country);
                        countryDOMapper.insert(countryDO);
                    }
                    countryDOS.add(countryDO);
                }
            }


            String[] languages = remoteMovieInfoDTO.getLanguages();
            List<LanguageDO> languageDOS = new ArrayList<>();
            if (languages != null) {
                for (String language : languages) {
                    LanguageDO languageDO = languageDOMapper.findByName(language);
                    if (languageDO == null) {
                        languageDO = new LanguageDO(null, language);
                        languageDOMapper.insert(languageDO);
                    }
                    languageDOS.add(languageDO);
                }
            }


            String[] writers = remoteMovieInfoDTO.getWriters();
            List<WriterDO> writerDOS = new ArrayList<>();
            if (writers != null) {
                for (String writer : writers) {
                    WriterDO writerDO = writerDOMapper.findByName(writer);
                    if (writerDO == null) {
                        writerDO = new WriterDO(null, writer);
                        writerDOMapper.insert(writerDO);
                    }
                    writerDOS.add(writerDO);
                }
            }


            MovieTag[] tags = remoteMovieInfoDTO.getTags();
            List<MovieTagDO> movieTagDOS = new ArrayList<>();
            if (tags != null) {
                for (MovieTag tag : tags) {
                    MovieTagDO movieTagDO = movieTagDOMapper.findByName(tag.toString());
                    if (movieTagDO == null) {
                        movieTagDO = new MovieTagDO(null, tag.toString());
                        movieTagDOMapper.insert(movieTagDO);
                    }
                    movieTagDOS.add(movieTagDO);
                }
            }

            remoteMovieInfoDOMapper.updateByPrimaryKeySelective(movie);
            System.out.println(movie);
            for (DirectorDO directorDO : directorDOS) {
                if (movieDirectorMapper.findById(movie.getId(), directorDO.getId()) == null) {
                    MovieDirectorKey movieDirectorKey = new MovieDirectorKey(movie.getId(), directorDO.getId());
                    movieDirectorMapper.insert(movieDirectorKey);
                }
            }

            for (ActorDO actorDO : actorDOS) {
                if (movieActorMapper.findById(movie.getId(), actorDO.getId()) == null) {
                    MovieActorKey movieActorKey = new MovieActorKey(movie.getId(), actorDO.getId());
                    movieActorMapper.insert(movieActorKey);
                }
            }

            for (CountryDO countryDO : countryDOS) {
                if (movieCountryMapper.findById(movie.getId(), countryDO.getId()) == null) {
                    MovieCountryKey movieCountryKey = new MovieCountryKey(movie.getId(), countryDO.getId());
                    movieCountryMapper.insert(movieCountryKey);
                }
            }

            for (LanguageDO languageDO : languageDOS) {
                if (movieLanguageMapper.findById(movie.getId(), languageDO.getId()) == null) {
                    MovieLanguageKey movieLanguageKey = new MovieLanguageKey(movie.getId(), languageDO.getId());
                    movieLanguageMapper.insert(movieLanguageKey);
                }
            }

            for (MovieTagDO movieTagDO : movieTagDOS) {
                if (movieTagMapper.findById(movie.getId(), movieTagDO.getId()) == null) {
                    MovieTagKey movieTagKey = new MovieTagKey(movie.getId(), movieTagDO.getId());
                    movieTagMapper.insert(movieTagKey);
                }
            }

            for (WriterDO writerDO : writerDOS) {
                if (movieWriterMapper.findById(movie.getId(), writerDO.getId()) == null) {
                    MovieWriterKey movieWriterKey = new MovieWriterKey(movie.getId(), writerDO.getId());
                    movieWriterMapper.insert(movieWriterKey);
                }
            }
        }
    }

    @Test
    public void testMovieReview() {
        for (String movieId : remoteMovieInfoDOMapper.findAllDisplayMovieIds()) {
            if (movieReviewDOMapper.countByMovieId(movieId) != 20) {
                crawlMovieReview(movieId);
            }
        }
    }
    
    @Test
    public void testCrawl(){
        crawlMovieReview("tt0155753");        
    }
    
    public void crawlMovieReview(String movieId) {
        int reviewCount = 0;
        for (int start = 0; reviewCount == 0 ? true : start < (reviewCount > 10 ? 20 : 10); start += 10) {
            Document doc = null;
            try {
                doc = Jsoup.connect(String.format(IMDB_MOVIE_REVIEW_URL, movieId))
                        .timeout(10000)
                        .cookies(generateCookie())
                        .data("start", String.valueOf(start)).get();
                Elements total = doc.getElementsContainingOwnText("reviews in total");
                Pattern firstNumberPattern = Pattern.compile(NUMBER_REG);
                Matcher m = firstNumberPattern.matcher(total.text());
                if (m.find()) {
                    reviewCount = Integer.parseInt(m.group().trim());
                }
                if (reviewCount == 0) {
                    return;
                }
                int currPageCount = (start + 10) > reviewCount ? reviewCount - start : 10;
                List<MovieReviewDO> movieReviews = new ArrayList<>(currPageCount);
                for (int i = 0; i < currPageCount; ++i) {
                    MovieReviewDO movieReviewDO = new MovieReviewDO();
                    movieReviewDO.setMovieId(movieId);
                    movieReviews.add(movieReviewDO);
                }

                Elements divs = doc.getElementById("tn15content").getElementsByTag("div");
                for (int i = 0; i < currPageCount; ++i) {
                    Element div = divs.get(i);
                    Elements bs = div.getElementsByTag("b");
                    if (bs.size() > 0 && bs.get(0).text().contains("Author:") && div.getElementsByTag("script").size() == 0) {
                        System.out.println(String.format(IMDB_MOVIE_REVIEW_URL, movieId) + "   :" + start);
                        //得到需要的currPageCount个div
                        MovieReviewDO movieReviewDO = movieReviews.get(i);


                        //解析helpfulTimes和viewTimes
                        int dataIndex = 0;
                        Element helpful = div.getElementsByTag("small").get(0);
                        if (helpful.text().contains("found the following review useful:")) {
                            dataIndex = 1;
                            Pattern pt = Pattern.compile(TWO_NUMBER_REG);
                            Matcher matcher = pt.matcher(helpful.text());
                            while (matcher.find()) {
                                String data = matcher.group().trim();
                                if (StringUtils.isNotEmpty(data)) {
                                    if (movieReviewDO.getHelpfulTimes() == null) {
                                        movieReviewDO.setHelpfulTimes(Integer.parseInt(data));
                                    } else {
                                        movieReviewDO.setViewTimes(Integer.parseInt(data));
                                    }
                                }
                            }
                        } else {
                            dataIndex = 0;
                        }


                        Element country = div.getElementsByTag("small").get(dataIndex);
                        if (country.text().contains("from")) {
                            dataIndex++;
                        }

                        Element date = div.getElementsByTag("small").get(dataIndex);
                        movieReviewDO.setTime(DateTimeUtil.toLocalDateTime(date.text()));

                        //解析summary
                        Elements h2s = div.getElementsByTag("h2");
                        movieReviewDO.setSummary(h2s.get(0).text());


                        //解析score
                        Elements imgs = div.getElementsByTag("img");
                        Matcher alt = firstNumberPattern.matcher(imgs.attr("alt"));
                        if (alt.find()) {
                            String score = alt.group().trim();
                            if (StringUtils.isNotEmpty(score)) {
                                movieReviewDO.setScore(Integer.valueOf(score));
                            }
                        }
                        if (movieReviewDO.getScore() == null) {
                            movieReviewDO.setScore(10);
                        }

                        //解析用户id
                        Elements a = div.getElementsByTag("a");
                        String[] href = a.attr("href").split("/");
                        movieReviewDO.setUserId(href[2]);


                        //解析content
                        Element sibling = div.nextElementSibling();
                        movieReviewDO.setContent(sibling.text().trim());

                        //收尾
                        if (movieReviewDO.getHelpfulTimes() == null) {
                            movieReviewDO.setHelpfulTimes(0);
                        }
                        if (movieReviewDO.getViewTimes() == null) {
                            movieReviewDO.setViewTimes(0);
                        }

                        movieReviewDO.setDisplay(Boolean.TRUE);
                        System.out.println(movieReviewDO);
                        if (userDOMapper.findByUserId(href[2]) == null) {
                            UserDO userDO = new UserDO();
                            userDO.setUserId(href[2]);
                            userDOMapper.insert(userDO);
                        }
                        movieReviewDOMapper.insert(movieReviewDO);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void updateMovies() {
        int index = 0;
        CsvReader csvReader = null;
        try {
            csvReader = new CsvReader("E:/movie/links.csv", ',', Charset.forName("UTF-8"));
            //读取表头
            csvReader.readHeaders();
            //逐条读取记录，直至读完
            while (csvReader.readRecord()) {
                System.out.printf("第%d部电影\n", index);
                index++;
                //按列名读取这条记录的值
                RemoteMovieInfoDO movie = new RemoteMovieInfoDO();
                movie.setMovieId("tt" + csvReader.get("imdbId"));
                movie.setMovieRecommendId(Long.valueOf(csvReader.get("movieId")));

                if (remoteMovieInfoDOMapper.findByMovieId(movie.getMovieId()) != null) {
                    System.out.println("已爬取");
                    continue;
                }

                RemoteMovieInfoDTO remoteMovieInfoDTO = pythonUtil.call("getMovieInfo", Arrays.asList(movie.getMovieId()), RemoteMovieInfoDTO.class);
                if (remoteMovieInfoDTO == null || remoteMovieInfoDTO.getPosterURL() == null) {
                    continue;
                }


                movie.setPosterUrl(remoteMovieInfoDTO.getPosterURL());
                movie.setName(remoteMovieInfoDTO.getName());
                movie.setReleaseTime(DateTimeUtil.toLocalDate(remoteMovieInfoDTO.getReleaseTime().getTime()));
                movie.setSummary(remoteMovieInfoDTO.getSummary());
                movie.setImdbScore(remoteMovieInfoDTO.getImdbScore());
                movie.setImdbReviewTime(remoteMovieInfoDTO.getImdbReviewTime());
                movie.setDuration(remoteMovieInfoDTO.getDuration());
                movie.setGross(remoteMovieInfoDTO.getGross());
                movie.setDisplay(Boolean.TRUE);


                String[] directors = remoteMovieInfoDTO.getDirectors();
                List<DirectorDO> directorDOS = new ArrayList<>();

                if (directors != null) {
                    for (String director : directors) {
                        DirectorDO directorDO = directorDOMapper.findByName(director);
                        if (directorDO == null) {
                            directorDO = new DirectorDO(null, director);
                            directorDOMapper.insert(directorDO);
                        }
                        directorDOS.add(directorDO);
                    }
                }


                String[] actors = remoteMovieInfoDTO.getActors();
                List<ActorDO> actorDOS = new ArrayList<>();
                if (actors != null) {
                    for (String actor : actors) {
                        ActorDO actorDO = actorDOMapper.findByName(actor);
                        if (actorDO == null) {
                            actorDO = new ActorDO(null, actor);
                            actorDOMapper.insert(actorDO);
                        }
                        actorDOS.add(actorDO);
                    }
                }


                String[] countries = remoteMovieInfoDTO.getCountries();
                List<CountryDO> countryDOS = new ArrayList<>();
                if (countries != null) {
                    for (String country : countries) {
                        CountryDO countryDO = countryDOMapper.findByName(country);
                        if (countryDO == null) {
                            countryDO = new CountryDO(null, country);
                            countryDOMapper.insert(countryDO);
                        }
                        countryDOS.add(countryDO);
                    }
                }


                String[] languages = remoteMovieInfoDTO.getLanguages();
                List<LanguageDO> languageDOS = new ArrayList<>();
                if (languages != null) {
                    for (String language : languages) {
                        LanguageDO languageDO = languageDOMapper.findByName(language);
                        if (languageDO == null) {
                            languageDO = new LanguageDO(null, language);
                            languageDOMapper.insert(languageDO);
                        }
                        languageDOS.add(languageDO);
                    }
                }


                String[] writers = remoteMovieInfoDTO.getWriters();
                List<WriterDO> writerDOS = new ArrayList<>();
                if (writers != null) {
                    for (String writer : writers) {
                        WriterDO writerDO = writerDOMapper.findByName(writer);
                        if (writerDO == null) {
                            writerDO = new WriterDO(null, writer);
                            writerDOMapper.insert(writerDO);
                        }
                        writerDOS.add(writerDO);
                    }
                }


                MovieTag[] tags = remoteMovieInfoDTO.getTags();
                List<MovieTagDO> movieTagDOS = new ArrayList<>();
                if (tags != null) {
                    for (MovieTag tag : tags) {
                        MovieTagDO movieTagDO = movieTagDOMapper.findByName(tag.toString());
                        if (movieTagDO == null) {
                            movieTagDO = new MovieTagDO(null, tag.toString());
                            movieTagDOMapper.insert(movieTagDO);
                        }
                        movieTagDOS.add(movieTagDO);
                    }
                }


                remoteMovieInfoDOMapper.insert(movie);
                System.out.println(movie);

                for (DirectorDO directorDO : directorDOS) {
                    if (movieDirectorMapper.findById(movie.getId(), directorDO.getId()) == null) {
                        MovieDirectorKey movieDirectorKey = new MovieDirectorKey(movie.getId(), directorDO.getId());
                        movieDirectorMapper.insert(movieDirectorKey);
                    }
                }

                for (ActorDO actorDO : actorDOS) {
                    if (movieActorMapper.findById(movie.getId(), actorDO.getId()) == null) {
                        MovieActorKey movieActorKey = new MovieActorKey(movie.getId(), actorDO.getId());
                        movieActorMapper.insert(movieActorKey);
                    }
                }

                for (CountryDO countryDO : countryDOS) {
                    if (movieCountryMapper.findById(movie.getId(), countryDO.getId()) == null) {
                        MovieCountryKey movieCountryKey = new MovieCountryKey(movie.getId(), countryDO.getId());
                        movieCountryMapper.insert(movieCountryKey);
                    }
                }

                for (LanguageDO languageDO : languageDOS) {
                    if (movieLanguageMapper.findById(movie.getId(), languageDO.getId()) == null) {
                        MovieLanguageKey movieLanguageKey = new MovieLanguageKey(movie.getId(), languageDO.getId());
                        movieLanguageMapper.insert(movieLanguageKey);
                    }
                }

                for (MovieTagDO movieTagDO : movieTagDOS) {
                    if (movieTagMapper.findById(movie.getId(), movieTagDO.getId()) == null) {
                        MovieTagKey movieTagKey = new MovieTagKey(movie.getId(), movieTagDO.getId());
                        movieTagMapper.insert(movieTagKey);
                    }
                }

                for (WriterDO writerDO : writerDOS) {
                    if (movieWriterMapper.findById(movie.getId(), writerDO.getId()) == null) {
                        MovieWriterKey movieWriterKey = new MovieWriterKey(movie.getId(), writerDO.getId());
                        movieWriterMapper.insert(movieWriterKey);
                    }
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addCSV() {
        CsvReader csvReader;
        try {
            csvReader = new CsvReader("E:/movie/ratings.csv", ',', Charset.forName("UTF-8"));
            //读取表头
            csvReader.readHeaders();
            //逐条读取记录，直至读完
            while (csvReader.readRecord()) {
                Long userRecommendId = Long.valueOf(csvReader.get("userId"));
                if (userDOMapper.findByUserRecommendId(userRecommendId) == null) {
                    UserDO userDO = new UserDO();
                    userDO.setUserRecommendId(userRecommendId);
                    userDO.setUserName("user" + userRecommendId);
                    userDO.setPassword("password" + userRecommendId);
                    userDO.setIsDisplay(Boolean.FALSE);
                    System.out.println(userDO);
                    userDOMapper.insert(userDO);
                }

                MovieReviewDO movieReviewDO = new MovieReviewDO();
                movieReviewDO.setUserRecommendId(userRecommendId);
                movieReviewDO.setMovieRecommendId(Long.valueOf(csvReader.get("movieId")));
                movieReviewDO.setRecommendScore(Double.valueOf(csvReader.get("rating")));
                movieReviewDO.setTime(DateTimeUtil.toLocalDateTime(Long.parseLong(csvReader.get("timestamp"))));
                movieReviewDO.setDisplay(Boolean.FALSE);
                movieReviewDOMapper.insert(movieReviewDO);
                System.out.println(movieReviewDO);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCrawlUser() {
        crawlUser(userDOMapper.findByUserId("ur3922673"));
    }

    public void crawlUser(UserDO user) {
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(USER_URL, user.getUserId()))
                    .timeout(10000)
                    .cookies(generateCookie())
                    .get();
            Element h1 = doc.getElementById("avatar-frame").nextElementSibling();
            user.setUserName(h1.text().trim());
            Elements timestamp = doc.getElementsByClass("timestamp");
            String[] split = timestamp.text().trim().split(" ");
            Integer month = null;
            int year = 0;
            for (String slice : split) {
                if (months.containsKey(slice)){
                    month = months.get(slice);
                }else if(slice.matches("^[0-9]*$")){
                    year = Integer.parseInt(slice);
                }
            }
            user.setRegisterTime(LocalDate.of(year,month,1));
            user.setIsDisplay(Boolean.TRUE);
            
            user.setUserName(user.getUserId());
            user.setPassword(user.getUserName());
            user.setAvatarUrl(doc.getElementById("avatar").attr("src"));
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

