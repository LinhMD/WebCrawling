package com.swd391.assi2.team2.repository;

import com.swd391.assi2.team2.models.Job;
import com.swd391.assi2.team2.data.job123.Details;
import com.swd391.assi2.team2.data.job123.Recruitment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Double> {

    default Job TransferSpiderToJob(@NotNull Recruitment recruitment) {
        Job job = new Job();
        Details details = recruitment.details;
        job.setCompany(recruitment.company);
        job.setDescription(recruitment.description);
        job.setAddress(details.address.split(":")[1]);
        job.setWorkType(details.workType.split(":")[1]);
        job.setQualification(details.qualification.split(":")[1]);
        job.setRequirement(recruitment.requirement);
        job.setBenefit(recruitment.benefit);
        job.setExperience(details.experience.split(":")[1]);
        job.setPosition(details.position.split(":")[1]);
        job.setSalary(details.salary.split(":")[1]);
        // ----------------------------
        job.setJobName(recruitment.title.toLowerCase().replaceAll("tuyển dụng", ""));
        job.setQuantity(details.quantity.split(":")[1]);
       return job;
    }
}



