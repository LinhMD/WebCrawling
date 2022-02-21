package com.swd391.assi2.team2.spider.job.imp;
import com.swd391.assi2.team2.data.job123.Recruitment;
import com.swd391.assi2.team2.models.Job;
import com.swd391.assi2.team2.spider.job.core.SpiderJob;
import com.swd391.assi2.team2.utils.FinalJobList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SaveAllRecuiment implements SpiderJob {
public String listJobName;

    @Override
    public MethodCall getMethodCall() {
        return SpiderJob.super.getMethodCall();
    }

    @Override
    public MethodCall[] getImplementMethods() {
        return new MethodCall[0];
    }

    @Override
    public Object run(Object recuitment) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Job> jobList;
       jobList = FinalJobList.JOB_LIST.get(listJobName) == null ? new ArrayList<Job>() : (ArrayList<Job>) FinalJobList.JOB_LIST.get(listJobName);

        Recruitment rec ;
        if(recuitment == null && recuitment instanceof Recruitment){
            throw new NoSuchMethodException("recuitment");
        }
        rec =(Recruitment) recuitment;
       Job job =   Job.makeTransaction(rec);
       jobList.add(job);
       FinalJobList.JOB_LIST.put(listJobName,jobList);

        return jobList;
    }

    @Override
    public void run() {
        jpaRepository.saveAll(FinalJobList.JOB_LIST.get(listJobName));
    }
}
