package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

@Ignore //tag for skipping a class
public class DataDrivenTests 
{
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String username, String Fname, String Lname, String useremail, String pwd, String ph)
	{
		User userPayload = new User();
		//creating the payload
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstName(Fname);
		userPayload.setLastName(Lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeletUserByUserName(String username)
	{
		//deleting all user created in first request
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);	
	}

}
