package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.core.begin.InJob;
import com.swd391.assi2.team2.utils.URLQueue;
import com.swd391.assi2.team2.utils.VisitedUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class VisitFromUrlQueue implements InJob {

	public String queueName;

	@Override
	public ArrayList<Element> start(ArrayList<Element> elements) throws IOException, NoSuchMethodException {
		return null;
	}

	@Override
	public Element start() throws IOException {
		URLQueue urlQueue = URLQueue.URL_QUEUE_HASHMAP.get(queueName);

		if(urlQueue != null && !urlQueue.isEmpty()){
			while (true){
				String url = urlQueue.pop();
				if(!VisitedUrl.getInstance().contains(url)){
					Document document = Jsoup.connect(url).get();
					Element element = new Element("div");
					element.html(document.html());
					VisitedUrl.getInstance().add(url);
					return element;
				}
			}
		}
		return null;
	}
}
