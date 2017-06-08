package me.newsong.controller;

import me.newsong.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SinjinSong on 2017/6/8.
 */
@RestController
@CrossOrigin
@RequestMapping("/prediction")
public class PredictionController {
    @Autowired
    private PredictionService predictionService;

    @RequestMapping(value = "/box_office", method = RequestMethod.GET, produces = "application/json")
    public String getGrossFeatureImportance() {
        return predictionService.getGrossFeatureImportance();
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET, produces = "application/json")
    public String getScoreFeatureImportance() {
        return predictionService.getScoreFeatureImportance();
    }
}
