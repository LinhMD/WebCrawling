package com.swd391.assi2.team2.spider.job;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

public interface SpiderJob{

	enum MethodCall{
		Start,
		Collect,
		CollectFromList,
		Collects,
		Filter,
		FindFirst,
		Map,
		Peek
	}

	default void start(){

	}

	Object collect(Element element);
	Object collectFromList(List<Element> elements);
	default List<Object> collects(List<Element>elements){
		return elements.stream().map(this::collect).collect(Collectors.toList());
	}

	List<Element> findAll(List<Element> elements);
	List<Element> filter(List<Element> elements);
	List<Element> map(List<Element> elements);
	List<Element> peek(List<Element> elements);

	default MethodCall getMethodCall(){
		return MethodCall.Start;
	}
	default MethodCall[] getAvailableMethod(){
		return MethodCall.values();
	}

	default Object run(Object o){

		return null;
	}
}
