package me.newsong.flyweight.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.newsong.flyweight.domain.Month;
import me.newsong.flyweight.domain.Movie;
import me.newsong.flyweight.domain.Season;
import me.newsong.flyweight.service.iface.MovieService;

/**
 * 
 * @author Sinjin Song
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieService service;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/ids", method = RequestMethod.GET)
	public List<String> findAllMovieIds() {
		return service.findAllIds();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Movie findMovieByID(@PathVariable("id") String id) {
		return service.findMovieByID(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/scores/{id}", method = RequestMethod.GET)
	public Map<Integer, Long> findScoresByStar(@PathVariable("id") String id) {
		return service.findScoresByStar(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/accum_review_counts_by_month/{id}", method = RequestMethod.GET)
	public Map<Month, Long> findAccumulatedReviewCountsByMonth(@PathVariable("id") String id,
			@RequestParam("begin") Long begin, @RequestParam("end") Long end, Locale locale) {
		return service.findAccumulatedReviewCountsBy(Month.class, id, new Date(begin), new Date(end));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/accum_review_counts_by_season/{id}", method = RequestMethod.GET)
	public Map<Season, Long> findAccumulatedReviewCountsBySeason(@PathVariable("id") String id,
			@RequestParam("begin") Long begin, @RequestParam("end") Long end, Locale locale) {
		return service.findAccumulatedReviewCountsBy(Season.class, id, new Date(begin), new Date(end));
	}

}
