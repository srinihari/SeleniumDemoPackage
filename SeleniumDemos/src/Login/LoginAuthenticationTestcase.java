package Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginAuthenticationTestcase {
	WebDriver driver;
	LoginAuthentication page;
	
	public LoginAuthenticationTestcase(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void homePages() throws Exception{
		page=new LoginAuthentication(driver);
		try {
			page.homePage();
		}catch(Exception e) {
			throw(e);
		}
	}
	public void login() throws Exception{
		page=new LoginAuthentication(driver);
		try {
			page.loginPage();
		}catch(Exception e) {
			throw(e);
		}
	}
	public void dashboard() throws Exception{
		page=new LoginAuthentication(driver);
		try {
			page.dshbordPage();
		}catch(Exception e) {
			throw(e);
		}
	}
	public void wishListPage() throws Exception{
		page=new LoginAuthentication(driver);
		try {
			page.myWishListPage();
		}catch(Exception e) {
			throw(e);
		}
	}
	public void wishListCreate() throws Exception{
		page=new LoginAuthentication(driver);
		try {
			page.myWishListPageCreation();
		}catch(Exception e) {
			throw(e);
		}
	}
	
	public void wishListViews() throws Exception{
	page=new LoginAuthentication(driver);
	try {
		page.wishListView();
	}catch(Exception e) {
		throw(e);
		}
	}
	

}
