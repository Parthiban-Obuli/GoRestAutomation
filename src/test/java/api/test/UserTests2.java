package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.GoRestEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 
{
	int ID;
	public Logger logger;
	Faker faker=new Faker();
	User userpayload=new User();
	@BeforeClass
	public void setup()
	{
		userpayload.setName(faker.name().fullName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		String gender="Male";
		userpayload.setGender(gender);
		String status="active";
		userpayload.setStatus(status);
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	void testCreateUser()
	{
		logger.info("Creating the User using Routes.Properties Method");
		Response response=GoRestEndpoints2.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
		JSONObject jobj=new JSONObject(response.asString());
		ID=jobj.getInt("id");
		logger.info("Created the User using Routes.Properties Method");
	}
	@Test(priority=2)
	void testGetUser()
	{
		logger.info("Getting the Details of the User using Routes.Properties Method");
		Response response=GoRestEndpoints2.getUser(ID);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Got the Details of the User using Routes.Properties Method");
	}
	@Test(priority=3)
	void testUpdateUser()
	{
		logger.info("Updating the User using Routes.Properties Method");
		userpayload.setName(faker.name().fullName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		String gender="Female";
		userpayload.setGender(gender);
		String status="Inactive";
		userpayload.setStatus(status);
		Response response=GoRestEndpoints2.updateUser(ID, userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Updated the User using Routes.Properties Method");
	}
	@Test(priority=4)
	void testDeleteUser()
	{
		logger.info("Deleting the User using Routes.Properties Method");
		Response response=GoRestEndpoints2.deleteUser(ID);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 204);
		logger.info("Deleted the User using Routes.Properties Method");
	}
}
