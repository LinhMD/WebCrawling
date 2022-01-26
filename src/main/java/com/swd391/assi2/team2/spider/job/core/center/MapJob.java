package com.swd391.assi2.team2.spider.job.core.center;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public interface MapJob extends SpiderJob {
	/**
	 * @param elements : list need to map to <jobName/>
	 * @return a new different list with different type of elements
	 * */
	ArrayList<Element> map(ArrayList<Element> elements);

	@Override
	default MethodCall getMethodCall() {
		return MethodCall.Map;
	}

	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Map};
	}

}
