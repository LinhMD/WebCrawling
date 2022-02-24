package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.core.center.FilterJob;
import com.swd391.assi2.team2.spider.job.core.center.FindJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Select implements  FindJob {
	public StringBuilder spiderLog;

	public String cssSelect;

	public Select(String cssSelect) {
		this.cssSelect = cssSelect;
	}

	public Select() {
	}

	public String getCssSelect() {
		return cssSelect;
	}

	public void setCssSelect(String cssSelect) {
		this.cssSelect = cssSelect;
	}



	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) throws IOException, Exception {
		ArrayList<Element> result = new ArrayList<>();
		for (Element element : elements) {
			try {
				result.addAll(element.select(cssSelect));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Element findOne(ArrayList<Element> elements) throws Exception {
		return FindJob.super.findOne(elements);
	}

	@Override
	public ArrayList<Element> findAll(Element element) throws IOException, Exception {
		return new ArrayList<>(element.select(cssSelect));
	}

	@Override
	public Element findOne(Element element) throws Exception {
		return element.select(cssSelect).first();
	}


}
