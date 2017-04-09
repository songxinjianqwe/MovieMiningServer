package me.newsong.flyweight.domain.entity;

import java.util.Map;
import java.util.Set;

/**
 * Created by SinjinSong on 2017/4/9.
 */
public class ReviewTimesAndScores {
    private Map<Integer, Set<Double>> reviewTimesAndScores;
    private double correlationCoefficient;

    public ReviewTimesAndScores(Map<Integer, Set<Double>> reviewTimesAndScores, double correlationCoefficient) {
        this.reviewTimesAndScores = reviewTimesAndScores;
        this.correlationCoefficient = correlationCoefficient;
    }

    public Map<Integer, Set<Double>> getReviewTimesAndScores() {
        return reviewTimesAndScores;
    }

    public void setReviewTimesAndScores(Map<Integer, Set<Double>> reviewTimesAndScores) {
        this.reviewTimesAndScores = reviewTimesAndScores;
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public void setCorrelationCoefficient(double correlationCoefficient) {
        this.correlationCoefficient = correlationCoefficient;
    }

    @Override
    public String toString() {
        return "ReviewTimesAndScores{" +
                "reviewTimesAndScores=" + reviewTimesAndScores +
                ", correlationCoefficient=" + correlationCoefficient +
                '}';
    }
}
