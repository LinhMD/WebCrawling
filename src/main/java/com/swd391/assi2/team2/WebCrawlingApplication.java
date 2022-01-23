package com.swd391.assi2.team2;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebCrawlingApplication  implements ApplicationRunner {
	@Autowired
	SpiderFactory spiderFactory;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(WebCrawlingApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		Document document = Jsoup.connect("https://123job.vn/jobs").get();
//		Elements title = document.getElementsByClass("info-title");
//		title.forEach(element -> {
//			Elements link = element.getElementsByTag("a");
//			System.out.println(link.attr("href"));
//		});
//		Document jobDetail = Jsoup.connect("https://123job.vn/viec-lam/tuyen-dung-nhan-vien-sale-khong-yeu-cau-kinh-nghiem-Mv91wVnOqW").get();
		Spider spider = spiderFactory.getSpider("src/main/resources/spider/spider.config.xml");
		spider.getSpiderJobs().forEach(System.out::println);
	}
}
