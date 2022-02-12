package com.swd391.assi2.team2.data.job123;

import com.swd391.assi2.team2.data.DataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Details extends DataModel {
	public String address;
	public String submissionDeadline;
	public String workType;
	public String quantityNeed;
	public String salary;
	public String position;
	public String experience;
	public String qualification;
	public String quantity;
}
