package com.swd391.assi2.team2.job123.models;

import com.swd391.assi2.team2.job123.DataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode()
@Data
@Table(name = "details")

@Entity
public class Details  extends DataModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public double id;
	public String address;
	public String submissionDeadline;
	public String workType;
	public String quantity;
	public String salary;
	public String position;
	public String experience;
	public String qualification;

}
