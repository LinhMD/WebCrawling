package com.swd391.assi2.team2.spider.job.multiple;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleJobs implements SpiderJob {
	List<SpiderJob> jobList = new ArrayList<>();

	public MultipleJobs() {
	}

	public void addJob(SpiderJob job){
		jobList.add(job);
	}

	@Override
	public String toString() {
		return "MultipleJobs{" +
				"jobList=" + jobList +
				'}';
	}

	@Override
	public Object collect(Element element) {
		return null;
	}

	@Override
	public Object collectFromList(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public ArrayList<Element> findAll(ArrayList<Element> elements) {
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
		return new MethodCall[0];
	}
}
