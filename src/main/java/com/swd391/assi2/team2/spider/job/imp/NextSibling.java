package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class NextSibling implements OutJob {
	@Override
	public Object collect(Element element) {
		return element.nextElementSibling();
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public MethodCall getMethodCall() {
		return MethodCall.Collect;
	}
}
