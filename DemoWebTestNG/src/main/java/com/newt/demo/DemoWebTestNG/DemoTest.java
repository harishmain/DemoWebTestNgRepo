package com.newt.demo.DemoWebTestNG;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DemoTest {
	
	private static String weburl="http://localhost:8181/DemoWebApp/login/Welcome.html";
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		// Create a new instance of the html unit driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//Navigate to DevOps Demo WebApp Home Page
		driver.get(weburl);
		System.out.println("Inside WebDemoTest :: setUp(), Driver get successfully ");
	}
	
	@Test
	public void verifyHomePage() 
	{
		
		driver = new FirefoxDriver();
		driver.get(weburl);
		
		// verify title of index page
		System.out.println("Testing for Title Verification");
		verifyTitle("Welcome");
	}
	
	@Test(dataProvider="getLoginData")
	public void testcaseforLogin(String username, String password) 
	{
		//verify User Logins successfuly
		System.out.println("Testing for User Credentials Verification for username:"+username+", password:"+password);
		verifyLoginSuccess(username,password);
	}
	
	@DataProvider
	public Object[][] getLoginData(){
		
		Object[][] data = new Object[1][2];
		data[0][0]="user1";
		data[0][1]="newt123";
		
/*		data[1][0]="user1";
		data[1][1]="newt1234";*/
		
		return data;
		
	}
	
/*		
	@Test
	public void testcaseforLoginSuccess() 
	{
		//verify User Logins successfuly
		System.out.println("Testing for User Credentials Verification");
		verifyLoginSuccess("user1","newt123");
	}

	@Test
	public void testcaseforLoginFail() 
	{
		//verify User Logins un-successfuly
		System.out.println("Testing for User Credentials Verification");
		verifyLoginFail("user1","newt12345");
	}*/
	
	//Test Case Function : Verifies if the User is on the Correct Page
	private void verifyTitle(String expectedTitle) {
		//get the title of the page
		String actualTitle = driver.getTitle();
		System.out.println("actualTitle:"+actualTitle);
		
		// verify title
		Assert.assertEquals( actualTitle, expectedTitle);	
	}
	
	//Test Case Function : Verifies if the User is able to login Successfully with valid Credentials
	private void verifyLoginSuccess(String userName, String password) {
		String expectedMessage = "You have login successfully ";
		// find the Username Text Box
		WebElement element = driver.findElement(By.xpath("//*[@id='usrname']"));

		// set the Username in Text Box
		element.sendKeys(userName);

		// find the Password Text Box
		element = driver.findElement(By.xpath("//*[@id='pwd']"));
		
		// set the Password in input Text Box
		element.sendKeys(password);

		// find the Login Button
		element = driver.findElement(By.xpath("//*[@type='submit']"));
		
		// submit form
		element.submit();
		
		// find the Successful Message 
		element = driver.findElement(By.xpath("(//span)[1]"));
		
		// get the Successful Message		
		String actualLoginMessage = element.getText();
//		System.out.println("actualLoginMessage :: " + actualLoginMessage);
		Assert.assertEquals(actualLoginMessage, expectedMessage);
	}
	
	//Test Case Function : Verifies if the User is NOT able to login Successfully with Invalid Credentials
	private void verifyLoginFail(String userName, String password) {
		String expectedMessage = "Invalid username or password, please try again ";
		// find the input Username Text Box
		WebElement element = driver.findElement(By.xpath("//*[@id='usrname']"));

		// set the user name in the Text Box
		element.sendKeys(userName);

		// find the input Password Text Box
		element = driver.findElement(By.xpath("//*[@id='pwd']"));
		
		// set the Password in the Text Box
		element.sendKeys(password);

		// find the Login Button
		element = driver.findElement(By.xpath("//*[@type='submit']"));
		
		// submit form
		element.submit();
		
		// find the Successful Message
		element = driver.findElement(By.xpath("(//span)[1]"));
		
		// get the Successful Message
		String actualLoginMessage = element.getText();
		System.out.println("actualLoginMessage :: " + actualLoginMessage);
		Assert.assertEquals(actualLoginMessage, expectedMessage);
	}
	
	@AfterClass
	public void endTest() {
		driver.quit();//
		System.out.println("Inside DemoTest :: endTest(), Driver quit successfully... ");
	}
	
	


}
