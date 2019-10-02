package tests;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pages.ObjectLocator;

public class BaseClass extends ObjectLocator {

	AndroidDriver<WebElement> driver;

	
	public void launchDevice() {

		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			cap.setCapability(MobileCapabilityType.VERSION, "9");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "52004803eed56503");
			cap.setCapability("appPackage", "org.openintents.shopping");
			cap.setCapability("appActivity", "org.openintents.shopping.ShoppingActivity");
			URL url = new URL("http://0.0.0.0:4723/wd/hub");
			driver = new AndroidDriver<WebElement>(url, cap);

		} catch (Exception E) {
			System.out.println("Error Message" + E.getMessage());
		}
	}

	
	public void ChooseLayout() {
		driver.findElement(By.id(Select_layout)).click();
	}

	public void addingList(String listname) {

		driver.findElement(By.xpath(hamburger_menu)).click();
		driver.findElement(By.xpath(newlist)).click();
		driver.findElement(By.id(newlist_textbox)).clear();
		driver.findElement(By.id(newlist_textbox)).sendKeys(listname);
		driver.findElement(By.id(ok_button)).click();
		System.out.println("Succesfully Added List:" + listname);
	}

	public void addItems(String itemname) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id(additem_textbox)).clear();
		driver.findElement(By.id(additem_textbox)).sendKeys(itemname);
		driver.findElement(By.id(add_itembutton)).isDisplayed();
		driver.findElement(By.id(add_itembutton)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void selectList(String list) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(hamburger_menu)).click();
		String list_value = "//*[@text='" + list + "']";
		System.out.println(list_value);
		// wait for the list till it is visible
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(list_value)));
		driver.findElement(By.xpath(list_value)).click();

	}

	public void deleteItems() {
		String checkboxValue = driver.findElement(By.xpath(checkbox_1)).getText();
		driver.findElement(By.xpath(checkbox_1)).click();
		driver.findElement(By.xpath(cleanup_button)).click();
		System.out.println("Deleted the Iteam succesfully" + checkboxValue);
	}

	public void sortItems(String listname) {

		selectList(listname);
		driver.findElement(By.xpath(more_options)).click();
		driver.findElement(By.xpath(settings_btn)).click();
		driver.findElement(By.xpath(sort_order)).click();
		driver.findElement(By.xpath(alphabetical_sort)).click();
		driver.navigate().back();

	}

	/*
	 * public void validateList(String listName) {
	 * 
	 * selectList(listName); List<String> actualList = new ArrayList<String>();
	 * List<MobileElement> SortedItems =
	 * driver.findElements(By.xpath(list_value));
	 * 
	 * for (int i = 0; i < SortedItems.size(); i++) {
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * actualList.add(SortedItems.get(i).getText());
	 * 
	 * } System.out.println("Actual List:" + actualList);
	 * 
	 * //Stores the value passed in list List<List<String>> expectedList =
	 * ShoppingListStepDef.list; // Tree set is used to maintain the Order
	 * TreeSet<String> ExpectedsortList = new
	 * TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	 * 
	 * for (int i = 1; i < expectedList.size(); i++) {
	 * 
	 * if (listName.equals(expectedList.get(i).get(0))) { for (int j = 1; j <
	 * expectedList.get(i).size(); j++) {
	 * ExpectedsortList.add(expectedList.get(i).get(j));
	 * 
	 * } } } System.out.println("Expected List:" + ExpectedsortList); int i = 0;
	 * for (String expectedItems : ExpectedsortList) {
	 * org.junit.Assert.assertEquals(expectedItems, actualList.get(i)); //
	 * Assertion is done to compare both the list to validate sorting //
	 * functionality
	 * 
	 * i++; }
	 * 
	 * }
	 */

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
