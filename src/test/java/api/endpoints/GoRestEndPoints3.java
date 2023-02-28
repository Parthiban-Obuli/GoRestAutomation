package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.PostComments;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class GoRestEndPoints3 
{
	int User_ID;
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
	public static Response getUser(int user_Id)
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.pathParam("user_id", user_Id)
		.when()
			.get(Routes.get_url);
		return response;
	}
	public static Response postCommentsByUser(int user_Id, PostComments payload)
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.pathParam("user_id", user_Id)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_comments_url);
		return response;			
	}
	public static Response getPostedCommentsByUser(int user_Id)
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.pathParam("user_id", user_Id)
		.when()
			.get(Routes.get_posted_comments_url);
		return response;
	}
	public static Response getAllUser()
	{
		Response response=given()
			.headers("Authorization", "Bearer "+bearer_token)
			.queryParam("page", 1)
			.queryParam("per_page", 20)
		.when()
			.get(Routes.get_all_users);
		return response;
	}
}
