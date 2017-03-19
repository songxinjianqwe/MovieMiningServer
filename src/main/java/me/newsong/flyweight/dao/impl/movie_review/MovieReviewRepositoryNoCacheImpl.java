package me.newsong.flyweight.dao.impl.movie_review;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.MovieReview;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by SinjinSong on 2017/3/16.
 */

@Repository("NoCacheReviews")
public class MovieReviewRepositoryNoCacheImpl implements MovieReviewRepository {
    private Map<String, List<MovieReview>> movieIdReviews;
    private Map<String, List<MovieReview>> userIdReviews;
    private String path;
    private ResourceBundle rb;

    public MovieReviewRepositoryNoCacheImpl() {
        rb = ResourceBundle.getBundle("fileSystemData");
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            path = rb.getString("dataDirForWin");
        } else {
            path = rb.getString("dataDirForLinux");
        }
        movieIdReviews = new HashMap<>();
        userIdReviews = new HashMap<>();
    }

//    @PostConstruct
    private void init() {
        System.out.println("Read File Started ...");
        try {
            String line = null;
            int curr = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            while ((line = br.readLine()) != null && !line.trim().equals("")) {
                String movieId = line.split(": ")[1].trim();
                String userId = br.readLine().split(": ")[1].trim();
                String username = br.readLine().split(": ")[1].trim();
                String helpfulness = br.readLine().trim();
                helpfulness = helpfulness.split(": ")[1].trim();
                int helpful = Integer.valueOf(helpfulness.split("/")[0]);
                int view = Integer.valueOf(helpfulness.split("/")[1]);
                String scoreStr = br.readLine();
                int score = Double.valueOf(scoreStr.split(": ")[1]).intValue();
                long mills = Long.valueOf(br.readLine().split(": ")[1]) * 1000;
                Date time = new Date(mills);
                String summary = null;
                try {
                    summary = br.readLine().split(": ")[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    summary = "";
                }
                String text = br.readLine().split(": ")[1].trim();
                br.readLine();
                MovieReview movieReview = new MovieReview(movieId, userId, username, helpful, view, score, time, summary,
                        text);
                putMovieReview(movieIdReviews, movieId, movieReview);
                putMovieReview(userIdReviews, userId, movieReview);
                curr++;
                if (curr % 100000 == 0) {
                    System.out.println("Has read " + curr + " records...");
                }
            }
            System.out.println("File Read Complele!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeDuplicateMovies();
    }


    private void putMovieReview(Map<String, List<MovieReview>> map, String id, MovieReview review) {
        if (map.get(id) == null) {
            List<MovieReview> reviews = new ArrayList<>();
            reviews.add(review);
            map.put(id, reviews);
        } else {
            map.get(id).add(review);
        }
    }

    private void removeDuplicateMovies() {
        System.out.println("Remove Duplicate Movies Started...");
        Map<String, List<MovieReview>> filteredUserIdReviews = new HashMap<>();
        for (String id : this.findAllUserIds()) {
            List<MovieReview> reviews = this.findByUserId(id);
            Set<MovieReview> set = new HashSet<>(reviews);
            if (set.size() > 1) {
                filteredUserIdReviews.put(id, userIdReviews.get(id));
            }
        }
        userIdReviews = filteredUserIdReviews;
        System.out.println("Remove Duplicate Movies Cmplete!");
    }
    
    @Override
    public List<MovieReview> findByMovieId(String id) {
        return movieIdReviews.get(id);
    }

    @Override
    public List<MovieReview> findByUserId(String id) {
        return userIdReviews.get(id);
    }

    @Override
    public List<String> findAllMovieIds() {
        return new ArrayList<>(movieIdReviews.keySet());
    }

    @Override
    public List<String> findAllUserIds() {
        return new ArrayList<>(userIdReviews.keySet());
    }
}
