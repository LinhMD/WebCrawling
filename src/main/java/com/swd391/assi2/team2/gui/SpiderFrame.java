package com.swd391.assi2.team2.gui;

import com.swd391.assi2.team2.repository.UnitOfWork;
import com.swd391.assi2.team2.spider.Spider;

import javax.swing.*;

public class SpiderFrame {

	public UnitOfWork work;

	private JButton btnStop;
	private JButton btnStart;
	private JTree JobList;
	private JTextPane txtSpiderLog;
	public JList resultList;
	private JButton btnExport;
	private JButton btnImport;
	private JProgressBar progressBar;
	private JButton btnSave;

	public Spider spider;

	public SpiderFrame() {
	}

	public SpiderFrame(UnitOfWork work, Spider spider) {
		this.work = work;
		this.spider = spider;
	}
}
