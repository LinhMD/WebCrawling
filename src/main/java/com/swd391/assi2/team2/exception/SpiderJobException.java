package com.swd391.assi2.team2.exception;

import com.swd391.assi2.team2.spider.job.SpiderJob;

public class SpiderJobException extends Exception{
	private SpiderJob.MethodCall methodCall;
	private Class<? extends SpiderJob> jobClass;
	private  String message;

	public SpiderJobException() {
	}

	public SpiderJobException(SpiderJob.MethodCall methodCall, Class<? extends SpiderJob> jobClass, String message){
		super(message);
		this.jobClass = jobClass;
		this.methodCall = methodCall;
	}

	public SpiderJob.MethodCall getMethodCall() {
		return methodCall;
	}

	public void setMethodCall(SpiderJob.MethodCall methodCall) {
		this.methodCall = methodCall;
	}

	public Class<? extends SpiderJob> getJobClass() {
		return jobClass;
	}

	public void setJobClass(Class<SpiderJob> jobClass) {
		this.jobClass = jobClass;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}





}
