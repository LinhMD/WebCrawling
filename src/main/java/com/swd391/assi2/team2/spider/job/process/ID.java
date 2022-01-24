package com.swd391.assi2.team2.spider.job.process;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.swd391.assi2.team2.spider.job.SpiderJob.MethodCall.Filter;
import static com.swd391.assi2.team2.spider.job.SpiderJob.MethodCall.FindAll;

public class ID implements ProcessJob {

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
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return elements.stream()
				.filter(element -> element.id().equals(this.id))
				.collect(Collectors.toCollection(ArrayList::new));
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
