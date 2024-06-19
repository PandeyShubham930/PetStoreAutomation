package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 
{
	//UserEndPoints(this class contains api call methods{post get etc})
	//created for CRUD operations(create, read, update, delete)
	//getting the data from properties files
	
	private static ResourceBundle getURL() {
		ResourceBundle resource = ResourceBundle.getBundle("routes");
		return resource;
	}
	
	public static Response createUser(User payload)
	{
		String post_url =getURL().getString("post_url");//getting data from routes.properties files
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when().post(post_url);
		return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_url =getURL().getString("get_url");
		Response response = given().pathParam("username", userName).when().get(get_url);
		return response;
	}
	
	public static Response  updateUser(String userName, User payload)
	{
		String update_url =getURL().getString("update_ur1");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName).body(payload).when().put(update_url);
		return response;
	}
	
	public static Response deleteUser(String userName) 
	{
		String delete_url = getURL().getString("delete_url");
		Response response =given().pathParam("username", userName).when().delete(delete_url);
		return response;
	}
	

}
