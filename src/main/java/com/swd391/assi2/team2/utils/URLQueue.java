package com.swd391.assi2.team2.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class URLQueue extends LinkedList<String> {
	public static HashMap<String, URLQueue> URL_QUEUE_HASHMAP = new HashMap<>();

	public boolean addUnique(String s){
		if(!this.contains(s))
			return this.add(s);
		return false;
	}
}
