package com.swd391.assi2.team2.spider;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;

public class SpiderLog implements Runnable {
	public Spider spider;
	public StringBuilder logs;
	public JTextPane pane;
	private boolean isUpdate = true;

	Thread thread = new Thread(this);

	public SpiderLog(Spider spider, StringBuilder logs, JTextPane pane) {
		this.spider = spider;
		this.logs = logs;
		this.pane = pane;
	}
	//2022-02-25 19:07:49.965  INFO  ---  com.swd391.assi2.team2.App               : Starting App using Java 13.0.3 on DESKTOP-8M2NJ75 with PID 7120
	private final static String LOG_FORMAT = "%24s %8s --- %20s : %s";

	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public void error(String log, Object currentJob){
		Logging(log, currentJob, "ERROR");
	}

	public void info(String log, Object currentJob){
		Logging(log, currentJob, "INFO");
	}

	public void debug(String log, Object currentJob){
		Logging(log, currentJob, "DEBUG");
	}

	public void warn(String log, Object currentJob){
		Logging(log, currentJob, "WARN");
	}

	public void fatal(String log, Object currentJob){
		Logging(log, currentJob, "FATAL");
	}

	private void Logging(String log, Object currentJob, String logLevel) {
		String newLog = String.format(LOG_FORMAT, DATE_FORMAT.format(Calendar.getInstance().getTime()), logLevel, currentJob.getClass().getSimpleName(), log) + "\n";
		this.logs.append(newLog);
		isUpdate = true;
	}

	@Override
	public void run() {
		while (true){
			if(isUpdate){
				String text = this.pane.getText();

				String updateText = text + this.logs.toString();
				this.pane.setText(updateText);
				this.logs = new StringBuilder();
				isUpdate = false;
			}else
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}


	}
}
