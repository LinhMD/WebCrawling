package com.swd391.assi2.team2.spider.job;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jdom2.Element;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JobFactory {
	public static String SPIDER_JOB_PACKAGE_NAME = "com.swd391.assi2.team2.spider.job.imp";
	public HashMap<String, SpiderJob> jobsMap = new HashMap<>();
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
			String jobID = objectData.getAttributeValue("id");
			if(jobID != null && !jobID.isBlank())
				jobsMap.put(jobID, jobInstance);
			jobList.add(jobInstance);

		}
		return jobList;
	}

	private  SpiderJob initData(Element objectData, Class<?> spiderJobClass, SpiderJob jobInstance) throws  IllegalAccessException {
		Field[] fields = spiderJobClass.getDeclaredFields();
		for (Field field : fields) {
			Element nodeField = objectData.getChild(field.getName());
			String value = nodeField.getValue();
			if(value != null)
				field.set(jobInstance, value.trim());
		}
		return jobInstance;
	}



}
