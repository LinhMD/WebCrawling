package com.swd391.assi2.team2.spider;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Spider implements SpiderJob {
	public String id;

	public List<SpiderJob> spiderJobs;

	public StringBuilder spiderLog = new StringBuilder();

	public Spider(List<SpiderJob> spiderJobs) {
		this.spiderJobs = spiderJobs;
	}

	public Spider() {
		this.spiderJobs = new ArrayList<>();
	}

	public List<SpiderJob> getSpiderJobs() {
		return spiderJobs;
	}

	public void setSpiderJobs(List<SpiderJob> spiderJobs) {
		this.spiderJobs = spiderJobs;
	}

	@Override
	public String toString() {
		return "Spider{" +
				"spiderJobs=" + spiderJobs +
				'}';
	}


	@Override
	public MethodCall getMethodCall() {
		return MethodCall.Run;
	}

	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[]{MethodCall.Run};
	}

	@Override
	public Object run(Object objectIn) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object result = null;
		for (SpiderJob spiderJob : this.spiderJobs) {
			try {
				result = spiderJob.run(result);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Spider){
			return this.id.equals(((Spider) obj).id);
		}
		return false;
	}
}
