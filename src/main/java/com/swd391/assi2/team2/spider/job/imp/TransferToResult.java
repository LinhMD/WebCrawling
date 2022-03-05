package com.swd391.assi2.team2.spider.job.imp;

import com.swd391.assi2.team2.models.Job;
import com.swd391.assi2.team2.spider.Spider;
import com.swd391.assi2.team2.spider.job.JobFactory;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import org.jdom2.Element;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TransferToResult extends ComplexJob{
    List<Object> objectList = new ArrayList<>();
    JList  ls = new JList<Object>();
    private boolean isUpdate = false;

    @Override
    public void run() {
        while(true){
            if(isUpdate){
                DefaultListModel<Object> defList  = new DefaultListModel<>();
                objectList.forEach(defList::addElement);
                ls.setModel(defList);
                isUpdate = false;
            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public MethodCall[] getImplementMethods() {
        return new MethodCall[0];
    }

    @Override
    public Object run(Object objectIn) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        isUpdate = true;
        objectList.add(objectIn);

        return objectIn;
    }

    @Override
    public SpiderJob initData(Element element, JobFactory jobFactory, Spider spider) {
         ls = spider.frame.resultPane;
            Thread thread = new Thread(this);
            thread.start();
         return this;
    }

}
