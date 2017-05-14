package me.newsong.util.movie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.*;
public class MovieReviewRepositoryImpl  {
    private Map<String, List<Index>> movieIndexMap;
    private Map<String, List<Index>> userIndexMap;
    private ObjectMapper mapper;
    private FileInputStream in;
    private int BLOCK_SIZE_UNIT;
    private String MOVIE_DIR_PREFIX;
    private String MOVIE_DIR_SUFFIX;

    public MovieReviewRepositoryImpl() {
        mapper = new ObjectMapper();
        init();
    }

    public void init() {
        try {
            movieIndexMap = Collections.unmodifiableMap(mapper.readValue(new FileReader("E:/cache/movieIdIndex.json"),
                    new TypeReference<Map<String, List<Index>>>() {
                    }));
            userIndexMap = Collections.unmodifiableMap(mapper.readValue(new FileReader("E:/cache/userIdIndex.json"),
                    new TypeReference<Map<String, List<Index>>>() {
                    }));
            BLOCK_SIZE_UNIT = 1000;
            MOVIE_DIR_PREFIX = "E:/movies/movies";
            MOVIE_DIR_SUFFIX = ".json";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<MovieReview> findBy(List<Index> indexs) {
        List<MovieReview> reviews = new ArrayList<>();
        if (indexs == null) {
            return reviews;
        }
        MovieReview review = null;
        int lastPageIndex = -1;
        int movieBlockSize = 0;
        MappedByteBuffer buf = null;
        try {
            synchronized (this) {
                for (Index index : indexs) {
                    // 当前页面的一个电影评分记录的大小
                    movieBlockSize = BLOCK_SIZE_UNIT * (index.getPageIndex() + 1);
                    if (lastPageIndex != index.getPageIndex() || lastPageIndex == -1) {
                        in = new FileInputStream(MOVIE_DIR_PREFIX + "_" + index.getPageIndex() + MOVIE_DIR_SUFFIX);
                        buf = in.getChannel().map(MapMode.READ_ONLY,
                                index.getMovieIndex() * (long) movieBlockSize,
                                movieBlockSize);
                    } else if (lastPageIndex == index.getPageIndex()) {
                        buf = in.getChannel().map(MapMode.READ_ONLY, index.getMovieIndex() * (long) movieBlockSize,
                                movieBlockSize);
                    }
                    byte[] bytes = new byte[movieBlockSize];
                    buf.get(bytes);
                    review = mapper.readValue(bytes, MovieReview.class);
                    // Unix时间戳转为Java中的时间需要*1000
                    review.setTime(new Date(review.getTime().getTime() * 1000));
                    // System.out.println(review);
                    reviews.add(review);
                    lastPageIndex = index.getPageIndex();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public List<MovieReview> findByMovieId(String id) {
        return findBy(movieIndexMap.get(id));
    }

    public List<MovieReview> findByUserId(String id) {
        return findBy(userIndexMap.get(id));
    }

    public List<String> findAllMovieIds() {
        return new ArrayList<>(movieIndexMap.keySet());
    }

    public List<String> findAllUserIds() {
        return new ArrayList<>(userIndexMap.keySet());
    }

}
