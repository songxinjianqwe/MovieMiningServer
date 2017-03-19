package me.newsong.flyweight.service.impl.comp;

import me.newsong.flyweight.domain.RemoteMovieInfo;

import java.rmi.Remote;
import java.util.Comparator;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class RemoteMovieInfoTimeDescComparator implements Comparator<RemoteMovieInfo>{
    
    @Override
    public int compare(RemoteMovieInfo o1, RemoteMovieInfo o2) {
        if (o1.getReleaseTime().before(o2.getReleaseTime())) {
			return 1;
		} else if (o1.getReleaseTime().after(o2.getReleaseTime())) {
			return -1;
		} else {
			return 0;
		}
    }
}
