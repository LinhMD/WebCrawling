package com.swd391.assi2.team2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Work implements UnitOfWork{

	JobRepository jobRepository;

	public Work(@Autowired JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public JobRepository getJobRepo() {
		return null;
	}
}
