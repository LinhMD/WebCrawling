package com.swd391.assi2.team2.job123.models;
import com.swd391.assi2.team2.job123.DataModel;
import lombok.Data;
@Data
public class Recruitment extends DataModel {
	public String title;
	public String company;
	public String description;
	public String requirement;
	public String benefit;
	public Details details;
}

