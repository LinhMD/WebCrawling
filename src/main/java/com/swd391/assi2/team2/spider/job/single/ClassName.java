package com.swd391.assi2.team2.spider.job.single;


public class ClassName extends SingleJob{
	public String className;

	public ClassName() {
	}

	public ClassName(String className) {
		this.className = className;
	}



	@Override
	public String toString() {
		return "ClassNameJob{" +
				"className='" + className + '\'' +
				'}';
	}

}
