package com.swd391.assi2.team2.spider.job.imp;


import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.core.center.FilterJob;
import com.swd391.assi2.team2.spider.job.core.center.FindJob;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.*;

public class Contains implements FindJob, FilterJob {
	public String contains;
	public String method;

	public SpiderLog LOGGER;

	public Contains() {
	}

	public String getContains() {
		return contains;
	}

	public void setContains(String contains) {
		this.contains = contains;
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
		ArrayList<Element> result = new ArrayList<>();

		for (Element element : elements) {
			result.addAll(element.getElementsContainingText(this.contains));
		}
		return result;
	}

	@Override
	public Element findOne(ArrayList<Element> elements) throws Exception {
		return FindJob.super.findOne(elements);
	}

	@Override
	public ArrayList<Element> findAll(Element element) throws IOException, Exception {
		return new ArrayList<>(element.getElementsContainingText(contains));
	}

	@Override
	public Element findOne(Element element) throws Exception {
		return element.getElementsContainingText(contains).last();
	}

	@Override
	public ArrayList<Element> filter(ArrayList<Element> elements) {
		return elements.stream()
				.filter(e -> e.text().contains(contains))
				.collect(Collectors.toCollection(ArrayList::new));

	}

	@Override
	public ArrayList<Element> filter(Element element) {
		Elements elements = element.getElementsContainingText(contains);
		return new ArrayList<>(elements);
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{FindAll, FindOne, Filter};
	}



	@Override
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return Filter;
	}
}
