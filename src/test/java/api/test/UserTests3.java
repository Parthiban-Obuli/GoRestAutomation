package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.GoRestEndPoints3;
import api.payload.PostComments;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests3 
{
	int USER_ID;
	public Logger logger;
	Faker faker=new Faker();
	User userpayload=new User();
	PostComments postcommentspayload=new PostComments();
	
	@BeforeClass
	public void setup()
	{
		userpayload.setName(faker.name().fullName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setGender("Male");
		userpayload.setStatus("Active");
		
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("---Creating the User---");
		Response response=GoRestEndPoints3.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
		JSONObject jobj=new JSONObject(response.asString());
		USER_ID=jobj.getInt("id");
		logger.info("---User has been Created---");
	}
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("---Getting the details of the User---");
		Response response=GoRestEndPoints3.getUser(USER_ID);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("---Got the Details of the User---");
	}
	@Test(priority=3)
	public void testPostCommentsByUser()
	{
		logger.info("---Posting an comments by the created User---");
		postcommentspayload.setUser_ID(USER_ID);
		postcommentspayload.setTitle("Parthiban-Title6");
		postcommentspayload.setBody("Parthiban-Post Comments-Body6");
		Response response=GoRestEndPoints3.postCommentsByUser(USER_ID, postcommentspayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
		int Comments_ID;
		JSONObject cmtsobj=new JSONObject(response.asString());
		Comments_ID=cmtsobj.getInt("id");
		System.out.println("The User Posted Comments ID is:"+" "+Comments_ID);
		logger.info("---Comments has been posted by the created User---");
	}
	@Test(priority=4)
	public void testGetPostedCommentsByUser()
	{
		logger.info("--Getting the details of the Posted Comments by the Created User---");
		Response response=GoRestEndPoints3.getPostedCommentsByUser(USER_ID);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("--Got the details of the Posted Comments by the Created User---");
	}
	@Test(priority=5)
	public void testGetAllUser()
	{
		logger.info("--Getting the details of the 20 Users---");
		Response response=GoRestEndPoints3.getAllUser();
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("--Got the details of the 20 Users---");
	}
}
