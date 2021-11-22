package com.healthcache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcache.models.Claim;
import com.healthcache.repository.ClaimRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class ClaimService {
	
	@Autowired
	private ClaimRepo cDao;
	
	
	/**
	 * @return null or list of all claims
	 */
	public List<Claim> findAllClaims(){
		List<Claim> claims = null;
		try { 
			claims = cDao.findAll();
		} catch(Exception ex) { ex.printStackTrace(); }
		return claims;
	}
	
	
	/**
	 * @param user_id
	 * @return return null or all claims by user_id
	 */
	public List<Claim> findAllClaimsByUserId(int id){
		List<Claim> claims = null;
		try {
			claims = cDao.findByUserId(id);
		} catch(Exception ex) { ex.printStackTrace(); }
		return claims;
	}
	
	
	/**
	 * @param status identified by static final String in Claim POJO (APPROVED/DENIED)
	 * @return null or all claims by status
	 */
	public List<Claim> findClaimsByStatus(String status){
		List<Claim> claims = null;
		try {
			claims = cDao.findByStatus(status);
		} catch(Exception ex) { ex.printStackTrace(); }
		return claims;
	}
	
	
	/**
	 * @return null or claim found by claim id
	 */
	public Claim findByClaimId(int id) {
		Claim claim = null;
		try {
		claim = cDao.findById(id);
		} catch(Exception ex) { ex.printStackTrace(); }
		return claim;
	}
	
	 
	/**
	 * @param claim
	 * @return true if update was successful or false otherwise
	 */
	public Claim updateClaim(Claim claim) {
		Claim c = null;
		try {
			c = cDao.save(claim);
		} catch(Exception ex) { ex.printStackTrace(); }
		return c;
	}
	
	
	/**
	 * @param claim
	 * @return claim on success
	 */
	public Claim saveNewClaim(Claim claim) {
		Claim c = null;
		try {
			c = cDao.save(claim);
		} catch(Exception ex) { ex.printStackTrace(); }
		return c;
	}
	 
	
	/**
	 * @param claimId
	 * @return true if claim deleted successfully or false otherwise
	 */
	public boolean deleteClaim(int id) {
		boolean success = true;
		try {
			cDao.deleteById(id);
		} catch(Exception ex) { 
			System.out.println("Deleting claim didn't work");
			ex.printStackTrace();
			success = false;
		} 
		return success;
	}

}
