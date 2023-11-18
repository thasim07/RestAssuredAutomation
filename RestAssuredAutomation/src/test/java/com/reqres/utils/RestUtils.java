package com.reqres.utils;

import org.apache.commons.lang3.RandomStringUtils;

/*
 * Description: here, i will get random generated data for employee name and employee job
 */

public class RestUtils {

	public static String empName() {
		String generatedString= RandomStringUtils.randomAlphabetic(3);
		return("ajith"+generatedString);
	}
	
	public static String empJob() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return("Tester"+generatedString);
	}
	
}
