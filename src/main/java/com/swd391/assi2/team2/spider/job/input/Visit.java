package com.swd391.assi2.team2.spider.job.input;

import com.swd391.assi2.team2.spider.job.process.ProcessJob;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import static com.swd391.assi2.team2.spider.job.SpiderJob.MethodCall.FindAll;
import static com.swd391.assi2.team2.spider.job.SpiderJob.MethodCall.Start;

public class Visit implements InJob, ProcessJob {
	public String url;
	public String method;

	public Visit() {
	}

	public Visit(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public ArrayList<Element> start(ArrayList<Element> elements) throws IOException {
		Document document = Jsoup.connect(url).get();
		ArrayList<Element> result = new ArrayList<>();
		result.add(document);
		return result;
	}

	/**
	 * @param elements a list of <a href=""/> elements
	 * @return the documents get from the link
	 * */
	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) throws Exception {
		ArrayList<Element> result = new ArrayList<>();
		for (Element element : elements) {
			String link = element.attr("href");
			result.add(Jsoup.connect(link).get());
		}
		return result;
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> map(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> peek(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return Start;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{Start, FindAll};
	}
}
