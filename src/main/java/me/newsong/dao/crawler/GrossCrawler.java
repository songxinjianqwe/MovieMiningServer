package me.newsong.dao.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/6/9.
 */
@Component
public class GrossCrawler {
    private static String URL = "http://www.boxofficemojo.com/daily/chart/";
    private static String BASE = "http://www.boxofficemojo.com/%s";

    public Map<Integer, Long> crawl(int index) {
        Map<Integer,Long> map = new HashMap<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements tables = doc.getElementsByTag("table");
            Element element = tables.get(6).getElementsByTag("table").first();
            Element parent = element.getElementsByTag("tr").get(2);
            Elements trs = parent.children().get(0).getElementsByTag("tr");
            Element tr = trs.get(index);
            String href = String.format(BASE, tr.getElementsByTag("a").first().attr("href"));
            System.out.println(href);
            Document chart = Jsoup.connect(href).data("view", "chart").get();
            Elements grossTRs = chart.getElementsByClass("chart-wide").first().getElementsByTag("tr");
            for (int i = 1; i < grossTRs.size(); ++i) {
                Element grossTR = grossTRs.get(i);
                Elements tds = grossTR.getElementsByTag("td");
                Integer key = Integer.valueOf(tds.get(tds.size()-1).text().trim());
                Long value = Long.valueOf(tds.get(tds.size()-2).text().replace("$","").replace(",","").trim());
                map.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    @Test
    public void test() {
        Map<Integer, Long> map = crawl(1);
        for(Map.Entry<Integer,Long> entry:map.entrySet()){
            System.out.println(entry);
        }
    }
}
