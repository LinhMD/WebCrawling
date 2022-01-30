package com.swd391.assi2.team2.model.job123;

import com.swd391.assi2.team2.model.DataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Recruitment extends DataModel {
	public String title;
	public String company;
	public Details details;
	public String description;
	public String requirement;
	public String benefit;
}
