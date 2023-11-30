package com.reqres.utils;

import org.apache.commons.lang3.RandomStringUtils;

/*
 * Description: here, i will get random generated data for employee name and employee job
 */

public final class RandomUtils {
	
	
	public static String generateRandomString(int numberofdigits) {
		return RandomStringUtils.randomAlphabetic(numberofdigits);
	}
	
	public static String generateRandomString1(int numberofdigits) {
		return RandomStringUtils.randomAlphabetic(numberofdigits);
	}
	
}
