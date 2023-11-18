package com.reqres.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static RequestSpecification httpRequest;
	public static Response response;

	public static Properties prop;
	public static FileInputStream input;

	public static Logger logger;

	/* author:Thasim
	 * Created date: 27/09/2023
	 * Description:Here, i have created reusable methods
	 */

	@BeforeSuite

	public void setup() throws IOException {

		logger=Logger.getLogger("ReqresRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);

		//to load the properties
		prop = new Properties();
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setBaseURI() {
		RestAssured.baseURI = prop.getProperty("baseURI");
		httpRequest = RestAssured.given();
	}
	public static void includeHeader(String key, String value) {
		httpRequest=RestAssured.given().header(key, value);

	}
	

	public static Response requestType(String type, String resource) {

		switch(type){

		case "GET": 
			response=httpRequest.get(resource);
			break;

		case "POST": 
			response=httpRequest.post(resource);
			break;

		case "PUT": 
			response=httpRequest.put(resource);
			break;

		case "DELETE": 
			response=httpRequest.delete(resource);
			break;

		default:
			break;
		}

		return response;
	}

	public void validateResponseBody(Response response) {
		try {
			logger.info("*********************Validate Response Body****************************");
			String responseBody = response.getBody().asPrettyString();
			logger.info("The Response body: " + responseBody);
			Assert.assertNotNull(responseBody);
		} catch (Exception e) {
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
	}

	public void validateStatusCode(Response response, int expectedStatusCode) {
		try {
			logger.info("*********************Checking Response code****************************");
			int statusCode = response.getStatusCode();
			logger.info("The Status code is:" + statusCode);
			Assert.assertEquals(statusCode, expectedStatusCode);
		} catch (Exception e) {
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
	}

	public void validateStatusLine(Response response, String expectedStatusLine) {
		try {
			logger.info("*********************Checking StatusLine****************************");
			String statusLine = response.getStatusLine();
			logger.info("The Status Line is: " + statusLine);
			Assert.assertEquals(statusLine, expectedStatusLine);
		} catch (Exception e) {
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
	}

	public void validateResponseTime(Response response, long maxResponseTime) {
		try {
			logger.info("*********************Checking ResponseTime****************************");
			long responseTime = response.getTime();
			logger.info("The response time is:" + responseTime);
			if (responseTime > maxResponseTime) {
				logger.warn("Response time is greater than " + maxResponseTime);
				Assert.assertTrue(responseTime < maxResponseTime);
			}
		} catch (Exception e) {
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
	}

	public void validateContentType(Response response, String expectedContentType) {
		try {
			logger.info("*********************Checking content type****************************");
			String contentType = response.header("Content-Type");
			logger.info("Content type is:" + contentType);
			Assert.assertEquals(contentType, expectedContentType);
		} catch (Exception e) {
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
	}

	public void checkResponseBody(Response response, String name, String job) {
		try {
			logger.info("*********************Checking Response Body****************************");
			String responseBody = response.getBody().asPrettyString();
			logger.info("The Response body: " + responseBody);
			Assert.assertEquals(responseBody.contains(name),true);
			Assert.assertEquals(responseBody.contains(job),true);
		} catch (Exception e) {
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
	}
}

