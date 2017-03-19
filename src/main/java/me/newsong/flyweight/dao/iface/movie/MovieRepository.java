package me.newsong.flyweight.dao.iface.movie;

import me.newsong.flyweight.domain.entity.RemoteMovieInfo;

/**
 * Created by SinjinSong on 2017/3/16.
 */
public interface MovieRepository {
    RemoteMovieInfo findMovieViaCrawler(String id);
}
