package com.swd391.assi2.team2.utils;

import java.util.TreeSet;

public class VisitedUrl extends TreeSet<String> {
	private static final VisitedUrl INSTANCE = new VisitedUrl();

	public static VisitedUrl getInstance(){
		return INSTANCE;
	}
}
