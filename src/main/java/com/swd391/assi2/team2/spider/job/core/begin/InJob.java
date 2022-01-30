package com.swd391.assi2.team2.spider.job.core.begin;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public interface InJob extends SpiderJob {

	//the_in_part Start
	/**
	 * @why: Everything need to start at somewhere
	 * */
	ArrayList<Element> start(ArrayList<Element> elements) throws IOException, NoSuchMethodException;

	Element start() throws IOException;

	@Override
	default MethodCall getMethodCall() {
		return MethodCall.Start;
	}

	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Start};
	}
}
