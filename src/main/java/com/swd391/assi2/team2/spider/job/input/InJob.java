package com.swd391.assi2.team2.spider.job.input;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public interface InJob extends SpiderJob {

	//the_in_part Start
	/**
	 * @why: Everything need to start at somewhere
	 * */
	ArrayList<Element> start(ArrayList<Element> elements) throws IOException, NoSuchMethodException;
	@Override
	default MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}
}
