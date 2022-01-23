package com.swd391.assi2.team2.spider.job.single;


import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ID extends SingleJob {
	public String id;

	public ID() {
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
	public List<Element> findAll(List<Element> elements) {

		List<Element> result = new ArrayList<>();
		elements.forEach(element -> result.add(element.getElementById(this.id)));
		return result;
	}

	@Override
	public List<Element> filter(List<Element> elements) {
		return elements.stream().filter(element -> element.id().equals(this.id)).collect(Collectors.toList());
	}
}
