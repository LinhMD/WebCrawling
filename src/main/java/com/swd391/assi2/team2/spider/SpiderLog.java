package com.swd391.assi2.team2.spider;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;

public class SpiderLog {
	public Spider spider;
	public StringBuilder logs;

	public JTextPane pane;

	public SpiderLog(Spider spider, StringBuilder logs, JTextPane pane) {
		this.spider = spider;
		this.logs = logs;
		this.pane = pane;
	}
	//2022-02-25 19:07:49.965  INFO  ---  com.swd391.assi2.team2.App               : Starting App using Java 13.0.3 on DESKTOP-8M2NJ75 with PID 7120
	private final static String LOG_FORMAT = "%24s %8s --- %50s : %s";

	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public void error(String log, Object currentJob){
		String text = this.pane.getText();
		String newLog = String.format(LOG_FORMAT, DATE_FORMAT.format(Calendar.getInstance().getTime()), "ERROR" ,currentJob.getClass().getSimpleName(), log);
		text = text + newLog;
		this.pane.setText(text);
	}

	public void info(String log, Object currentJob){

	}

	public void debug(String log, Object currentJob){

	}

	public void warn(String log, Object currentJob){

	}

	public void fatal(String log, Object currentJob){

	}
}
