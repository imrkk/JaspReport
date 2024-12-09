package com.project.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.report.entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long>{

	@Query(nativeQuery = true, value = "select s.storeName from Store s where s.cityName = :cityNameForQuery")
	List<String>  findStoreNameByCityName(String cityNameForQuery);

}
