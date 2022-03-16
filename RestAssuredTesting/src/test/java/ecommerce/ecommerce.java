package ecommerce;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ecommerce {
	
	
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accessToken;
	
	public static int count;
	public static String email;
	public static String users;
	public static String id;
	
	
	
	@Test(priority = 0 , enabled = false)
	public void signup()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"shesha800@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/signup")
			
			.then()
			.assertThat().statusCode(201).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	message = js.get("message");
	System.out.println(message);
	
	
}
	
	
	@Test(priority = 1)
	public void Login()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"shesha800@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/login")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	accessToken = js.get("accessToken");
	System.out.println(accessToken);
	}
	
	
	
	@Test(priority = 2)
	public void getUser()
	{
		RestAssured.baseURI =baseurl;
		
	/*
	 * String 	requestbody = "{\n"
	 
			+ "	\"email\": \"shesha80@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	*/
	Response resposne = given()
			.header("Content-Type","application/json")
			.header("Authorization","bearer "+accessToken)
			//.body(requ)
			
			.when()
			.get("/user")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	//JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	//accessToken = js.get("accessToken");
	System.out.println(jsonresponse);
	
	
	JsonPath jsonData = new JsonPath(jsonresponse);
	//const jsonData = pm.response.json();

	count = jsonData.get("count");
	System.out.println(count);
	
	
	/*String userss[]=new String[count];
	users = jsonData.get("users");
	String[] array=null;
	array=users.split(",");
	//email = jsonData.get("users.email");
	//userss.push.apply(userss, users);
	System.out.println(userss[1]);
	
	*/
	
	users = jsonData.get("users").toString();
	System.out.println(users);
	String array[]=null;
	array=users.split("}");
	System.out.println(array);
	//JsonPath ar = new JsonPath(array[]);
	
	//for(int i=0;i<3;i++)
	//{
		email = array[1];
		System.out.println("email="+email);
		
		
		/*
		if(email=="bal@gmail.com")
		{
			id = jsonData.get("user[i]._id");
			System.out.println(id);
	        
	        //pm.environment.set("Uid", id);
	 
	    }*/
	//}
	
	
	
	}
	
	
	@Test(priority = 3, enabled = false)
	public void delete()
	{
		RestAssured.baseURI =baseurl;
		
			Response resp = given()
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer "+accessToken)
			.when()
			.delete("/user/"+id)
			
			.then()
			.contentType(ContentType.JSON)
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resp.asString();
	JsonPath js = new JsonPath(jsonresponse);
	message = js.get("message");
	System.out.println(message);
  }
	
}
