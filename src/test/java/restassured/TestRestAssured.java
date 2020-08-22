package restassured;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import restassured.UserResponse;

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
	
	@Test
	public void methodPost() {
		User user = new User("John", "Doist");
			
			UserResponse response = RestAssured
			.given().contentType("application/json")
			.body(user)
			.when().post("api/users/")
			.then().contentType("application/json")
			.statusCode(201).extract().as(UserResponse.class);
		Assert.assertNotNull(response.getId());
		Assert.assertNotNull(response.getCreatedAt());
		Assert.assertEquals(user.getName(), response.getName());
		Assert.assertEquals(user.getJob(), response.getJob());

	}
	
	@Test
	public void methodDelete() {
		RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
			.delete("https://reqres.in/api/users/2")
		.then().statusCode(204);
	}
}