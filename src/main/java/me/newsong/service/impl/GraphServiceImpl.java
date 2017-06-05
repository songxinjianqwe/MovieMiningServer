package me.newsong.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import me.newsong.service.GraphService;
import me.newsong.util.JsonUtil;
import me.newsong.util.PythonUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@Service
public class GraphServiceImpl implements GraphService {
    private PythonUtil util = PythonUtil.getInstance();

    @Override
    public String getHistDistributionOfImdbScore(Integer bins, Boolean normed) {
        return util.call("get_hist_distribution_of_imdb_score", Arrays.asList(bins, normed), String.class);
    }

    @Override
    public String getHistDistributionOfImdbReviewCount(Integer bins, Boolean normed) {
        return util.call("get_hist_distribution_of_imdb_review_count", Arrays.asList(bins, normed), String.class);
    }

    @Override
    public Map<Double, Double> getCdfOfImdbScore() {
        String json = util.call("get_cdf_of_imdb_score", null, String.class);
        try {
            Map<Double, Double> rawMap = JsonUtil.getObjectMapper().readValue(json, new TypeReference<Map<Double, Double>>() {
            });
            return new TreeMap<>(rawMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Double, Double> getKdeOfImdbScore() {
        String json = util.call("get_kde_of_imdb_score", null, String.class);
        try {
            Map<Double, Double> rawMap = JsonUtil.getObjectMapper().readValue(json, new TypeReference<Map<Double, Double>>() {
            });
            return new TreeMap<>(rawMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Double> getKdeOfImdbReviewCount() {
        String json = util.call("get_kde_of_imdb_review_count", null, String.class);
        try {
            Map<Integer, Double> rawMap = JsonUtil.getObjectMapper().readValue(json, new TypeReference<Map<Integer, Double>>() {
            });
            return new TreeMap<>(rawMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
