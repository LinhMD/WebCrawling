package com.swd391.assi2.team2.job123.services;

import com.swd391.assi2.team2.job123.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsRepository extends JpaRepository<Details, Double> {
    @Override
    List<Details> findAll();

}
