package com.swd391.assi2.team2.spider.job.complex;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexJob implements SpiderJob {
	public abstract SpiderJob init(Element jobData);

	@Override
	public Object collect(org.jsoup.nodes.Element element) {
		return null;
	}

	@Override
	public Object collectFromList(ArrayList<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public ArrayList<org.jsoup.nodes.Element> findAll(ArrayList<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public ArrayList<org.jsoup.nodes.Element> filter(ArrayList<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public ArrayList<org.jsoup.nodes.Element> map(ArrayList<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public ArrayList<org.jsoup.nodes.Element> peek(ArrayList<org.jsoup.nodes.Element> elements) {
		return null;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}
}
