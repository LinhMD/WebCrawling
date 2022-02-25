package com.swd391.assi2.team2.repository;

import org.springframework.stereotype.Service;

@Service
public interface UnitOfWork {
	JobRepository getJobRepo();

}
