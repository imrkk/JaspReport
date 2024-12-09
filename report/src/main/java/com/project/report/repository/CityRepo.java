package com.project.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.report.entity.City;

@Repository
public interface CityRepo extends JpaRepository<City, Long>{

	public City findCityByCityId(Long cityId);

}
