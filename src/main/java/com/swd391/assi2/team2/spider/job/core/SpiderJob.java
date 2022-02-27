package com.swd391.assi2.team2.spider.job.core;

import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderLog;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public interface SpiderJob extends Runnable{

	enum MethodCall{
		Start("start"),
		Collect("collect"),
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
		if(objectIn == null){
			Method method = this.getClass().getMethod(this.getMethodCall().getMethodName());
			return method.invoke(this);
		}else {
			Method method = this.getClass().getMethod(this.getMethodCall().getMethodName(), objectIn.getClass());
			return method.invoke(this, objectIn);
		}
	}

	@Override
	default void run() {
		try {
			this.run(null);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	default MutableTreeNode toTreeNode(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.getClass().getSimpleName());
		Field[] fields = this.getClass().getFields();
		for (Field field : fields) {
			try {
				Object value = field.get(this);

				if(value == null) continue;
				if(value instanceof SpiderLog) continue;
				if(value instanceof Thread) continue;

				if(value instanceof List){
					MutableTreeNode child = getNodeFromList(field, (List<?>) value);
					root.add(child);
					continue;
				}

				DefaultMutableTreeNode node = new DefaultMutableTreeNode(field.getName() + ": " + value);
				root.add(node);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return root;
	}

	private MutableTreeNode getNodeFromList(Field field, List<?> value) {
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(field.getName());
		for(Object o : value){
			if(o instanceof SpiderJob){
				child.add(((SpiderJob) o).toTreeNode());
				continue;
			}
			child.add(new DefaultMutableTreeNode(o));
		}

		return child;
	}

}
