package me.newsong.flyweight.service.impl.comp;

import me.newsong.flyweight.domain.entity.MovieReview;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@Component("Helpful")
public class MovieReviewHelpfulDescComparator implements Comparator<MovieReview> {

    @Override
    public int compare(MovieReview o1, MovieReview o2) {
        int result = Double.compare(1 - 1.0 * o1.getHelpfulTimes() / o1.getViewTimes(), 1 - 1.0 * o2.getHelpfulTimes() / o2.getViewTimes());
        if(result == 0){
            return o2.getViewTimes() - o1.getViewTimes();
        }else{
            return result;
        }
    }
}
