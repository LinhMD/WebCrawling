package com.swd391.assi2.team2.spider;

import javax.swing.*;

public class SpiderLog {
	public Spider spider;
	public StringBuilder logs;

	public JTextPane pane;

	public SpiderLog(Spider spider, StringBuilder logs, JTextPane pane) {
		this.spider = spider;
		this.logs = logs;
		this.pane = pane;
	}


	public void error(String log){
		String text = this.pane.getText();
		text = text + log;
		this.pane.setText(text);
	}

	public void info(String log){

	}

	public void debug(String log){

	}

	public void warn(String log){

	}

	public void fatal(String log){

	}
}
