package com.reqres.TestCases;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endpoints.Endpoints;
import com.reqres.Base.BaseClass;
import com.reqres.utils.RandomService;


public class TC004_PUT_Userdetail extends BaseClass{

	String username=RandomService.getRandomEmpName();
	String userjob=RandomService.getRandomEmpJob();

	@BeforeClass
	public void updateUserDetail() throws InterruptedException {
		
		setBaseURI();
		JSONObject requestParam=new JSONObject();
		requestParam.put("name", username);
		requestParam.put("job", userjob);
		includeHeader("Authorization","Bearer "+prop.getProperty("Accesstoken"));
		includeHeader("Content-Type","application/json");
		httpRequest.body(requestParam.toString());
		response=requestType("PUT",Endpoints.updateuser);

	}

	@Test(priority=2,description = "Validating the StatusCode")
	void checkStatusCode() {
		validateStatusCode(response, 200);
	}

	@Test(priority=3,description = "Validating the StatusLine")
	void checkStatusLine() {
		validateStatusLine(response, "HTTP/1.1 200 OK");
	}

	@Test(priority=1,description = "Checking ResponseBody")
	void checkResponseBody() {
		checkResponseBody(response, "name", "job");
	}

	@Test(priority=4,description = "Validating the ContentType")
	void checkContentType() {
		validateContentType(response, "application/json; charset=utf-8");
	}

	@AfterClass
	void teardown() {
		logger.info("**************************Finished TC004_PUT_Userdetail**********************");
	}
}
