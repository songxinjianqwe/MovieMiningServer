package me.newsong.service.impl;

import me.newsong.service.GraphService;
import me.newsong.util.PythonUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@Service
public class GraphServiceImpl implements GraphService {
    private PythonUtil util = PythonUtil.getInstance();

    @Override
    public String getHistDistributionOfImdbScore(Integer bins, Boolean normed) {
        return util.call("get_hist_distribution_of_imdb_score", Arrays.asList(bins,normed), String.class);
    }

    @Override
    public String getHistDistributionOfImdbReviewCount(Integer bins, Boolean normed) {
        return util.call("get_hist_distribution_of_imdb_review_count", Arrays.asList(bins,normed), String.class);
    }

    @Override
    public String getCdfOfImdbScore() {
        return util.call("get_cdf_of_imdb_score", null, String.class);
    }

    @Override
    public String getKdeOfImdbScore() {
        return util.call("get_kde_of_imdb_score", null, String.class);
    }

    @Override
    public String getKdeOfImdbReviewCount() {
        return util.call("get_kde_of_imdb_review_count", null, String.class);
    }

    @Override
    public String getScoreOfSingleYear() {
        return util.call("get_score_of_single_year", null, String.class);
    }

    @Override
    public String getBoxOfficeOfSingleYear() {
        return util.call("get_box_office_of_single_year", null, String.class);
    }
}
