package com.swd391.assi2.team2.spider;

import com.swd391.assi2.team2.spider.job.JobFactory;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SpiderFactory {

	public HashMap<String, Spider> spiderMap = new HashMap<>();
	final
	JobFactory jobFactory;

	public SpiderFactory(JobFactory jobFactory) {
		this.jobFactory = jobFactory;
	}

	public void getAllSpider(String filePath){
		try {
			List<Spider> spiders = Files.walk(Paths.get(filePath))
					.filter(Files::isRegularFile)
					.map(Path::toString).peek(System.out::println)
					.map(filePath1 -> {
						try {
							return getSpider(filePath1);
						} catch (IOException | JDOMException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
							e.printStackTrace();
						}
						return null;
					}).peek(System.out::println).filter(Objects::nonNull)
					.collect(Collectors.toList());
			for (Spider spider : spiders) {
				System.out.println(spider.id);
				spiderMap.put(spider.id, spider);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	* Read file spider.config.xml
	* then create spider as config
	* */
	public Spider getSpider(String filePath) throws IOException, JDOMException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Spider spider = new Spider();
		SAXBuilder builder = new SAXBuilder();
		Document spiderConfig = builder.build(new File(filePath));
		Element root = spiderConfig.getRootElement();
		spider.id = root.getAttributeValue("id");
		return initData(spider, root);
	}

	@NotNull
	private Spider initData(Spider spider, Element root) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<Element> jobs = root.getChild("SpiderJobs").getChildren();
		spider.setSpiderJobs(jobFactory.getJobs(jobs, spider));
		return spider;
	}

}
