package com.healthcache.service;

import java.util.List;
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
	public List<Claim> findAllClaimsByUserId(int userId){
		List<Claim> claims = null;
		try {
			claims = cDao.findByUserId(userId);
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
	public Claim findByClaimId(int claimId) {
		Claim claim = null;
		try {
			cDao.findByClaimId(claimId);
		} catch(Exception ex) { ex.printStackTrace(); }
		return claim;
	}
	
	
	/**
	 * @param claim
	 * @return true if update was successful or false otherwise
	 */
	public boolean updateClaim(Claim claim) {
		boolean success = false;
		try {
			success = updateClaim(claim);
		} catch(Exception ex) { ex.printStackTrace(); }
		return success;
	}
	
	
	/**
	 * @param claim
	 * @return true if new claim was successfully saved or false otherwise
	 */
	public boolean saveNewClaim(Claim claim) {
		boolean success = false;
		try {
			success = cDao.saveClaim(claim);
		} catch(Exception ex) { ex.printStackTrace(); }
		return success;
	}
	
	
	/**
	 * @param claimId
	 * @return true if claim deleted successfully or false otherwise
	 */
	public boolean deleteClaim(int claimId) {
		boolean success = false;
		try {
			success = cDao.deleteClaim(claimId);
		} catch(Exception ex) { ex.printStackTrace(); }
		return success;
	}

}
