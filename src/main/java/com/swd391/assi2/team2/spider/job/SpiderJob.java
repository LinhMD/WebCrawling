package com.swd391.assi2.team2.spider.job;

import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public interface SpiderJob{

	enum MethodCall{
		Start("start"),
		Collect("collect"),
		CollectFromList("collectFromList"),
		Collects("collects"),
		Filter("filter"),
		FindAll("findAll"),
		Map("map"),
		Peek("peek");
		final String methodName;
		MethodCall(String methodName){
			this.methodName = methodName;
		}

		public String getMethodName() {
			return methodName;
		}
	}

	//the_main_part Start
	/**
	 * @why: Everything need to start at somewhere
	 * */
	default void start(){

	}

	// the_chain_stream_combo Start
	/**
	 * @param elements : list of element need to find <jobName/> on
	 * @return a different list
	 * */
	ArrayList<Element> findAll(ArrayList<Element> elements);

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
	// the_chain_stream_combo End

	/**
	 * @why: the element need to be converted into DTO model
	 * @what: take the element and convert into Object
	 * @param element element tobe convert
	 * @return a DTO model object
	 * */
	Object collect(Element element);

	/**
	 * @why: sometimes they don't want to play nice
	 *
	 * */
	Object collectFromList(ArrayList<Element> elements);

	/**
	 *  @what: same as collect but list
	 * */
	default ArrayList<Object> collects(ArrayList<Element>elements){
		return elements.stream()
				.map(this::collect)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	//the_main_part End

	/**
	 * @what: return the method at define at .config.xml file by tag <method></method>
	 * @why: to automatically call the config method
	 * */
	default MethodCall getMethodCall(){
		return MethodCall.FindAll;
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
}
