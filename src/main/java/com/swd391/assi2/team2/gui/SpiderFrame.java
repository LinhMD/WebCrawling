package com.swd391.assi2.team2.gui;

import com.swd391.assi2.team2.repository.UnitOfWork;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;

public class SpiderFrame {

	public UnitOfWork work;

	public JButton btnStop;
	public JButton btnStart;
	public JTree jobList;
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
		this.btnImport.addActionListener(this::btnImportClick);

	}

	public void btnStartClick(ActionEvent event){
		this.spider.thread = new Thread(this.spider);

		this.spider.thread.start();
		this.btnStart.setEnabled(false);
		this.btnStop.setEnabled(true);
	}

	public void btnStopClick(ActionEvent event){
		this.spider.thread.stop();
		this.btnStart.setEnabled(true);
		this.btnStop.setEnabled(false);
	}

	public void btnImportClick(ActionEvent event){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(spider);
		DefaultMutableTreeNode jobsList = new DefaultMutableTreeNode("Jobs List");

		for (SpiderJob spiderJob : spider.spiderJobs) {
			MutableTreeNode child = spiderJob.toTreeNode();
			if(child == null) continue;
			jobsList.add(child);
		}
		System.out.println(jobsList.getChildCount());

		root.add(jobsList);

		this.jobList.setModel(new DefaultTreeModel(root));
	}




}
