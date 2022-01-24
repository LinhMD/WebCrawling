package com.swd391.assi2.team2.spider.job.imp;


import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Link implements OutJob {


	@Override
	public Object collect(Element element) {
		return new ArrayList<>(element.getElementsByTag("a"));
	}

	@Override
	public Object collectFromList(ArrayList<Element> elements) {
		ArrayList<Element> result = new ArrayList<>();
		for (Element element : elements) {
			result.addAll(element.getElementsByTag("a"));
		}
		return result;
	}
}
