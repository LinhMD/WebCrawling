package com.swd391.assi2.team2.spider.job.core.center;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public interface FilterJob extends SpiderJob {
	/**
	 * @param elements : list need to be filter out by <jobName/> some element
	 * @return the same list but with fewer elements
	 * */
	ArrayList<Element> filter(ArrayList<Element> elements);

	/**
	 * @param element : the children of which need to be filter out by <jobName/>
	 * @return a new filtered list (if only 1 element can be found return a list of 1 element)
	 * */
	ArrayList<Element> filter(Element element);


	@Override
	default MethodCall getMethodCall() {
		return MethodCall.Filter;
	}

	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Filter};
	}
}
