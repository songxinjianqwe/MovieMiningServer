package me.newsong.flyweight.util.movies;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import me.newsong.flyweight.util.BaseSpringTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.dao.impl.movie_review.MovieReviewRepositoryCachedImpl;
import me.newsong.flyweight.domain.Index;
import me.newsong.flyweight.domain.entity.MovieReview;

public class MergeDuplicateMoviesUtil extends BaseSpringTester{
	@Autowired
	private MovieReviewRepository dao;
	private ResourceBundle rb;
	private Map<String, List<Index>> userIdIndexMap;
	private Map<String, List<Index>> filteredUserIndexMap;
	private ObjectMapper mapper = new ObjectMapper();

	public MergeDuplicateMoviesUtil() {
		rb = ResourceBundle.getBundle("fileSystemData");
		mapper = new ObjectMapper();
		filteredUserIndexMap = new HashMap<>();
		try {
			userIdIndexMap = mapper.readValue(
					MovieReviewRepositoryCachedImpl.class.getClassLoader().getResourceAsStream(rb.getString("userIndex")),
					new TypeReference<Map<String, List<Index>>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void merge() throws IOException {
		System.out.println(dao.findAllUserIds().size());
		for (String id : dao.findAllUserIds()) {
			List<MovieReview> reviews = dao.findByUserId(id);
			Set<MovieReview> set = new HashSet<>(reviews);
			if(set.size() > 1){
				filteredUserIndexMap.put(id, userIdIndexMap.get(id));
//				for(MovieReview review:set){
//					System.out.println(review);
//				}
			}
		}
		System.out.println(filteredUserIndexMap.size());
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:/userIndex.json"));
		bw.write(mapper.writeValueAsString(filteredUserIndexMap));
		bw.close();
	}
}
