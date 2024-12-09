package com.project.report.entity;

import org.springframework.data.geo.Point;

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
@Table(name = "Store")
public class Store extends BaseVo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;	
	private String storeName;
	private String cityName;
	private String gstNumber;
	private String fssai;
	private Point geoLocation;
	private Boolean isEnabled;
	private String deactivationMessage;
	private String paymentLookUpId;
	private String storeAddress;
	private String email;
	private String contactNumber;
	private String stateName;
	private Boolean isPanIndia;

}
