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
	public Object collectFromList(List<Element> elements) {
		return null;
	}

	@Override
	public List<Object> collects(List<Element> elements) {
		return SpiderJob.super.collects(elements);
	}

	@Override
	public List<Element> findAll(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> filter(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> map(List<Element> elements) {
		return null;
	}

	@Override
	public List<Element> peek(List<Element> elements) {
		return null;
	}
}
