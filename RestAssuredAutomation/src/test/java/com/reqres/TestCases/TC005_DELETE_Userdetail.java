package com.reqres.TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endpoints.Endpoints;
import com.reqres.Base.BaseClass;

import io.restassured.path.json.JsonPath;

public class TC005_DELETE_Userdetail extends BaseClass{

	@BeforeClass
	public void deleteUserDetail() throws InterruptedException {

		setBaseURI();
		includeHeader("Authorization","Bearer "+prop.getProperty("Accesstoken"));
		response=requestType("GET",Endpoints.getUsers);

		//By using JsonPath, i am getting the first employee and deleting that user data

		JsonPath jsonPathEvaluater = response.jsonPath();

		String empID = jsonPathEvaluater.get("[0].id");
		response=requestType("DELETE",Endpoints.deleteuser +empID);

	}

	@Test(priority=2,description = "Validating the StatusCode")
	void checkStatusCode() {
		validateStatusCode(response, 204);
	}

	@Test(priority=3,description = "Validating the StatusLine")
	void checkStatusLine() {
		
		validateStatusLine(response, "HTTP/1.1 204 No Content");
	}

	@Test(priority=1,description = "Validating the ResponseBody")
	void validateResponseBody() {
		validateResponseBody(response);
	}
	@AfterClass
	void teardown() {
		logger.info("**************************Finished TC005_DELETE_Userdetail**********************");
	}
}
