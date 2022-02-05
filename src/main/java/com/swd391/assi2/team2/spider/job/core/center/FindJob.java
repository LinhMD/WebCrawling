package com.swd391.assi2.team2.spider.job.core.center;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public interface FindJob extends SpiderJob {
	/**
	 * @param elements : list of element need to find <jobName/> on
	 * @return a different list
	 * */
	ArrayList<Element> findAll(ArrayList<Element> elements) throws IOException, Exception;

	default Element findOne(ArrayList<Element> elements) throws Exception {
		return findAll(elements).get(0);
	}

	ArrayList<Element> findAll(Element element) throws IOException, Exception;

	default Element findOne(Element element) throws Exception {
		return findAll(element).get(0);
	}

	@Override
	default MethodCall getMethodCall() {
		return MethodCall.FindAll;
	}

	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.FindAll};
	}
}
