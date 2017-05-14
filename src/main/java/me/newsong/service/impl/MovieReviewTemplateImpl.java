package me.newsong.service.impl;

import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.enums.TimeUnit;
import me.newsong.service.comp.MovieReviewTimeDescComparator;
import me.newsong.util.PythonUtil;
import me.newsong.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
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
        return reviews;
    }

    public <T> Map<T, Long> findAccumulatedReviewCountsBy(TimeUnit unit, String id, LocalDateTime begin, LocalDateTime end) {
        Map<T, Long> map = findMovieReviewDOsById(id)
                .stream()
                .filter((review) -> !review.getTime().isBefore(begin) && !review.getTime().isAfter(end))
                .collect(
                        Collectors.groupingBy(SpringContextUtil.getBean(unit.toString()), 
                                Collectors.counting()));
        Map<T, Long> result = new TreeMap<>();
        result.putAll(map);
        long accumulatedCount = 0;
        for (Entry<T, Long> entry : result.entrySet()) {
            entry.setValue(entry.getValue() + accumulatedCount);
            accumulatedCount = entry.getValue();
        }
        return result;
    }

    public List<String> getKeyWords(List<MovieReviewDO> reviews) {
        String content = reviews.stream()
                .map(MovieReviewDO::getContent)
                .collect(Collectors.joining());
        return util.callForRawList("findKeyWords", Arrays.asList(content, 5), String.class);
    }
}
