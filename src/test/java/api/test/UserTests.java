package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.GoRestEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests 
{
	int UserID;
	public Logger logger;
	Faker faker=new Faker();
	User userpayload=new User();
	@BeforeClass
	public void setup()
	{
		userpayload.setName(faker.name().fullName());
		userpayload.setEmail(faker.internet().safeEmailAddress());	
		userpayload.setGender("Male");
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("Creating the User");
		Response response=GoRestEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
		JSONObject jobj=new JSONObject(response.asString());
		UserID=jobj.getInt("id");
		logger.info("User is Created Successfully");
	}
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("Reading the Created User");
		Response response=GoRestEndPoints.getUser(UserID);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Got the details of the Created User Successfully");
	}
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("Updating the Created User");
		userpayload.setName(faker.name().fullName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setGender("female");
		userpayload.setStatus("Inactive");
		Response response=GoRestEndPoints.updateUser(UserID, userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Updated the Created User Successfully");
	}
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("Deleting the Created User");
		Response response=GoRestEndPoints.deleteUser(UserID);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 204);
		logger.info("Deleted the Created User Successfully");
	}
}
