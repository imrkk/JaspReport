package com.project.report.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "City")
public class City extends BaseVo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityId;
	
	private String city;
	
	private Long stateId;

}
