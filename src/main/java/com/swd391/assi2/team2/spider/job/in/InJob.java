package com.swd391.assi2.team2.spider.job.in;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class InJob implements SpiderJob {
	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) throws  Exception {
		return null;
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
	public Object collect(Element element) {
		return null;
	}

	@Override
	public Object collectFromList(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}
}
