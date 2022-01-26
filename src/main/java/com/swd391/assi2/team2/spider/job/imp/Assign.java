package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Assign extends ComplexJob implements OutJob {

	JobFactory jobFactory = new JobFactory();

	public String field;
	public List<SpiderJob> jobList = new ArrayList<>();

	public Assign() {
	}

	public Assign(JobFactory jobFactory, String field, List<SpiderJob> jobList) {
		this.jobFactory = jobFactory;
		this.field = field;
		this.jobList = jobList;
	}

	@Override
	public Object collect(Element element) {

		return null;
	}

	@Override
	public Object collectFromList(ArrayList<Element> elements) {
		Object result = elements;
		for (SpiderJob spiderJob : jobList) {
			try {
				result = spiderJob.run(result);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result != null ? result.toString() : null;
	}

	@Override
	public MethodCall getMethodCall() {
		return MethodCall.CollectFromList;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.CollectFromList};
	}

	@Override
	public SpiderJob initData(org.jdom2.Element element) {
		field = element.getChildText("field");
		try {
			this.jobList.addAll(jobFactory.getJobs(element.getChild("SpiderJobs").getChildren()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
}
