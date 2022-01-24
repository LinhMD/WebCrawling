package com.swd391.assi2.team2.spider.job.process;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Print extends ProcessJob {
	public String method;
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
		System.out.println(elements);
		return elements;
	}

	@Override
	public MethodCall getMethodCall() {
		return MethodCall.Peek;
	}
}
