package com.project.report.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.report.entity.City;
import com.project.report.exception.ReportException;
import com.project.report.repository.CityRepo;
import com.project.report.response.CityResponse;

@Service
public class CityService {
	
	@Autowired
	private CityRepo cityRepo;
	
	public CityResponse getCityData(Long cityId) {
		CityResponse response = new CityResponse();
		City city = cityRepo.findCityByCityId(cityId);
		if(ObjectUtils.isEmpty(city)) {
			throw new ReportException("No city found from this city id !!");
		}
		BeanUtils.copyProperties(city, response);
		return response;
		
	}
	
	
	public List<City> getAllCityData(){
		 List<City> allData = cityRepo.findAll();
		 return allData;
	}

}
