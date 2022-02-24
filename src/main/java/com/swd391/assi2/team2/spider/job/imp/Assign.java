package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Assign extends ComplexJob implements OutJob {
	public String field;
	public List<SpiderJob> jobList = new ArrayList<>();

	public StringBuilder spiderLog;

	public Assign() {
	}

	public Assign(String field, List<SpiderJob> jobList) {
		this.field = field;
		this.jobList = jobList;
	}

	@Override
	public Object collect(Element element) {
		Object result = element;
		for (SpiderJob spiderJob : jobList) {
			try {
				result = spiderJob.run(result);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		Object result = elements;
		for (SpiderJob spiderJob : jobList) {
			try {
				result = spiderJob.run(result);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public MethodCall getMethodCall() {
		return MethodCall.Collect;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Collect};
	}

	@Override
	public SpiderJob initData(org.jdom2.Element element, JobFactory jobFactory, Spider spider) {
		field = element.getChildText("field");
		try {
			this.jobList.addAll(jobFactory.getJobs(element.getChild("SpiderJobs").getChildren(), spider));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
}
