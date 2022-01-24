package com.swd391.assi2.team2.spider.job.process;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import static com.swd391.assi2.team2.spider.job.SpiderJob.MethodCall.Peek;

public class Print implements ProcessJob {
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
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return Peek;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}
}
