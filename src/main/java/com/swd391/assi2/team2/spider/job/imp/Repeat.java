package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Repeat extends ComplexJob implements SpiderJob {

	public int interval;

	List<SpiderJob> jobList = new ArrayList<>();

	public Repeat() {
	}

	@Override
	public Object run(Object objectIn) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object result = null;
		for (int i = 0; i < interval; i++) {
			result = objectIn;
			for (SpiderJob spiderJob : jobList) {
				try {
					result = spiderJob.run(result);
				} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public MethodCall getMethodCall() {
		return super.getMethodCall();
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}

	@Override
	public SpiderJob initData(org.jdom2.Element element, JobFactory jobFactory) {
		try {
			method = element.getChildText("method");
			interval = Integer.parseInt(element.getChildText("interval"));
			this.jobList.addAll(jobFactory.getJobs(element.getChild("SpiderJobs").getChildren()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
}
