package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.GoRestEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest 
{
	public Logger logger;
	User userpayload=new User();
	int UserID;
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	void testCreateUserThroughDataDriven(String name, String email, String gender)
	{
		userpayload.setName(name);
		userpayload.setEmail(email);
		userpayload.setGender(gender);
		userpayload.setStatus("active");
		logger=LogManager.getLogger(this.getClass());
		logger.info("Data Driven Testing: Creating the Users");
		Response response=GoRestEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 201);
		logger.info("Data Driven Testing: Users has been created successfully");
	}
}
