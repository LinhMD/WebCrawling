package com.swd391.assi2.team2.spider.job.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public interface SpiderJob extends Runnable{

	enum MethodCall{
		Start("start"),
		Collect("collect"),
		CollectFromList("collectFromList"),
		Collects("collects"),
		Filter("filter"),
		Evaluate("Evaluate"),
		FindAll("findAll"),
		FindOne("findOne"),
		Map("map"),
		Peek("peek"),
		Run("run");
		final String methodName;
		MethodCall(String methodName){
			this.methodName = methodName;
		}

		public String getMethodName() {
			return methodName;
		}
	}

	/**
	 * @what: return the method at define at .config.xml file by tag <method></method>
	 * @why: to automatically call the config method
	 * */
	default MethodCall getMethodCall(){
		return MethodCall.Run;
	}

	/**
	* @what:
	* */
	MethodCall[] getImplementMethods();

	/**
	* @what: This method will automatically call the function that return from getMethodCall()
	* @why: to do the job, that why
	* @throws NoSuchMethodException when there is no support method or objectIn is wrong type
	* @throws InvocationTargetException  should not be throw if throw god know why
	* @throws IllegalAccessException when there is a private things around, every thing should be public cus fuk encapsulation
	* */
	default Object run(Object objectIn) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if(Arrays.stream(this.getImplementMethods()).noneMatch(m -> m == this.getMethodCall()))
			throw new NoSuchMethodException("Unsupported method " + this.getMethodCall());
		Method method = this.getClass().getMethod(this.getMethodCall().getMethodName(), objectIn.getClass());
		return method.invoke(this, objectIn);
	}

	@Override
	default void run() {
		try {
			this.run(null);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
