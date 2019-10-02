package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ShoppingTestCases extends BaseClass{
	
	AndroidDriver<WebElement> driver;
	
	public ShoppingTestCases(AndroidDriver<WebElement> driver)
	{
		this.driver=driver;
	}
	
	ShoppingTestCases stc=new ShoppingTestCases(driver);
	BaseClass base=new BaseClass();
	
	@BeforeTest
	public void selectlayout()
	{
		base.launchDevice();
		base.ChooseLayout();
	}
	
	@Test
	public void createlist(String listname)
	{
		base.addingList(listname);
	}

}
