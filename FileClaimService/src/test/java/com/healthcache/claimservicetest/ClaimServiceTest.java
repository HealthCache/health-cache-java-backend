package com.healthcache.claimservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.FileClaimService.models.Claim;
import com.FileClaimService.repository.ClaimRepo;
import com.FileClaimService.service.ClaimService;

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
	public void saveNewClaim() {
		Claim c = new Claim(1, 2, "test", "test", "test");
		
		
		Mockito.when(claimServ.saveNewClaim(c)).thenReturn(c);
		Claim test = claimServ.saveNewClaim(c);
		
		
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
	
	
	@Test
	public void findClaimById() {
		
		Claim c = new Claim(2, 2, "test", "test", "pending");
		Mockito.when(claimServ.findByClaimId(2)).thenReturn(c);
		Claim b = claimServ.findByClaimId(2);
		
		System.out.println("claim c " + c);
		System.out.println("claim b " + b);
		assertEquals(c, b);
		 
	}
	
	
	@Test
	public void updateClaim() {
		Claim c = new Claim(1, 2, "test", "test", "approved");
		
		Mockito.when(claimServ.updateClaim(c)).thenReturn(c);
		Claim b = claimServ.updateClaim(c);
		
		assertEquals(c, b);
		
	}
	
	@Test
	public void findClaimsByStatus() {
		Claim c = new Claim(1, 2, "test", "test", "pending");
		Claim d = new Claim(3, 4, "test2", "test2", "pending");
		List<Claim> cList = new ArrayList<Claim>();
		cList.add(c);
		cList.add(d);
		
		Mockito.when(claimServ.findClaimsByStatus("pending")).thenReturn(cList);
		List<Claim> testList = claimServ.findClaimsByStatus("pending");
		
		assertEquals(cList, testList);
		
		
	}
	
	
	@Test
	public void deleteClaim() {
		
		
		Mockito.doNothing().when(claimRep).deleteById(1);
		boolean success = claimServ.deleteClaim(1);
		 
		System.out.println("boolean coming back as " + success);
		assertEquals(true, success);
	}
	
	@Test
	public void newClaimException() {
		try {
			Claim c = new Claim(1, 2, "test", "test", "approved");
			Mockito.when(claimServ.saveNewClaim(c)).thenThrow(Exception.class);
			
		}catch(Exception e) {
			assertTrue(e instanceof Exception);
		}
	}
	
	 
}
