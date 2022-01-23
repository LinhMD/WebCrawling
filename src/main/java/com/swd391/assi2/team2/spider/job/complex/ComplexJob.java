package com.swd391.assi2.team2.spider.job.complex;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jdom2.Element;

import java.util.List;

public abstract class ComplexJob implements SpiderJob {
	public abstract SpiderJob init(Element jobData);

	@Override
	public Object collect(org.jsoup.nodes.Element element) {
		return null;
	}

	@Override
	public Object collectFromList(List<org.jsoup.nodes.Element> elements) {
		return null;
	}


	@Override
	public List<org.jsoup.nodes.Element> findAll(List<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public List<org.jsoup.nodes.Element> filter(List<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public List<org.jsoup.nodes.Element> map(List<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public List<org.jsoup.nodes.Element> peek(List<org.jsoup.nodes.Element> elements) {
		return null;
	}
}
