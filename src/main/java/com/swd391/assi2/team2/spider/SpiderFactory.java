package com.swd391.assi2.team2.spider;

import com.swd391.assi2.team2.spider.job.JobFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SpiderFactory {
	@Autowired
	JobFactory jobFactory;
	/*
	* Read file spider.config.xml
	* then create spider as config
	* */
	public Spider getSpider(String filePath) throws IOException, JDOMException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Spider spider = new Spider();
		SAXBuilder builder = new SAXBuilder();
		Document spiderConfig = builder.build(new File(filePath));
		Element root = spiderConfig.getRootElement();
		return initData(spider, root);
	}

	@NotNull
	private Spider initData(Spider spider, Element root) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<Element> jobs = root.getChild("SpiderJobs").getChildren();
		spider.setSpiderJobs(jobFactory.getJobs(jobs));
		return spider;
	}

}
