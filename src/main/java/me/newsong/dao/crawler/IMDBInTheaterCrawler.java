package me.newsong.dao.crawler;

import me.newsong.domain.entity.ActorDO;
import me.newsong.domain.entity.DirectorDO;
import me.newsong.domain.entity.MovieTagDO;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.util.BaseSpringTester;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SinjinSong on 2017/6/5.
 */
@Component
public class IMDBInTheaterCrawler extends BaseSpringTester {
    public static final String URL = "http://www.imdb.com/movies-in-theaters/?ref_=nv_mv_inth_1";

    public List<RemoteMovieInfoDO> findInTheaterMovies() throws IOException {
        Document doc = Jsoup.connect(URL)
                .timeout(10000)
                .get();
        List<RemoteMovieInfoDO> result = new ArrayList<>();
        Elements odds = doc.getElementsByClass("list_item odd");
        Elements evens = doc.getElementsByClass("list_item even");
        for (Element odd : odds) {
            result.add(parse(odd));
        }
        
        for (Element even : evens) {
            result.add(parse(even));
        }
        return result;
    }
    
    private RemoteMovieInfoDO parse(Element div) {
        Element a = div.getElementsByClass("overview-top").get(0).getElementsByTag("a").get(0);
        String href = a.attr("href");
        String[] split = href.split("/");
        String movieId = split[2];
//        System.out.println(movieId);
        String name = a.text().trim();
//        System.out.println(name);
        Elements time = div.getElementsByTag("time");
        
        Integer min = Integer.valueOf(time.text().substring(0,time.text().indexOf(' ')));
//        System.out.println(min);
        Elements spans = div.getElementsByClass("cert-runtime-genre").get(0).getElementsByTag("span");
        List<MovieTagDO> tags = new ArrayList<>();
        
        for (Element span : spans) {
            if(!span.text().trim().equals("|")){
                tags.add(new MovieTagDO(span.text().trim()));
            }
        }
//        System.out.println(tags);

        String summary = div.getElementsByClass("outline").get(0).text().trim();
//        System.out.println(summary);
        List<DirectorDO> directors = new ArrayList<>();
        
        Elements blocks = div.getElementsByClass("txt-block");
        Element directorsEle = blocks.get(0);
        Elements dirs = directorsEle.getElementsByTag("a");
        Elements stars = blocks.get(1).getElementsByTag("a");
        for (Element dir : dirs) {
            directors.add(new DirectorDO(dir.text().trim()));
        }
//        System.out.println(directors);
        
        List<ActorDO> actors = new ArrayList<>();
        for (Element star : stars) {
            actors.add(new ActorDO(star.text().trim()));
        }
//        System.out.println(actors);


        Elements imgs = div.getElementsByTag("img");
        String posterUrl = imgs.get(0).attr("src");

        RemoteMovieInfoDO movie = new RemoteMovieInfoDO();
        movie.setMovieId(movieId);
        movie.setName(name);
        movie.setDuration(min);
        movie.setTags(tags);
        movie.setSummary(summary);
        movie.setDirectors(directors);
        movie.setActors(actors);
        movie.setPosterUrl(posterUrl);
        return movie;
    }

    @Test
    public void test() {
        try {
            findInTheaterMovies().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}