package com.swd391.assi2.team2.spider.job.core.end;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

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
	Object collect(ArrayList<Element> elements);

	/**
	 *  @what: same as collect but list
	 * */
	default ArrayList<Object> collects(ArrayList<Element>elements){
		return elements.stream()
				.map(this::collect)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	default MethodCall getMethodCall() {
		return MethodCall.Collect;
	}

	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Collect, MethodCall.Collects};
	}
}
