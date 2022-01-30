package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.model.DataModel;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CreateModel extends ComplexJob implements OutJob {
	JobFactory jobFactory = new JobFactory();

	public Class<? extends DataModel> ModelClass;

	List<SpiderJob> jobList = new ArrayList<>();

	public CreateModel() {
	}

	public CreateModel(JobFactory jobFactory, String method, Class<? extends DataModel> modelClass, List<SpiderJob> outJobs) {
		this.jobFactory = jobFactory;
		this.method = method;
		ModelClass = modelClass;
		this.jobList = outJobs;
	}

	@Override
	public Object collect(Element element) {
		try {
			Constructor<? extends DataModel> constructor = ModelClass.getConstructor();
			DataModel dataModel = constructor.newInstance();
			for (SpiderJob spiderJob : jobList) {
				try {
					Object result = spiderJob.run(element);
					String fieldName = ((Assign) spiderJob).field;
					Field field = ModelClass.getField(fieldName);
					if(field.getType().isAssignableFrom(String.class)){
						field.set(dataModel, result.toString());
					}else {
						field.set(dataModel, result);
					}

				} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
					e.printStackTrace();
				}
			}

			return dataModel;
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		return null;
	}

	@Override
	public SpiderJob initData(org.jdom2.Element element) {
		try {
			method = element.getChildText("method");
			ModelClass = (Class<? extends DataModel>) Class.forName("com.swd391.assi2.team2.model." + element.getChildText("ModelClass"));
			this.jobList.addAll(jobFactory.getJobs(element.getChild("SpiderJobs").getChildren()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
}
