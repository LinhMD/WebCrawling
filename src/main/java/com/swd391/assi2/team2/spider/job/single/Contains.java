package com.swd391.assi2.team2.spider.job.single;


public class Contains extends SingleJob {
	public String contains;

	public Contains() {
	}

	public String getContains() {
		return contains;
	}

	public void setContains(String contains) {
		this.contains = contains;
	}


	@Override
	public MethodCall[] getImplementMethods() {
		return new MethodCall[0];
	}
}
