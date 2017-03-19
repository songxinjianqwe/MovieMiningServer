package me.newsong.flyweight.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.newsong.flyweight.enums.TimeUnit;
import me.newsong.flyweight.exceptions.TimeUnitNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.newsong.flyweight.domain.DescLengthRange;
import me.newsong.flyweight.domain.time.Season;
import me.newsong.flyweight.domain.User;
import me.newsong.flyweight.service.iface.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/ids", method = RequestMethod.GET)
	public List<String> findAllUserIds() {
		return service.findAllIds();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findMovieByID(@PathVariable("id") String id) {
		return service.findUserById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/desc_lengths/{id}", method = RequestMethod.GET)
	public Map<DescLengthRange, Long> findDescLengthsWithRange(@PathVariable("id") String id,
			@RequestParam(defaultValue = "20", required = false) int gap) {
		return service.findDescLengthsWithRange(id, gap);
	}

	
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/accum_review_counts/{id}", method = RequestMethod.GET)
    public Map<Season, Long> findAccumulatedReviewCounts(@PathVariable("id") String id, @RequestParam("timeUnit") String timeUnit,
                                                         @RequestParam("begin") Long begin, @RequestParam("end") Long end, Locale locale) {
        TimeUnit unit = TimeUnit.fromString(StringUtils.capitalize(timeUnit));
        if (unit == null) {
            throw new TimeUnitNotFoundException(timeUnit);
        }
        return service.findAccumulatedReviewCountsBy(unit, id, new Date(begin), new Date(end));
    }
}
