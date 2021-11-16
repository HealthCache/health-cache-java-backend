package com.healthcache.repository;

import java.util.List;

import com.healthcache.models.Claim;

public interface ClaimRepo {
	
	public List<Claim> findAll();
	
	public List<Claim> findAllByUserId(int user_id);
	
	public List<Claim> getClaimByStatus(String status);
	
	public Claim findByClaimId();
	
	public boolean updateClaim();
	
	public boolean createClaim();
	
	public boolean deleteClaim();

}
