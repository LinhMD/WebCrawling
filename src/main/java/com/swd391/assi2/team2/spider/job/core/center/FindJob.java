package com.swd391.assi2.team2.spider.job.core.center;

import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public interface FindJob extends SpiderJob {
	/**
	 * @param elements : list of element need to find <jobName/> on
	 * @return a different list
	 * */
	ArrayList<Element> findAll(ArrayList<Element> elements) throws IOException, Exception;

}
