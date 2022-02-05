package com.swd391.assi2.team2.job123.services;

import com.swd391.assi2.team2.job123.models.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Double> {
    List<Recruitment> findAll();
    List<Recruitment>findByCompany(String company);

   // @Override
 //  List<Recruitment> saveAll(Iterable<S> iterable);
}
