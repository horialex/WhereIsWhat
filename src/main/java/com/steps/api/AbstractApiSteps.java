package com.steps.api;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.steps.AbstractSteps;
import com.tools.constants.ApiUrlConstants;


public class AbstractApiSteps extends AbstractSteps{

	private static final long serialVersionUID = 1L;
	private static RequestSpecification tokenSpec = null;
	public static Map<String, String> extraHeaders = new HashMap<String, String>();
	
public static RequestSpecification getSpecWithExtraHeaders(){
		
		tokenSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(ApiUrlConstants.BASE_URI)
				.addHeader("User-Agent-WW", "web_agent")
				.addHeaders(extraHeaders)
				.build();
		
		return tokenSpec;
	}


	protected static <T> T createResource (String path, Object requestBody, Class<T> responseClass) {
		return given().relaxedHTTPSValidation()
				.spec(getSpecWithExtraHeaders())
				.body(requestBody)
				.when().post(path)
				.then()
				.assertThat().statusCode(anyOf(is(201), is(200), is(302)))
				.extract().as(responseClass);
	}

	protected static <T> T updateResource (String path, Object requestBody, Class<T> responseClass, Object...params) {
		return given().relaxedHTTPSValidation()
				.spec(getSpecWithExtraHeaders())
				.body(requestBody)
				.when().put(path, params)
				.then()
				.assertThat().statusCode(anyOf(is(201), is(200), is(302)))
				.extract().as(responseClass);
	}
	
	protected static String createResource (String path, Object requestBody) {
		return given()
			.relaxedHTTPSValidation()
			.spec(getSpecWithExtraHeaders())
			.body(requestBody)
			.when().post(path)
			.then()
			.assertThat().statusCode(anyOf(is(201),is(204), is(200), is(302)))
		    .extract().response().asString();
	}

	protected void uploadResource (String path, String resourcePath) {
		given()
			.relaxedHTTPSValidation()
			.spec(getSpecWithExtraHeaders())
			.multiPart(new File(resourcePath))
			.when().post(path)
			.then()
			.assertThat().statusCode(anyOf(is(201),is(204), is(200), is(302)));
	}
	
	protected String uploadCSVResource (String path, String pathToFile, String fileName) {
		 return given().relaxedHTTPSValidation()
			.spec(getSpecWithExtraHeaders())
			.multiPart(new MultiPartSpecBuilder(new File(pathToFile + fileName)).fileName(fileName).mimeType("application/vnd.ms-excel").build())
			.when().post(path)
			.then()
			.assertThat().statusCode(anyOf(is(201),is(204), is(200), is(302)))
		    .extract().response().asString();
	}
	
	protected String createItemFromCSV(String path) {
		 return given().relaxedHTTPSValidation()
					.spec(getSpecWithExtraHeaders())
					.when().post(path)
					.then()
					.assertThat().statusCode(anyOf(is(201),is(204), is(200), is(302)))
				    .extract().response().asString();
	}
	
	protected <T> List<T> getResources(String path, Class<T> responseClass) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		json = given().relaxedHTTPSValidation()
					.spec(getSpecWithExtraHeaders())
					.when().get(path)
					.then()
					.assertThat().statusCode(anyOf(is(201),is(204), is(200), is(302)))
				    .extract().asString();
		
		@SuppressWarnings("unchecked")
		Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + responseClass.getName() + ";");
		T[] objects = mapper.readValue(json, arrayClass);
		return Arrays.asList(objects);
	}
}