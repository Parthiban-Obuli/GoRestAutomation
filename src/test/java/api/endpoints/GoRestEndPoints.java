package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class GoRestEndPoints 
{
	static String bearer_token="9b064672b8266626b4db433cba8e91390d89ee43faab6701dd63076bf7198c9d";
	public static Response createUser(User payload)
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return response;
	}
	public static Response getUser(int user_ID)
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.pathParam("user_id",user_ID)
			
		.when()
			.get(Routes.get_url);
		return response;						
	}
	public static Response updateUser(int user_ID, User payload)
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.pathParam("user_id", user_ID)			
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.put(Routes.put_url);
		return response;
	}
	public static Response deleteUser(int user_ID)
	{
		Response response=given()
			.headers("Authorization","Bearer "+bearer_token)
			.pathParam("user_id", user_ID)
		.when()
			.delete(Routes.delete_url);
		return response;
	}
}
