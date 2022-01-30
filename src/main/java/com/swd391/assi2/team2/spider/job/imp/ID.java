package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.core.center.FilterJob;
import com.swd391.assi2.team2.spider.job.core.center.FindJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.Filter;
import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.FindAll;

public class ID implements FindJob, FilterJob {

	public String id;
	public String method;
	public ID() {
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ID(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IDJob{" +
				"id='" + id + '\'' +
				'}';
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
		ArrayList<Element> result = new ArrayList<>();

		elements.forEach(element -> result.add(element.getElementById(this.id)));
		return result;
	}

	@Override
	public ArrayList<Element> findAll(Element element) throws IOException, Exception {
		return null;
	}

	@Override
	public Element findOne(Element element) throws Exception {
		return FindJob.super.findOne(element);
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return elements.stream()
				.filter(element -> element.id().equals(this.id))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<Element> filter(Element element) {
		Element idElement = element.getElementById(this.id);
		ArrayList<Element> list = new ArrayList<>();
		list.add(idElement);
		return list;
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
		return FindAll;
	}
}
