package com.swd391.assi2.team2.job123.models;
import com.swd391.assi2.team2.job123.DataModel;
import lombok.Data;

@Data
public class Details  extends DataModel {
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
