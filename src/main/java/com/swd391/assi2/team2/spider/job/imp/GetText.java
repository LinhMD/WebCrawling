package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GetText implements OutJob {
	public SpiderLog LOGGER;

	@Override
	public Object collect(Element element) {
		return element.text() + "\n";
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		StringBuilder builder = new StringBuilder();
		for (Element element : elements) {
			builder.append(element.text()).append("\n");
		}
		return builder.toString();
	}

	@Override
	public ArrayList<Object> collects(ArrayList<Element> elements) {
		return elements.stream().map(Element::text).map(s -> s + "\n").collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public MethodCall getMethodCall() {
		return MethodCall.Collect;
	}

}
