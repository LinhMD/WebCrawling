package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jdom2.Element;
import org.springframework.stereotype.Service;

import static com.swd391.assi2.team2.spider.job.core.SpiderJob.MethodCall.Collect;

@Service
public abstract class ComplexJob implements SpiderJob {
	public String method;
	public abstract SpiderJob initData(Element element, JobFactory jobFactory, Spider spider);

	@Override
	public MethodCall getMethodCall() {
		for (MethodCall value : MethodCall.values()) {
			if(value.getMethodName().equals(method))
				return value;
		}
		return Collect;
	}
}