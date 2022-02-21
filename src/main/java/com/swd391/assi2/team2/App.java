package com.swd391.assi2.team2;

import com.swd391.assi2.team2.repository.JobRepository;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderFactory;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.utils.FinalJobList;
import com.swd391.assi2.team2.utils.URLQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements ApplicationRunner {
	@Autowired
	SpiderFactory spiderFactory;
	@Autowired
	JobFactory jobFactory;
	@Autowired
	JobRepository jobRepository;

	//-----------

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
		//------------ spider zone ---- //
//		builder.headless(false);
//		ConfigurableApplicationContext context = builder.run(args);

		//---------------
		ApplicationContext ctx = SpringApplication.run(App.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Spider spider = spiderFactory.getSpider("src/main/resources/spider/job123/navigate_spider.config.xml");
		 spider.run();
         spider = spiderFactory.getSpider("src/main/resources/spider/job123/spider.config.xml");
		Object run = spider.run(null);
		System.out.println(FinalJobList.JOB_LIST);
	}

}
