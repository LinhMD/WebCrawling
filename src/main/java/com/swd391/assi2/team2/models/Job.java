package com.swd391.assi2.team2.models;

import lombok.*;

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
