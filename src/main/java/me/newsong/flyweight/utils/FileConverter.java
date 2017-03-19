package me.newsong.flyweight.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.newsong.flyweight.domain.Index;
import me.newsong.flyweight.domain.entity.MovieReview;

public class FileConverter {
	private Map<String, List<Index>> movieIndIndexMap;
	private Map<String, List<Index>> userIdIndexMap;
	private ObjectMapper objectMapper;

	public FileConverter() {
		movieIndIndexMap = new HashMap<>();
		objectMapper = new ObjectMapper();
		userIdIndexMap = new HashMap<>();
	}

	public void convert(String src, String destMainFile, String destMovieIdIndexFile, String destUserIdIndexFile)
			throws IOException {
		Map<Integer, Integer> pageMovieIndex = new HashMap<>();
		for (int i = 0; i < 24; ++i) {
			pageMovieIndex.put(i, 0);
		}
		int pageIndex = 0;
		int movieIndex = 0;
		RandomAccessFile writer = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(src), "UTF-8"));
		String line = null;
		while ((line = br.readLine()) != null && !line.trim().equals("")) {
			String movieId = line.split(": ")[1].trim();
			String userId = br.readLine().split(": ")[1].trim();
			String username = br.readLine().split(": ")[1].trim();
			String helpfulness = br.readLine().trim();
			helpfulness = helpfulness.split(": ")[1].trim();
			int helpful = Integer.valueOf(helpfulness.split("/")[0]);
			int view = Integer.valueOf(helpfulness.split("/")[1]);
			String scoreStr = br.readLine();
			int score = Double.valueOf(scoreStr.split(": ")[1]).intValue();
			long mills = Long.valueOf(br.readLine().split(": ")[1])*1000;
			Date time = new Date(mills);
			String summary = null;
			try {
				summary = br.readLine().split(": ")[1].trim();
			} catch (ArrayIndexOutOfBoundsException e) {
				summary = "";
			}
			String text = br.readLine().split(": ")[1].trim();
			br.readLine();
			MovieReview movieReview = new MovieReview(movieId, userId, username, helpful, view, score, time, summary,
					text);
			byte[] review = objectMapper.writeValueAsBytes(movieReview);
			pageIndex = review.length / 1000;// 24000 -> 24页
			movieIndex = pageMovieIndex.get(pageIndex);
			System.out.println("movieId:" + movieId + "     pageIndex:" + pageIndex + "   movieIndex:" + movieIndex
					+ "    length:" + review.length);
			if (writer != null) {
				writer.close();
			}
			writer = new RandomAccessFile(destMainFile + "_" + pageIndex + ".json", "rw");
			writer.seek(pageMovieIndex.get(pageIndex) * 1000 * (pageIndex + 1));
			writer.write(review);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < (pageIndex + 1) * 1000 - review.length; ++i) {
				sb.append("\t");
			}
			writer.write(sb.toString().getBytes());
			pageMovieIndex.put(pageIndex, pageMovieIndex.get(pageIndex) + 1);// 电影索引+1
			if (movieIndIndexMap.get(movieId) == null) {
				List<Index> rows = new ArrayList<>();
				rows.add(new Index(pageIndex, movieIndex));
				movieIndIndexMap.put(movieId, rows);
			} else {
				movieIndIndexMap.get(movieId).add(new Index(pageIndex, movieIndex));
			}
			if (userIdIndexMap.get(userId) == null) {
				List<Index> rows = new ArrayList<>();
				rows.add(new Index(pageIndex, movieIndex));
				userIdIndexMap.put(userId, rows);
			} else {
				userIdIndexMap.get(userId).add(new Index(pageIndex, movieIndex));
			}
		}
		writer.close();
		br.close();
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(destMovieIdIndexFile));
		bw1.write(objectMapper.writeValueAsString(movieIndIndexMap));
		bw1.close();
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(destUserIdIndexFile));
		bw2.write(objectMapper.writeValueAsString(userIdIndexMap));
		bw2.close();
	}

	public static void main(String[] args) {
		FileConverter converter = new FileConverter();
		try {
			converter.convert("D:/movies_mock.txt", "E:/movies", "E:/movieIdIndex.json", "E:/userIdIndex.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
