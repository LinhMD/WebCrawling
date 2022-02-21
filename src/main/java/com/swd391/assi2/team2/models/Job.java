package com.swd391.assi2.team2.models;

import com.swd391.assi2.team2.data.job123.Details;
import com.swd391.assi2.team2.data.job123.Recruitment;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date submissionDeadline;
    private String salary;
    private String jobName;
    private String address;
    private String company;
    private String quantity;
    private String position;
    private String experience;
    private String workType;
    private String description;
    private String qualification;
    private String requirement;
    private String benefit;
    public static Job makeTransaction(@NotNull Recruitment recruitment) {
        Job job = new Job();
        Details details = recruitment.details;
        job.setCompany(recruitment.company);
        job.setDescription(recruitment.description);
        job.setAddress(details.address.split(":")[1]);
        job.setWorkType(details.workType.split(":")[1]);
//        job.setQualification(details.qualification.split(":")[1]);
//        job.setRequirement(recruitment.requirement);
//        job.setBenefit(recruitment.benefit);
//        job.setExperience(details.experience.split(":")[1]);
//        job.setPosition(details.position.split(":")[1]);
//        job.setSalary(details.salary.split(":")[1]);
//        // ----------------------------
//        job.setJobName(recruitment.title.toLowerCase().replaceAll("tuyển dụng", ""));
//        job.setQuantity(details.quantity.split(":")[1]);
        return job;
    }
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", submissionDeadline=" + submissionDeadline +
                ", salary='" + salary + '\'' +
                ", jobName='" + jobName + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", quantity=" + quantity +
                ", position='" + position + '\'' +
                ", experience='" + experience + '\'' +
                ", workType='" + workType + '\'' +
                ", description='" + description + '\'' +
                ", qualification='" + qualification + '\'' +
                ", requirement='" + requirement + '\'' +
                ", benefit='" + benefit + '\'' +
                '}';
    }
}
