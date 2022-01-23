package com.swd391.assi2.team2.spider.job.single;


import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ID extends SingleJob {

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
//		List<Element> result = new ArrayList<>();
//		elements.forEach(element -> result.add(element.getElementById(this.id)));
//		return result;
		System.out.println("find ALl call");
		return null;
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return elements.stream().filter(element -> element.id().equals(this.id)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.FindAll, MethodCall.Filter};
	}


}
