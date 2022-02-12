package com.swd391.assi2.team2.job123.repository;

import com.swd391.assi2.team2.job123.models.Details;
import com.swd391.assi2.team2.job123.models.Job;
import com.swd391.assi2.team2.job123.models.Recruitment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Double> {
    <S extends Job> S save(S s);

    default Job makeTransaction(@NotNull Recruitment recruitment) {
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



