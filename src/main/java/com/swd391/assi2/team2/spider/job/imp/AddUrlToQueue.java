package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import com.swd391.assi2.team2.utils.URLQueue;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddUrlToQueue extends ComplexJob implements OutJob {

	public String queueName;
	public String addIfMissingUrl;
	public List<String> urlStartWiths = new ArrayList<>();
	private URLQueue urlQueue;

	public StringBuilder spiderLog;

	public AddUrlToQueue() {
	}

	public AddUrlToQueue(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public Object collect(Element element) {
		String link = element.attr("href");

		if(!link.startsWith(addIfMissingUrl))
			link = addIfMissingUrl + link;

		if(urlQueue == null){
			urlQueue = new URLQueue();
		}
		for (String urlStartWith : urlStartWiths) {
			if(link.startsWith(urlStartWith)){
				urlQueue.add(link); break;
			}
		}

		URLQueue.URL_QUEUE_HASHMAP.put(queueName, urlQueue);
		return null;
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		if (elements.isEmpty()) return null;

		URLQueue urlQueue = URLQueue.URL_QUEUE_HASHMAP.get(queueName);
		if(urlQueue == null) urlQueue = new URLQueue();

		for (Element element : elements) {
			String link = element.attr("href");
			if(!link.startsWith(addIfMissingUrl))
				link = addIfMissingUrl + link;

			for (String urlStartWith : urlStartWiths) {
				if(link.startsWith(urlStartWith)){
					urlQueue.addUnique(link); break;
				}
			}
		}

		URLQueue.URL_QUEUE_HASHMAP.put(queueName, urlQueue);
		return null;
	}

	@Override
	public SpiderJob initData(org.jdom2.Element element, JobFactory jobFactory, Spider spider) {
		this.queueName = element.getChild("queueName").getText();
		this.addIfMissingUrl = element.getChild("addIfMissingUrl").getText();

		List<org.jdom2.Element> urlFilters = element.getChild("urlStartWiths").getChildren();
		if(urlFilters != null && !urlFilters.isEmpty()){
			urlFilters.forEach(u -> this.urlStartWiths.add(u.getText()));
		}

		URLQueue urlQueue = URLQueue.URL_QUEUE_HASHMAP.get(queueName);
		if(urlQueue == null) urlQueue = new URLQueue();

		URLQueue.URL_QUEUE_HASHMAP.put(queueName, urlQueue);
		this.urlQueue = urlQueue;
		Iterator<String> iterator = urlQueue.iterator();
		return this;
	}
}
