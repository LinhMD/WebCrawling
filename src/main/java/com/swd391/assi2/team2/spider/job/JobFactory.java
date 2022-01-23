package com.swd391.assi2.team2.spider.job;

import com.swd391.assi2.team2.spider.job.complex.ComplexJob;
import com.swd391.assi2.team2.spider.job.multiple.MultipleJobs;
import com.swd391.assi2.team2.spider.job.single.SingleJob;
import org.jdom2.Element;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobFactory {
	public static String SPIDER_JOB_PACKAGE_NAME = "com.swd391.assi2.team2.spider.job.";

	/*
	* start at this
	* need to create jobs
	*
	* */
	public  List<SpiderJob> getJobs(List<Element> jobsData) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<SpiderJob> jobList = new ArrayList<>();
		for (Element objectData : jobsData) {
			String name = objectData.getName();
			Class<?> spiderJobClass = Class.forName(SPIDER_JOB_PACKAGE_NAME + name);

			SpiderJob jobInstance = (SpiderJob) spiderJobClass.newInstance();
			initData(objectData, spiderJobClass, jobInstance);
			jobList.add(jobInstance);

		}
		return jobList;
	}

	private  void initData(Element objectData, Class<?> spiderJobClass, SpiderJob jobInstance) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(jobInstance instanceof SingleJob){
			getSingleJob(objectData, spiderJobClass, jobInstance);
		}else if(jobInstance instanceof MultipleJobs) {
			getMultipleJobs(objectData, (MultipleJobs) jobInstance);
		}else if(jobInstance instanceof ComplexJob){
			((ComplexJob) jobInstance).init(objectData);
		}
	}
	/*
	 * the reflection magic
	 * - note that every field in the class must be String
	 * - or else
	 * */
	public  SpiderJob getSingleJob(Element objectData, Class<?> spiderJobClass, Object job) throws  IllegalAccessException {
		Field[] fields = spiderJobClass.getDeclaredFields();
		for (Field field : fields) {
			Element nodeField = objectData.getChild(field.getName());
			String value = nodeField.getValue();
			if(value != null)
				field.set(job, value.trim());
		}
		return (SpiderJob)job;
	}

	private  void getMultipleJobs(Element objectData, MultipleJobs jobInstance) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<Element> children = objectData.getChildren();
		for (Element childJobData : children) {
			Class<?> childJobClass = Class.forName(SPIDER_JOB_PACKAGE_NAME + childJobData.getName());
			SpiderJob childJobInstance = (SpiderJob) childJobClass.newInstance();
			initData(childJobData, childJobClass, childJobInstance);
			jobInstance.addJob(getSingleJob(childJobData, childJobClass, childJobInstance));
		}
	}


}
