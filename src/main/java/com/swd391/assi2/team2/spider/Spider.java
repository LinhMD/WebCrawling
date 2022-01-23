package com.swd391.assi2.team2.spider;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Spider {
	List<SpiderJob> spiderJobs;

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

	public void run(){
		for (SpiderJob spiderJob : this.spiderJobs) {
			try {
				spiderJob.run(new ArrayList<Element>());
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


}
