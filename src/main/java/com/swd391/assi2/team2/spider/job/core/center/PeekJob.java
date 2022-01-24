package com.swd391.assi2.team2.spider.job.core.center;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public interface PeekJob extends SpiderJob {

	/**
	 * @param elements : list need to do something
	 * @return a new different list with different type of elements
	 * */
	ArrayList<Element> peek(ArrayList<Element> elements);

	@Override
	default MethodCall getMethodCall() {
		return MethodCall.Collect;
	}

	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Collect, MethodCall.CollectFromList, MethodCall.Collects};
	}
}
