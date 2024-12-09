package com.project.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.report.entity.City;
import com.project.report.response.CityResponse;
import com.project.report.service.CityService;

@RestController
@RequestMapping("v1/city")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@PostMapping("/getCityData")
	public ResponseEntity<CityResponse> getCityData(@RequestParam(required = true) Long cityId){
		return new ResponseEntity<>(cityService.getCityData(cityId),HttpStatus.OK);
		
	}
	
	@PostMapping("/allCityData")
	public ResponseEntity<List<City>> getCityData(){
		return new ResponseEntity<>(cityService.getAllCityData(),HttpStatus.OK);
		
	}

}
