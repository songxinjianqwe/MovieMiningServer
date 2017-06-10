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
        return o2.getHelpfulTimes() - o1.getHelpfulTimes();
    }
}
