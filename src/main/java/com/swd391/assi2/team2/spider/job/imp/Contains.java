package com.swd391.assi2.team2.spider.job.imp;


import com.swd391.assi2.team2.spider.job.core.center.FilterJob;
import com.swd391.assi2.team2.spider.job.core.center.FindJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.Filter;
import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.FindAll;

public class Contains implements FindJob, FilterJob {
	public String contains;
	public String method;
	public Contains() {
	}

	public String getContains() {
		return contains;
	}

	public void setContains(String contains) {
		this.contains = contains;
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
		ArrayList<Element> result = new ArrayList<>();

		for (Element element : elements) {
			result.addAll(element.getElementsContainingText(this.contains));
		}
		return result;
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return elements.stream()
				.filter(e -> e.val().contains(contains))
				.collect(Collectors.toCollection(ArrayList::new));

	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{FindAll, Filter};
	}

	@Override
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return Filter;
	}
}
