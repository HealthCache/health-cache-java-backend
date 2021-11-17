package com.healthcache.util;

import java.util.Arrays;
import java.util.List;

public class Util {

	public static final String APPROVED = "APPROVED";
	public static final String DENIED = "DENIED";
	public static final String PENDING = "PENDING";

	public static final List<String> CLAIM_TYPES = Arrays.asList("SURGERY", "MEDICATION", "ELECTIVE", 
			"EMERGENCY", "OTHER");
}
