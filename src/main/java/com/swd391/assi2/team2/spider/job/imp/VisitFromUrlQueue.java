package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.begin.InJob;
import com.swd391.assi2.team2.utils.URLQueue;
import com.swd391.assi2.team2.utils.VisitedUrl;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitFromUrlQueue extends ComplexJob implements InJob {
	public SpiderLog LOGGER;

	public String queueName;

	public List<String> urlStartWiths = new ArrayList<>();
	@Override
	public ArrayList<Element> start(ArrayList<Element> elements) throws IOException, NoSuchMethodException {
		return null;
	}

	@Override
	public Element start() throws IOException {
		URLQueue urlQueue = URLQueue.URL_QUEUE_HASHMAP.get(queueName);

		if(urlQueue != null && !urlQueue.isEmpty()){
			while (true){
				String url = urlQueue.poll();
				urlQueue.add(url);

				if(url == null) continue;

				if(VisitedUrl.getInstance().contains(url)) continue;

				boolean checkStartWith = false;

				for (String urlStartWith : urlStartWiths) {
					if (url.startsWith(urlStartWith)) {
						checkStartWith = true;
						break;
					}
				}

				if(!checkStartWith) continue;

				try{
					Document document = Jsoup.connect(url).get();
					Element element = new Element("div");
					element.html(document.html());
					VisitedUrl.getInstance().add(url);
					this.LOGGER.info("Visiting: " + url, this);
					this.LOGGER.info("pool size: " + urlQueue.size(), this);
					return element;
				}catch (Exception e){
					this.LOGGER.error(e.getMessage(), this);
				}
			}
		}
		return null;
	}


	@Override
	public SpiderJob initData(org.jdom2.Element element, JobFactory jobFactory, Spider spider) {

		LOGGER = spider.spiderLog;

		this.queueName = element.getChild("queueName").getText();
		this.method = element.getChildText("method");
		List<org.jdom2.Element> urlFilters = element.getChild("urlStartWiths").getChildren();
		if(urlFilters != null && !urlFilters.isEmpty()){
			urlFilters.stream().map(org.jdom2.Element::getText).forEach(urlStartWiths::add);
		}
		return this;
	}
}
