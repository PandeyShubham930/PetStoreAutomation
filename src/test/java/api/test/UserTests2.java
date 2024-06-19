package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2
{
	Faker faker;
	User userPayload;
	Logger logger;
	@BeforeClass(description = "here we are generating all data for tests")
	public void setupData()
	{
		faker= new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//logs
		 logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
	//logger.info("***************************** creating user ***********************************"); for logging info level logs
		logger.debug("***************************** creating user ***********************************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);	
	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{
		logger.debug("***************************** getting user ***********************************");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);//in assertion use getStatusCode()//in then() use statusCode()	
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		//updating user data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
	
		logger.debug("***************************** updating user ***********************************");
		
		Response responseAfterUpdate = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		responseAfterUpdate.then().log().all();
		
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);//assertion
		
		//getting the updated user
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
	}
	
	@Test(priority = 4)
	public void testDeleteUsrByName()
	{
		logger.debug("***************************** deleting user ***********************************");
		//delete api call
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());	
		AssertJUnit.assertEquals(response.getStatusCode(), 200);//deleting response code
	}
}
