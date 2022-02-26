package com.swd391.assi2.team2.gui;

import com.swd391.assi2.team2.repository.UnitOfWork;
import com.swd391.assi2.team2.spider.Spider;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SpiderFrame {

	public UnitOfWork work;

	private JButton btnStop;
	private JButton btnStart;
	public JTree JobList;
	public JTextPane txtSpiderLog;
	public JPanel SpiderPanel;
	private JButton btnSave;
	private JProgressBar progressBar;
	private JButton btnExport;
	private JButton btnImport;
	private JList resultPane;

	public Spider spider;

	public SpiderFrame() {
	}

	public SpiderFrame(UnitOfWork work, Spider spider) {
		this.work = work;
		this.spider = spider;
		this.btnStart.addActionListener(this::btnStartClick);
		this.btnStop.addActionListener(this::btnStopClick);

	}

	public void btnStartClick(ActionEvent event){
		this.spider.thread.start();
		this.btnStart.setEnabled(false);
		this.btnStop.setEnabled(true);
	}

	public void btnStopClick(ActionEvent event){
		this.spider.thread.stop();
		this.spider.thread = new Thread(this.spider);
		this.btnStart.setEnabled(true);
		this.btnStop.setEnabled(false);
	}




}
