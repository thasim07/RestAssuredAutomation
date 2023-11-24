package com.reqres.TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.reqres.Base.BaseClass;

public class TC001_GET_AllUsers extends BaseClass{

	@BeforeClass

	public static void getAllUsersData() throws InterruptedException {
		logger.info("**********************TC001_GET_AllUsers**********************************");
		setBaseURI();
		response=requestType("GET",prop.getProperty("userpath"));		
	}

	@Test(priority=1,description = "Validating the ResponseBody")
	void checkResponseBody() {
		validateResponseBody(response);
	}

	@Test(priority=2,description = "Validating the StatusCode")
	void checkStatusCode() {
		validateStatusCode(response, 200);
	}

	@Test(priority=3,description = "Validating the StatusLine")
	void checkStatusLine() {
		validateStatusLine(response, "HTTP/1.1 200 OK");
	}

	@Test(priority=4,description = "Validating the ResponseTime")
	void checkResponseTime() {
		validateResponseTime(response, 2000);
	}

	@Test(priority=5,description = "Validating the ContentType")
	void checkContentType() {
		validateContentType(response, "application/json; charset=utf-8");
	}

	@AfterClass
	void teardown() {
		logger.info("**************************Finished TC001_GET_AllUsers**********************");
	}
}
