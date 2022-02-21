package com.swd391.assi2.team2.repository;

import com.swd391.assi2.team2.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends JpaRepository<Job, Double> {
}



