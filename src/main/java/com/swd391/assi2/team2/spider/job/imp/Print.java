package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.core.center.PeekJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.Peek;

public class Print implements PeekJob {
	public String method;

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
