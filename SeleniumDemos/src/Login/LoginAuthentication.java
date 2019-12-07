/**
 * 
 */
package Login;

import java.net.URL;

import org.apache.commons.mail.ImageHtmlEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExcelConfig.excelConfig;
import PropertyFile.PropertyFileConfig;
import PropertyFile.PropertyFileConfig;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class LoginAuthentication {

	@FindBy(how=How.XPATH,using="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a" + "") 
	public WebElement HomePage;
	
	@FindBy(how=How.ID,using="email") 
	public WebElement email;
	
	@FindBy(how=How.ID,using="passwd")
	public WebElement password;
	
	@FindBy(how=How.ID,using="SubmitLogin")
	public WebElement submit;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a"+ "")
	public WebElement orderdetils;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"center_column\"]/div/div[2]/ul/li/a" + "")
	public WebElement wishlist;
	
	@FindBy(how=How.ID,using="form_wishlist")
	public WebElement wishlistcreate;
	
	@FindBy(how=How.ID,using="block-history")
	public WebElement viewlist;
	
	WebDriver driver;
	PropertyFileConfig config;
	excelConfig excel=new excelConfig();
	
	public LoginAuthentication(WebDriver dri) {
		this.driver=dri;
		PageFactory.initElements(dri, this);
	}
	
	@Test
	public void  homePage() throws Exception{
		String Testcase_ID = excel.excelReadData(0, 6, 0);
		String Expected_resul=excel.excelReadData(0, 6, 7);
		try {
			config=new PropertyFileConfig();
			driver.get(config.getUrl());
			excel.writeExcelData(0, 6, 11);
		}catch(Exception e) {
			excel.writeFailData(0, 6, 11);
			throw(e);
		}
	}
	
	@Test
	public void loginPage() throws Exception{
	String Testcase_ID = excel.excelReadData(0, 8, 0);
	String Expected_resul=excel.excelReadData(0, 8, 7);
	try {
		HomePage.click();
		config=new PropertyFileConfig();
		email.sendKeys(config.getUserName());
		password.sendKeys(config.getPassword());
		submit.click();
		excel.writeExcelData(0, 7, 11);
		excel.writeExcelData(0, 8, 11);
		Thread.sleep(3000);

	}catch(Exception e) {
		excel.writeFailData(0, 7, 11);
		excel.writeFailData(0, 8, 11);
		throw(e);
	}
	}
	@Test
	public void dshbordPage() throws Exception{
	String Testcase_ID = excel.excelReadData(0, 9, 0);
	String Expected_resul=excel.excelReadData(0, 9, 7);
	try {
		ExplicitWait(driver,orderdetils);
		orderdetils.click();
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/a" + "")).click();
		excel.writeExcelData(0, 9, 11);

	}catch(Exception e) {
		Exceptionalert("OrderForm Failed", driver);
		driver.switchTo().alert().accept();
		excel.writeFailData(0, 9, 11);
		throw(e);
	}
	}
	@Test
	public void myWishListPage() throws Exception{
		String TestCase_ID=excel.excelReadData(0, 10, 0);
		String Excepted_Result=excel.excelReadData(0, 10, 7);
		try{
			ExplicitWait(driver, wishlist);
			wishlist.click();
			driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/a" + "")).click();
			
			excel.writeExcelData(0, 10,11);
		}catch(Exception e) {
			Exceptionalert("Wishlistpage Failed to click", driver);
			driver.switchTo().alert().accept();
			excel.writeExcelData(0, 10, 11);
		}
	}
	@Test
	public void myWishListPageCreation() throws Exception{
		String TestCase_ID=excel.excelReadData(0, 11, 0);
		String Excepted_Result=excel.excelReadData(0, 11, 7);
		try {
			ExplicitWait(driver, wishlistcreate);
			driver.findElement(By.id("name")).sendKeys("Shirts");
			driver.findElement(By.id("submitWishlist")).click();
			excel.writeExcelData(0, 11, 7);
		}catch(Exception e) {
			Exceptionalert("WishList Creation Failed", driver);
			excel.writeFailData(0, 11, 11);
		}
	}
	@Test
	public void wishListView() throws Exception{
	String Testcase_ID=excel.excelReadData(0, 12, 0);
	String Excepted_Result=excel.excelReadData(0, 12, 7);
	try {
		ExplicitWait(driver,viewlist);
		driver.findElement(By.id("block-history"));
		driver.findElement(By.xpath("//*[@id=\"wishlist_14688\"]/td[5]/a" + "")).click();
		excel.writeExcelData(0, 12, 11);
		}catch(Exception e) {
			Exceptionalert("view Result Failed to View", driver);
			excel.writeFailData(0, 12, 11);
		}
	}
		
		
	
	
	

	

	
	public static void Exceptionalert(String message,WebDriver driver) throws Exception
	{	
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("alert('"+message+"')");
	}	
	
	public static void ExplicitWait(WebDriver driver,WebElement locater) throws Exception
	{	

		(new WebDriverWait(driver,60)).until(ExpectedConditions
				.elementToBeClickable(locater));

	}
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	

	


