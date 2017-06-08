package me.newsong.service.impl;

import me.newsong.service.PredictionService;
import me.newsong.util.PythonUtil;
import org.springframework.stereotype.Service;

/**
 * Created by SinjinSong on 2017/6/8.
 */
@Service
public class PredictionServiceImpl implements PredictionService{
    private PythonUtil util = PythonUtil.getInstance();
    
    
    @Override
    public String getScoreFeatureImportance() {
        return util.call("get_score_feature_importance",null,String.class);
    }
    
    @Override
    public String getGrossFeatureImportance() {
        return util.call("get_gross_feature_importance",null,String.class);
    }
}
