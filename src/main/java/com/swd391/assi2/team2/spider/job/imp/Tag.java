package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.core.center.FilterJob;
import com.swd391.assi2.team2.spider.job.core.center.FindJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.FindAll;

public class Tag implements FindJob, FilterJob {
	public SpiderLog LOGGER;
	public String method;
	public String tagName;
	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> filter(Element element) {
		return null;
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) throws IOException, Exception {
		ArrayList<Element> result = new ArrayList<>();
		for (Element element : elements) {
			result.addAll(element.getElementsByTag(tagName));
		}
		return result;
	}

	@Override
	public ArrayList<Element> findAll(Element element) throws IOException, Exception {
		return new ArrayList<>(element.getElementsByTag(tagName));
	}

	@Override
	public Element findOne(Element element) throws Exception {
		return element.getElementsByTag(tagName).first();
	}


	@Override
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values())
			if (value.getMethodName().equals(method))
				return value;
		return FindAll;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.FindAll};
	}
}
