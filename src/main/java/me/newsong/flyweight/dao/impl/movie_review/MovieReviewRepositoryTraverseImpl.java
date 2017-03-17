package me.newsong.flyweight.dao.impl.movie_review;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.MovieReview;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by SinjinSong on 2017/3/16.
 */
@Repository("Traverse")
public class MovieReviewRepositoryTraverseImpl implements MovieReviewRepository {
    private String path;
    private ResourceBundle rb;

    public MovieReviewRepositoryTraverseImpl() {
        rb = ResourceBundle.getBundle("fileSystemData");
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            path = rb.getString("dataDirForWin");
        } else {
            path = rb.getString("dataDirForLinux");
        }

    }
    
    @Override
    public List<MovieReview> findByMovieId(String id) {
        List<MovieReview> reviews = new ArrayList<>();
        try {
            String line = null;
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
                if(id.equals(movieId)){
                    MovieReview movieReview = new MovieReview(movieId, userId, username, helpful, view, score, time, summary,
                            text);
                    reviews.add(movieReview);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<MovieReview> findByUserId(String id) {
        return null;
    }

    @Override
    public List<String> findAllMovieIds() {
        return null;
    }

    @Override
    public List<String> findAllUserIds() {
        return null;
    }
}
