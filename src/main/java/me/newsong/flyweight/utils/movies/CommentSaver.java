package me.newsong.flyweight.utils.movies;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.newsong.flyweight.utils.BaseSpringTester;
import me.newsong.flyweight.utils.PythonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.entity.MovieReview;

public class CommentSaver extends BaseSpringTester{
	@Autowired
	private MovieReviewRepository dao;
	private final String movieBaseDir = "E:/movie_comments/comment";
	private final String userBaseDir = "E:/user_comments/comment";
	private PythonUtil util = PythonUtil.getInstance();;
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void saveMovieContentToFile() throws IOException {
		List<MovieReview> reviews = null;
		String content = null;
		for (String id : dao.findAllMovieIds()) {
			System.out.println(id);
			reviews = dao.findByMovieId(id);
			content = reviews.stream().map(MovieReview::getContent).collect(Collectors.joining());
			FileWriter writer = new FileWriter(movieBaseDir + "_" + id + ".txt");
			StringBuilder sb = new StringBuilder();
			for (String word : util.callForRawList("cutWords", Arrays.asList(content), String.class)) {
				sb.append(word + " ");
			}
			writer.write(sb.toString());
			writer.close();
		}
	}

	@Test
	public void saveUserContentToFile() throws IOException {
		List<MovieReview> reviews = null;
		String content = null;
		for (String id : dao.findAllUserIds()) {
			System.out.println(id);
			reviews = dao.findByUserId(id);
			content = reviews.stream().map(MovieReview::getContent).collect(Collectors.joining());
			FileWriter writer = new FileWriter(userBaseDir + "_" + id + ".txt");
			StringBuilder sb = new StringBuilder();
			for (String word : util.callForRawList("cutWords", Arrays.asList(content), String.class)) {
				sb.append(word + " ");
			}
			writer.write(sb.toString());
			writer.close();
		}
	}

	@Test
	public void saveMovieResult() throws JsonProcessingException, IOException {
		util.call("calcKeyWords", Arrays.asList("E:/movie_comments/", 5));
	}
	
	@Test
	public void saveUserResult() throws JsonProcessingException, IOException {
		util.call("calcKeyWords", Arrays.asList("E:/user_comments/", 5, "E:/userKeyWords.json"));
	}
}