package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class GoRestEndpoints2 
{
	static String bearer_token="9b064672b8266626b4db433cba8e91390d89ee43faab6701dd63076bf7198c9d";
	public static ResourceBundle getURL()
	{
		ResourceBundle Routes=ResourceBundle.getBundle("Routes");
		return Routes;
	}
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		Response response=given()
			.headers("Authorization","Bearer "+bearer_token)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		return response;			
	}
	public static Response getUser(int user_ID)
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
			.headers("Authorization","Bearer "+bearer_token)
			.pathParam("user_id", user_ID)
		.when()
			.get(get_url);
		return response;
	}
	public static Response updateUser(int user_ID, User payload)
	{
		String put_url=getURL().getString("put_url");
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.pathParam("user_id", user_ID)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.put(put_url);
		return response;
	}
	public static Response deleteUser(int user_ID)
	{
		String delete_url=getURL().getString("delete_url");
		Response response=given()
			.headers("Authorization","Bearer "+bearer_token)
			.pathParam("user_id", user_ID)
		.when()
			.delete(delete_url);
		return response;
	}
}
