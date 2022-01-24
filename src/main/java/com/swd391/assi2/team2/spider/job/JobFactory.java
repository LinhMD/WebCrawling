package com.swd391.assi2.team2.spider.job;

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
		initJobData(objectData, spiderJobClass, jobInstance);
	}
	/*
	 * the reflection magic
	 * - note that every field in the class must be String
	 * - or else
	 * */
	public  SpiderJob initJobData(Element objectData, Class<?> spiderJobClass, Object job) throws  IllegalAccessException {
		Field[] fields = spiderJobClass.getDeclaredFields();
		for (Field field : fields) {
			Element nodeField = objectData.getChild(field.getName());
			String value = nodeField.getValue();
			if(value != null)
				field.set(job, value.trim());
		}
		return (SpiderJob)job;
	}
//
//	private  void getMultipleJobs(Element objectData, MultipleJobs jobInstance) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		List<Element> children = objectData.getChildren();
//		for (Element childJobData : children) {
//			Class<?> childJobClass = Class.forName(SPIDER_JOB_PACKAGE_NAME + childJobData.getName());
//			SpiderJob childJobInstance = (SpiderJob) childJobClass.newInstance();
//			initData(childJobData, childJobClass, childJobInstance);
//			jobInstance.addJob(getSingleJob(childJobData, childJobClass, childJobInstance));
//		}
//	}


}
