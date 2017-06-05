package me.newsong.service.impl;

import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.time.BaseTimeUnit;
import me.newsong.enums.TimeUnit;
import me.newsong.service.comp.MovieReviewTimeDescComparator;
import me.newsong.util.PythonUtil;
import me.newsong.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public abstract class MovieReviewTemplateImpl {
    @Autowired
    protected MovieReviewDOMapper movieReviewDOMapper;
    protected PythonUtil util = PythonUtil.getInstance();

    /**
     * 模板方法
     *
     * @param id
     * @return
     */
    protected abstract List<MovieReviewDO> findMovieReviewDOsById(String id);

    protected List<MovieReviewDO> findMovieReviewDOsSortedByTimeDesc(String id) {
        List<MovieReviewDO> reviews = findMovieReviewDOsById(id);
        Collections.sort(reviews, new MovieReviewTimeDescComparator());
        reviews.forEach(System.out::println);
        return reviews;
    }

    public Map<BaseTimeUnit, Long> findAccumulatedReviewCountsBy(TimeUnit unit, String id, LocalDateTime begin, LocalDateTime end) {
        Map<BaseTimeUnit, Long> map = findMovieReviewDOsById(id)
                .stream()
                .filter((review) -> !review.getTime().isBefore(begin) && !review.getTime().isAfter(end))
                .collect(
                        Collectors.groupingBy(SpringContextUtil.getBean(unit.toString()),
                                Collectors.counting()));
        TreeMap<BaseTimeUnit, Long> result = new TreeMap<>(map);
        long accumulatedCount = 0;
        for (Map.Entry<BaseTimeUnit, Long> entry : result.entrySet()) {
            entry.setValue(entry.getValue() + accumulatedCount);
            accumulatedCount = entry.getValue();
        }
        result.putIfAbsent(BaseTimeUnit.from(unit,begin),0L);
        result.putIfAbsent(BaseTimeUnit.from(unit,end),result.lastEntry().getValue());
        return result;
    }

    public List<String> getKeyWords(List<MovieReviewDO> reviews) {
        String content = reviews.stream()
                .map(MovieReviewDO::getContent)
                .collect(Collectors.joining());
        return util.callForRawList("findKeyWords", Arrays.asList(content, 5), String.class);
    }
}
