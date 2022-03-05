package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.data.DataModel;
import com.swd391.assi2.team2.data.MapModel;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderLog;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.spider.job.core.end.OutJob;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreateMapModel extends ComplexJob implements OutJob {

	public List<String> Keys = new ArrayList<>();
	public HashMap<String, Assign> JobList = new HashMap<>();
	public String modelType = "Model";
	public SpiderLog LOGGER;

	public CreateMapModel() {
	}

	public CreateMapModel(String method, Class<? extends DataModel> modelClass, HashMap<String, Assign> outJobs) {
		this.method = method;
		this.JobList = outJobs;
	}

	@Override
	public Object collect(Element element) {
		MapModel model = new MapModel();
		Keys.forEach(k -> model.put(k, null));
		for (String key : Keys) {
			try {
				Assign assign = JobList.get(key);
				Object result = assign.run(element);
				model.put(assign.field, result);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NullPointerException e) {
				LOGGER.error(e.getMessage(), this);
			}
		}
		StringBuilder info = new StringBuilder();
		for (String key : model.keySet()) {
			info.append(key).append(": ").append(model.get(key));
		}
		LOGGER.info(info.toString(), this);
		return model;
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
		this.LOGGER = spider.spiderLog;
		this.modelType = element.getChildText("modelType");
		this.method = element.getChildText("method");
		element.getChild("Keys").getChildren().stream()
				.map(org.jdom2.Element::getText)
				.peek(k -> this.JobList.put(k, null))
				.forEach(Keys::add);
		List<SpiderJob> spiderJobs = jobFactory.getJobs(element.getChild("SpiderJobs").getChildren(), spider);
		spiderJobs.forEach(s ->{
			if(s instanceof Assign && this.JobList.containsKey(((Assign) s).field)){
				this.JobList.put(((Assign) s).field, (Assign) s);
			}
		});
		return this;
	}
}
