package com.swd391.assi2.team2.spider.job.process;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ProcessJob implements SpiderJob {

	@Override
	public ArrayList<Element> start(ArrayList<Element>  elements) {
		return null;
	}



	@Override
	public Object collect(Element element) {
		return null;
	}

	@Override
	public Object collectFromList(ArrayList<Element> elements)  {
		return null;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.FindAll, MethodCall.Filter, MethodCall.Map, MethodCall.Peek};
	}
}
