package com.nurseryshopapp.genericlibrary;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverLibrary implements ProjectConstant{

	public WebDriver driver;
	public static WebDriver stdriver;
	public Actions actionobj;
	public Select selectobj;
	
	public  WebDriver openBrowser(String browser) {
		
		if (browser.equals("Chrome")) {
			driver = new ChromeDriver();
			stdriver = driver;

		} else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
			stdriver = driver;

		} else if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();
			stdriver = driver;
		} else {
			Reporter.log("Invalid Browser Name....!!", true);
		}
		return driver;

	}
	public void navigateToApp(String url)
	{
		driver.get(url);
	}
	
	public void maximizeBrowser() 
	{
		driver.manage().window().maximize();
	}
	
	public void closeBrowser() 
	{
		driver.close();
		
		Reporter.log("Browser Terminated Successfully",true);
		
	}
	
	public void waitStatement()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitduration));
	}
	public void waitStatement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitduration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickOnElement(WebElement element)
	{
		element.click();
	}
	
	public void clickOnElement_UsingActions(WebElement element)
	{
		actionobj= new Actions(driver);
		
		actionobj.click(element).perform();
	}
	
	public void enterDataOnElement(WebElement element,String data)
	{
		element.clear();
		element.sendKeys(data);
	}
	
	public void enterDataOnElementUsingActions(WebElement element,String data)
	{
		actionobj= new Actions(driver);
		element.clear();
		actionobj.moveToElement(element).sendKeys(data).perform();
	}
	
	public void SelectOption(WebElement dropdownElement , String value ) {
		selectobj = new Select(dropdownElement);
		selectobj.selectByValue(value);
		
	}
	
	public void SelectOption(WebElement dropdownElement, int value) {
		selectobj = new Select(dropdownElement);
		selectobj.selectByIndex(value);
	}
	
	public void SelectOption(String value , WebElement dropdownElement ) {
		selectobj = new Select(dropdownElement);
		selectobj.selectByVisibleText(value);
	}
	
	public void mouseHoverToElement(WebElement element) {
		actionobj = new Actions(driver);
		actionobj.moveToElement(element).perform();
	}
}


