package com.swd391.assi2.team2.spider.job.process;

import com.swd391.assi2.team2.spider.job.SpiderJob;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public interface  ProcessJob extends SpiderJob {

// the_chain_stream_combo/ process Start
	/**
	 * @param elements : list of element need to find <jobName/> on
	 * @return a different list
	 * */
	ArrayList<Element> findAll(ArrayList<Element> elements) throws IOException, Exception;

	/**
	 * @param elements : list need to be filter out by <jobName/> some element
	 * @return the same list but with fewer elements
	 * */
	ArrayList<Element> filter(ArrayList<Element> elements);

	/**
	 * @param elements : list need to map to <jobName/>
	 * @return a new different list with different type of elements
	 * */
	ArrayList<Element> map(ArrayList<Element> elements);

	/**
	 * @param elements : list need to do something
	 * @return a new different list with different type of elements
	 * */
	ArrayList<Element> peek(ArrayList<Element> elements);



}
