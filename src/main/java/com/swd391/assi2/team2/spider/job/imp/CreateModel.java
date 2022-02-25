package com.swd391.assi2.team2.spider.job.imp;
import com.swd391.assi2.team2.data.DataModel;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreateModel extends ComplexJob implements OutJob {

	public Class<? extends DataModel> ModelClass;

	List<SpiderJob> jobList = new ArrayList<>();
	public SpiderLog LOGGER;

	public CreateModel() {
	}

	public CreateModel(String method, Class<? extends DataModel> modelClass, List<SpiderJob> outJobs) {
		this.method = method;
		this.ModelClass = modelClass;
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
			System.out.println(dataModel);
			return dataModel;
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object collect(ArrayList<Element> elements) {
		return elements.stream()
				.map(this::collect)
				.filter(Objects::nonNull)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public SpiderJob initData(org.jdom2.Element element, JobFactory jobFactory, Spider spider) {
		LOGGER = spider.spiderLog;
		try {
			method = element.getChildText("method");
			ModelClass = (Class<? extends DataModel>) Class.forName("com.swd391.assi2.team2.data." + element.getChildText("ModelClass"));
			this.jobList.addAll(jobFactory.getJobs(element.getChild("SpiderJobs").getChildren(), spider));

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
}
