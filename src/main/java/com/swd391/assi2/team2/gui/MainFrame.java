package com.swd391.assi2.team2.gui;

import com.swd391.assi2.team2.repository.UnitOfWork;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.SpiderFactory;
import com.swd391.assi2.team2.spider.job.JobFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MainFrame {

	public JPanel panel;
	public JList spiderList;
	private JTabbedPane tabbedPane;

	SpiderFactory spiderFactory;
	JobFactory jobFactory;
	UnitOfWork work;


	public MainFrame(SpiderFactory spiderFactory, JobFactory jobFactory, UnitOfWork work, List<Spider> spiders) {
		this.spiderFactory = spiderFactory;
		this.jobFactory = jobFactory;
		this.work = work;
		DefaultListModel model = new DefaultListModel<>();
		model.addAll(spiders);
		this.spiderList.setModel(model);
		this.spiderList.setCellRenderer(new DefaultListCellRenderer());
		this.spiderList.setVisible(true);
		for (Spider spider : spiders) {
			this.tabbedPane.addTab(spider.id, spider.frame.SpiderPanel);
		}

		this.tabbedPane.addChangeListener( e -> {
			int i = this.tabbedPane.getSelectedIndex() - 1;
			Spider spider = (Spider) this.spiderList.getModel().getElementAt(i);
			spider.frame.initJobTree();
		});

		this.spiderList.addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) {
				JList source = (JList)e.getSource();
				if(source.getSelectedValue() instanceof Spider){
					Spider spider = (Spider) source.getSelectedValue();
					this.tabbedPane.setSelectedIndex((this.tabbedPane.indexOfTab(spider.id)));
					spider.frame.initJobTree();
				}
			}
		});
	}


}
