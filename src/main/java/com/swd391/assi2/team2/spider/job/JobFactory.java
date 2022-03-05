package com.swd391.assi2.team2.spider.job;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.imp.ComplexJob;
import org.jdom2.Element;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JobFactory {
	public static String SPIDER_JOB_PACKAGE_NAME = "com.swd391.assi2.team2.spider.job.imp.";
	public static HashMap<String, SpiderJob> JOBS_MAP = new HashMap<>();

	/*
	* start at this
	* need to create jobs
	* */
	public  List<SpiderJob> getJobs(List<Element> jobsData, Spider spider)  {
		List<SpiderJob> jobList = new ArrayList<>();
		for (Element objectData : jobsData) {
			try{
				String name = objectData.getName();
				Class<?> spiderJobClass = Class.forName(SPIDER_JOB_PACKAGE_NAME + name);

				SpiderJob jobInstance = (SpiderJob) spiderJobClass.newInstance();
				if(jobInstance instanceof ComplexJob){
					((ComplexJob) jobInstance).initData(objectData, this, spider);
				}else
					initData(objectData, spiderJobClass, jobInstance, spider);
				String jobID = objectData.getAttributeValue("id");
				if(jobID != null && !jobID.isBlank())
					JOBS_MAP.put(jobID, jobInstance);
				jobList.add(jobInstance);

			}catch (Exception e){
				spider.spiderLog.error(e.getMessage(), this);
			}

		}
		return jobList;
	}

	public   SpiderJob initData(@NotNull Element objectData, Class<?> spiderJobClass, SpiderJob jobInstance, Spider spider) throws  IllegalAccessException {
		Field[] fields = spiderJobClass.getDeclaredFields();
		for (Field field : fields) {
			if(field.getType().isAssignableFrom(SpiderLog.class)){
				field.set(jobInstance, spider.spiderLog);
				continue;
			}

			Element nodeField = objectData.getChild(field.getName());
			String value = nodeField.getValue();
			if(value != null){
				field.set(jobInstance, value.trim());
			}

		}
		return jobInstance;
	}


}
