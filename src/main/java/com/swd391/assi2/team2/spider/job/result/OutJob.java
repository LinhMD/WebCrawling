package com.swd391.assi2.team2.spider.job.result;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public abstract class  OutJob implements SpiderJob {
	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) throws IOException, Exception {
		return null;
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> map(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> peek(ArrayList<Element> elements) {
		return null;
	}


	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Collect, MethodCall.CollectFromList, MethodCall.Collects};
	}
}
