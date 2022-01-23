package com.swd391.assi2.team2.spider.job.single;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

//please note that all class extend this must have all the field type String
public abstract class SingleJob implements SpiderJob {

	@Override
	public Object collect(Element element) {
		return null;
	}

	@Override
	public Object collectFromList(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
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
	public MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}
}
