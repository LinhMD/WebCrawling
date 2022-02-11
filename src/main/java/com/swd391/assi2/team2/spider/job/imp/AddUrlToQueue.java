package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import com.swd391.assi2.team2.utils.URLQueue;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class AddUrlToQueue implements OutJob {

	public String queueName;

	public AddUrlToQueue() {
	}

	public AddUrlToQueue(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public Object collect(Element element) {
		String link = element.attr("href");
		if(!link.contains("https://123job.vn/")) return null;
		URLQueue urlQueue = URLQueue.URL_QUEUE_HASHMAP.get(queueName);
		if(urlQueue == null){
			urlQueue = new URLQueue();
		}
		urlQueue.add(link);

		URLQueue.URL_QUEUE_HASHMAP.put(queueName, urlQueue);
		return null;
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		if (elements.isEmpty()) return null;

		URLQueue urlQueue = URLQueue.URL_QUEUE_HASHMAP.get(queueName);
		if(urlQueue == null){
			urlQueue = new URLQueue();
		}
		elements.stream()
				.map(element -> element.attr("href"))
				.filter(s -> s.contains("https://123job.vn/"))
				.forEach(urlQueue::add);

		URLQueue.URL_QUEUE_HASHMAP.put(queueName, urlQueue);
		return null;
	}
}
