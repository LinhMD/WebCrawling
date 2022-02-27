package com.swd391.assi2.team2.spider.job.imp;


import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.center.FilterJob;
import com.swd391.assi2.team2.spider.job.core.center.FindJob;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ClassName implements FindJob, FilterJob {
	public String className;
	public String method;
	public SpiderLog LOGGER;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ClassName() {
	}

	public ClassName(String className) {
		this.className = className;
	}



	@Override
	public String toString() {
		return "ClassNameJob{" +
				"className='" + className + '\'' +
				'}';
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Filter, MethodCall.FindAll, MethodCall.FindOne};
	}



	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
		ArrayList<Element> result = new ArrayList<>();
		for (Element e : elements) {
			result.addAll(e.getElementsByClass(this.className));
		}
		this.LOGGER.info("Total found: " + result.size(), this);
		return result;
	}

	@Override
	public ArrayList<Element> findAll(Element element) throws IOException, Exception {
		ArrayList<Element> result = new ArrayList<>(element.getElementsByClass(className));
		this.LOGGER.info("Total found: " + result.size(), this);
		return result;
	}



	@Override
	public Element findOne(Element element) throws Exception {
		return element.getElementsByClass(className).first();
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		this.LOGGER.info("Run filter class name: " + this.className , this);
		return elements.stream()
				.filter(e -> e.className().equals(this.className))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<Element> filter(Element element) {
		this.LOGGER.info("Run filter class name: " + this.className , this);
		return new ArrayList<>(element.getElementsByClass(className));
	}


	@Override
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return MethodCall.FindAll;
	}
}
