package me.newsong.flyweight.service.impl.comp;

import me.newsong.flyweight.domain.entity.RemoteMovieInfo;

import java.util.Comparator;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class RemoteMovieInfoScoreDescComparator implements Comparator<RemoteMovieInfo> {
    @Override
    public int compare(RemoteMovieInfo o1, RemoteMovieInfo o2) {
        if (o1.getMovie().getAvgScore() > o2.getMovie().getAvgScore()) {
            return -1;
        } else if (o1.getMovie().getAvgScore() < o2.getMovie().getAvgScore()) {
            return 1;
        } else {
            return 0;
        }
    }
}
