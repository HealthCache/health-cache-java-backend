package healthcache.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import healthcache.models.Claim;
import healthcache.service.ClaimService;
import healthcache.util.Util;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("claim")
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClaimController {
	
	
	private static final Logger logger = LogManager.getLogger(ClaimController.class);
	
	@Autowired
	private ClaimService cServ;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate rest;
	
	
	/**
	 * @return the claim types defined in the Claim POJO as a list of strings
	 */
	@GetMapping("/claimtypes")
	public ResponseEntity<List<String>> getClaimTypes()
	{
		return new ResponseEntity<List<String>>(Util.CLAIM_TYPES, HttpStatus.OK);
	}
	
	//Test performed by Taylor to test Eureka
	@GetMapping("/test")
	public String test() {
		return "This controller is responding";
	}
	

	/**
	 * @return all claims or null
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Claim>> getAllClaims(){
		List<Claim> claims = null;
		HttpStatus responseStatus = HttpStatus.OK;
		try
		{
			claims =  cServ.findAllClaims();
		} catch(Exception ex) 
		{ 
			
			ex.printStackTrace(); 
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}
		return new ResponseEntity<List<Claim>>(claims, responseStatus);
	}
	
	
	/**
	 * @param id of user as request parameter
	 * @return all claims found by the user id
	 */
	@GetMapping("/byuserid/{id}")
	public ResponseEntity<List<Claim>> findAllByUserId(@PathVariable int id){
		List<Claim> claims = null;
		HttpStatus responseStatus = HttpStatus.OK;
		
		System.out.println("userId: " + id);
		try
		{
			claims =  cServ.findAllClaimsByUserId(id);
		} catch(Exception ex) 
		{ 
			ex.printStackTrace(); 
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}
		return new ResponseEntity<List<Claim>>(claims, responseStatus);
	}
	
	
	/**
	 * @param status string - refer to the Claim POJO (APPROVED, DENIED, PENDING) 
	 * @return
	 */
	@GetMapping("/bystatus/{status}")
	public ResponseEntity<List<Claim>> findClaimsByStatus(@PathVariable String status) {
		List<Claim> claims = null;
		HttpStatus responseStatus = HttpStatus.OK;
		try
		{
			claims =  cServ.findClaimsByStatus(status);
		} catch(Exception ex) 
		{ 
			ex.printStackTrace(); 
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}
		return new ResponseEntity<List<Claim>>(claims, responseStatus);
	}
	
	
	/**
	 * @param id of claim
	 * @return the claim found by the claim id
	 */
	@GetMapping("/byclaimid/{id}")
	public ResponseEntity<Claim> findByClaimId(@PathVariable int id) {
		Claim claim = null;
		HttpStatus responseStatus = HttpStatus.OK;
		try
		{
			claim =  cServ.findByClaimId(id);
			if (claim == null)
			{
				responseStatus = HttpStatus.NOT_FOUND;
			}
		} catch(Exception ex) 
		{ 
			ex.printStackTrace(); 
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}
		return new ResponseEntity<Claim>(claim, responseStatus);
	}
	
	
	/**
	 * @param claim information to update
	 * @return successful string if the claim updates successfully or error string otherwise
	 */
	@PostMapping("/update")
	public ResponseEntity<String> updateClaim(@RequestBody LinkedHashMap<String, String> claim) {
		String result = "Claim Updated Successfully";
		HttpStatus resultStatus = HttpStatus.CREATED;
		try
		{
			Claim c = new Claim(Integer.valueOf(claim.get("id")), Integer.valueOf(claim.get("userId")), 
					claim.get("claimType"), claim.get("description"), claim.get("status"));
			if(cServ.updateClaim(c) == null) { throw new Exception("conflict updating");}
		} catch(Exception ex) 
		{
			ex.printStackTrace();
			result = "Claim Not Updated. Error: " + ex.getMessage();
			resultStatus = HttpStatus.CONFLICT;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}

		return new ResponseEntity<String>(result, resultStatus);
	}
	
	
	/**
	 * @param claim
	 * @return successful response if the claim is save, or conflict response if there is a conflict
	 */
	@PostMapping("/save")
	public ResponseEntity<String> saveNewClaim(@RequestBody LinkedHashMap<String, String> claim) {
		String result = "Claim Saved Successfully";
		HttpStatus resultStatus = HttpStatus.CREATED;
		try
		{
			Claim c = new Claim(0, Integer.valueOf(claim.get("userId")), claim.get("claimType"), 
					claim.get("description"), Util.PENDING);
			
			System.out.println(c);
			
			if(cServ.saveNewClaim(c) == null) { throw new Exception("returning null");}
		} catch(Exception ex) 
		{
			ex.printStackTrace();
			result = "Claim Not Saved. Error: " + ex.getMessage();
			resultStatus = HttpStatus.CONFLICT;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}

		return new ResponseEntity<String>(result, resultStatus);
	}
	
	
	/**
	 * @param id of claim to delete
	 * @return string and status indicating success or failure
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteClaim(@PathVariable int id) {
		String result = "Claim Was Deleted. ID: " + id;
		HttpStatus responseStatus = HttpStatus.OK;
		try
		{
			if (!cServ.deleteClaim(id)) {
				result = "Failed to delete claim. ID: " + id;
				responseStatus = HttpStatus.NOT_FOUND;
			}
			
		} catch(Exception ex) 
		{ 
			ex.printStackTrace(); 
			result = "There was a problem deleting. ID: " + id + " - " + ex.getMessage();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error = " + ex.getMessage());
			logger.debug(ex.getStackTrace());
		}
		return new ResponseEntity<String>(result, responseStatus);
	}
	
}
