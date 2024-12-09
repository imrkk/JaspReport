package com.project.report.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.report.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	@Query(nativeQuery=true, value = "select u.userName  from `User` u ")
	public List<String> getAllUser();
	
	public Optional<User> findByUserName(String userName);

}
