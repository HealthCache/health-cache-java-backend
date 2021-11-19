package com.healthcache.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcache.models.Claim;

@Repository
public interface ClaimRepo extends JpaRepository<Claim, Integer> {
	
	public List<Claim> findAll();
	
	public List<Claim> findByUserId(int userId);
	
	public List<Claim> findByStatus(String status);
	
	public Claim findById(int id);

}
