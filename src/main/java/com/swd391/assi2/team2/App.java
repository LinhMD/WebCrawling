package com.swd391.assi2.team2;

import com.swd391.assi2.team2.gui.MainFrame;
import com.swd391.assi2.team2.repository.JobRepository;
import com.swd391.assi2.team2.repository.UnitOfWork;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderFactory;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class App implements ApplicationRunner {
	@Autowired
	SpiderFactory spiderFactory;
	@Autowired
	JobFactory jobFactory;
	@Autowired
	JobRepository jobRepository;
	@Autowired
	UnitOfWork work;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
		builder.headless(false);
		ApplicationContext ctx = builder.run(args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<Spider> allSpider = spiderFactory.getAllSpider("src/main/resources/spider/");

//		for (Spider spider : allSpider) {
//			System.out.println(spider.toTreeNode().getChildCount());
//		}

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		SwingUtilities.invokeLater(() -> {
			MainFrame mainFrame = new MainFrame(spiderFactory, jobFactory, work, allSpider);

			JFrame frame = new JFrame("Spider App");

			frame.setContentPane(mainFrame.panel);
			frame.setSize(1000, 700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});

	}

}
