package com.swd391.assi2.team2.spider.job.process;


import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ClassName implements ProcessJob {
	public String className;
	public String method;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ClassName() {
	}

	public ClassName(String className) {
		this.className = className;
	}



	@Override
	public String toString() {
		return "ClassNameJob{" +
				"className='" + className + '\'' +
				'}';
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Filter, MethodCall.FindAll};
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
		ArrayList<Element> result = new ArrayList<>();
		for (Element e : elements) {
			result.addAll(e.getElementsByClass(this.className));
		}
		System.out.println(result.size());
		return result;
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return elements.stream()
				.filter(e -> e.className().equals(this.className))
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
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return null;
	}
}
