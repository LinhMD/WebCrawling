package com.swd391.assi2.team2.spider;

import com.swd391.assi2.team2.gui.SpiderFrame;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Spider implements SpiderJob {
	public String id;

	public List<SpiderJob> spiderJobs;

	public SpiderLog spiderLog;

	public SpiderFrame frame;

	public Spider(List<SpiderJob> spiderJobs) {
		this.spiderJobs = spiderJobs;
	}

	public Thread thread;

	public Spider() {
		this.spiderJobs = new ArrayList<>();
		this.thread = new Thread(this);
	}


	public void setSpiderJobs(List<SpiderJob> spiderJobs) {
		this.spiderJobs = spiderJobs;
	}

	@Override
	public String toString() {
		return id;
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
