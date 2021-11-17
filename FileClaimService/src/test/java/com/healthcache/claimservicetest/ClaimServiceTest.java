package com.healthcache.claimservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.healthcache.models.Claim;
import com.healthcache.repository.ClaimRepo;
import com.healthcache.service.ClaimService;

@SpringBootTest
public class ClaimServiceTest {

	
	@Autowired
	private ClaimService claimServ;
	
	@MockBean
	private ClaimRepo claimRep;
	
	@Before(value="test")
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void saveClaim() {
		Claim c = new Claim(1, 2, "test", "test", "test");
		
		
		Mockito.when(claimRep.save(c)).thenReturn(c);
		Claim test = claimRep.save(c);
		
		
		assertEquals(test, c);
	}
	
	@Test
	public void findAllClaims() {
		List<Claim> allClaims=claimServ.findAllClaims();
		
		assertTrue(allClaims!=null);
		
	}
	
	@Test
	public void findClaimsById() {
//		List<Claim>userClaims = claimServ.findAllClaimsByUserId(1);
//		assertTrue(userClaims !=null);
		
		Claim c = new Claim(1, 2, "test", "test", "test");
		Claim d = new Claim(3, 4, "test2", "test2", "test2");
		List<Claim> cList = new ArrayList<Claim>();
		cList.add(c);
		cList.add(d);
		Mockito.when(claimRep.findByUserId(1)).thenReturn(cList);
		List<Claim> dList = claimServ.findAllClaimsByUserId(1);
		
		assertEquals(cList, dList);
		
		
	}
	
	@Test
	public void findClaimByStatus() {
		Claim c = new Claim(1, 2, "test", "test", "pending");
		Claim d = new Claim(3, 4, "test2", "test2", "pending");
		List<Claim> cList = new ArrayList<Claim>();
		cList.add(c);
		cList.add(d);
		
		Mockito.when(claimRep.findByStatus("pending")).thenReturn(cList);
		List<Claim> testList = claimRep.findByStatus("pending");
		
		assertEquals(cList, testList);
		
		
	}
	
	
}
