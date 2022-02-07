package com.swd391.assi2.team2;

import com.swd391.assi2.team2.job123.models.Job;
import com.swd391.assi2.team2.job123.models.Recruitment;
import com.swd391.assi2.team2.job123.repository.JobRepository;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderFactory;
import com.swd391.assi2.team2.spider.job.JobFactory;
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
//		Document document = Jsoup.connect("https://123
//
//
//
//
//
//		job.vn/jobs").get();
//		Elements title = document.getElementsByClass("info-title");
//		title.forEach(element -> {
//			Elements link = element.getElementsByTag("a");
//			System.out.println(link.attr("href"));
//		});

		Spider spider = spiderFactory.getSpider("src/main/resources/spider/spider.config.xml");
		Object run = spider.run(null);
//		System.out.println(spider);
//		System.out.println(run);
//		System.out.println(run instanceof DataModel);

//		Document jobDetail = Jsoup.connect("https://123job.vn/viec-lam/nhan-vien-phuc-vu-QJDa6Wk69G").get();
//		System.out.println(jobDetail.select("div.content-group:contains(Mô tả công việc) > div"));
//		ArrayList<Recruitment> listRec = new ArrayList<>();
//        recruitmentRepository.save(listRec);
		Recruitment recruitment = (Recruitment) run;
		Job job = jobRepository.makeTransaction(recruitment);
		//jobRepository.save(job); phần benefit và requirment quá dài để lưu vào database
		System.out.println(job.toString());

	}

}
