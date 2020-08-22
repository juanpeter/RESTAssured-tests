package restassured;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class TestRestAssured {
	
	@Before
	public void configuraApi() {
		RestAssured.baseURI = "https://reqres.in";
	}
	@Test
	public void methodGet1() {
		given()
		.when()
			.get("/api/users/2")
		.then().contentType("application/json")
			.statusCode(200).and().log().all();
	}
	
	@Test
	public void methodGet2() {
		given()
		.when()
			.get("https://reqres.in/api/users/23")
		.then().contentType("application/json")
			.statusCode(404).and().log().all();
	}
}