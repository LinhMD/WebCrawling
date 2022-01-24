package com.swd391.assi2.team2.spider.job.result;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public interface  OutJob extends SpiderJob {
	/**
	 * @why: the element need to be converted into DTO model
	 * @what: take the element and convert into Object
	 * @param element element tobe convert
	 * @return a DTO model object
	 * */
	Object collect(Element element);

	/**
	 * @why: sometimes they don't want to play nice
	 *
	 * */
	Object collectFromList(ArrayList<Element> elements);

	/**
	 *  @what: same as collect but list
	 * */
	default ArrayList<Object> collects(ArrayList<Element>elements){
		return elements.stream()
				.map(this::collect)
				.collect(Collectors.toCollection(ArrayList::new));
	}


	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Collect, MethodCall.CollectFromList, MethodCall.Collects};
	}
}
