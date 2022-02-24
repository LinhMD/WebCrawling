package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jdom2.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Repeat extends ComplexJob implements SpiderJob {

	public StringBuilder spiderLog;

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
	public SpiderJob initData(Element element, JobFactory jobFactory, Spider spider) {
		try {
			method = element.getChildText("method");
			interval = Integer.parseInt(element.getChildText("interval"));
			this.jobList.addAll(jobFactory.getJobs(element.getChild("SpiderJobs").getChildren(), spider));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
}
