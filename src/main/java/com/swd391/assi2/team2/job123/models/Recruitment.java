package com.swd391.assi2.team2.job123.models;

import com.swd391.assi2.team2.job123.DataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode()
@Data
@Entity
@Table(name = "recruitment")
public class Recruitment extends DataModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public double id;
	//-----
	public String title;
	public String company;
	public String description;
	public String requirement;
	public String benefit;
	//-----
	@OneToOne // danh giấu chó mqh 1 - 1 với Details
	@JoinColumn(name ="details_id")
	public Details details;

}
