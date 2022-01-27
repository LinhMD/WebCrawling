package com.swd391.assi2.team2;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderFactory;
import com.swd391.assi2.team2.spider.job.JobFactory;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class WebCrawlingApplication  implements ApplicationRunner {
	@Autowired
	SpiderFactory spiderFactory;
	@Autowired
	JobFactory jobFactory;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(WebCrawlingApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
////		Document document = Jsoup.connect("https://123job.vn/jobs").get();
////		Elements title = document.getElementsByClass("info-title");
////		title.forEach(element -> {
////			Elements link = element.getElementsByTag("a");
////			System.out.println(link.attr("href"));
////		});
////		Document jobDetail = Jsoup.connect("https://123job.vn/viec-lam/tuyen-dung-nhan-vien-sale-khong-yeu-cau-kinh-nghiem-Mv91wVnOqW").get();
		Spider spider = spiderFactory.getSpider("src/main/resources/spider/spider.config.xml");
		Object run = spider.run(null);
//		System.out.println(spider);
		System.out.println(run);


	}
}
