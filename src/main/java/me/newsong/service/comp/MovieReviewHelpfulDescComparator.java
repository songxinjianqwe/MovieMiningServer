package me.newsong.service.comp;

import me.newsong.domain.entity.MovieReviewDO;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@Component("Helpful")
public class MovieReviewHelpfulDescComparator implements Comparator<MovieReviewDO> {

    @Override
    public int compare(MovieReviewDO o1, MovieReviewDO o2) {
        int result = Double.compare(1 - 1.0 * o1.getHelpfulTimes() / o1.getViewTimes(), 1 - 1.0 * o2.getHelpfulTimes() / o2.getViewTimes());
        if(result == 0){
            return o2.getViewTimes() - o1.getViewTimes();
        }else{
            return result;
        }
    }
}
