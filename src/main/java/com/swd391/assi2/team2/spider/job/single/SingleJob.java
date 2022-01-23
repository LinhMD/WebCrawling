package com.swd391.assi2.team2.spider.job.single;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.util.List;

//please note that all class extend this must have all the field type String
public abstract class SingleJob implements SpiderJob {

	@Override
	public Object collect(Element element) {
		return null;
	}

	@Override
	public Object collectFromList(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> findAll(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> filter(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> map(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> peek(List<Element> elements) {
		return null;
	}
}
