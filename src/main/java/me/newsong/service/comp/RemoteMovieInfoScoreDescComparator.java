package me.newsong.service.comp;

import me.newsong.domain.entity.RemoteMovieInfoDO;

import java.util.Comparator;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class RemoteMovieInfoScoreDescComparator implements Comparator<RemoteMovieInfoDO> {
    @Override
    public int compare(RemoteMovieInfoDO o1, RemoteMovieInfoDO o2) {
        if (o1.getMovie().getAvgScore() > o2.getMovie().getAvgScore()) {
            return -1;
        } else if (o1.getMovie().getAvgScore() < o2.getMovie().getAvgScore()) {
            return 1;
        } else {
            return 0;
        }
    }
}
