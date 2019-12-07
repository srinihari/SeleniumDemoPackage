package DriverInitiate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.activation.CommandMap;
import javax.activation.DataSource;
import javax.activation.MailcapCommandMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import ExcelConfig.excelConfig;
import Login.LoginAuthenticationTestcase;
import PropertyFile.PropertyFileConfig;


public class TestDriverInitial {
	PropertyFileConfig fileconfig;
	LoginAuthenticationTestcase testcase;
	
	public String browsr_name;
	WebDriver driver;
	WebDriverWait wait;
	public static String start_date;
	public static String end_date;
	public static String pass;
	public static String fail;
	public static String skip;
	public static String total;





	
	
	@BeforeTest
	@Parameters("browser")
	public void startDriver(String browsr_name) throws Exception{
		fileconfig=new PropertyFileConfig();
		if(browsr_name.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");	
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		}
	}
	
	@Test(priority=0)
	public void orderHomePage() throws Exception{
		testcase=new LoginAuthenticationTestcase(driver);
		try {
			testcase.homePages();
		}catch(Exception e) {
			throw(e);
		}
	}
	@Test(priority=1)
	public void orderLoginPage() throws Exception{
		testcase=new LoginAuthenticationTestcase(driver);
		try {
			testcase.login();
		}catch(Exception e) {
			throw(e);
		}
	}
	
	@Test(priority=2)
	public void orderdDashboardPage() throws Exception{
		testcase=new LoginAuthenticationTestcase(driver);
		try {
			testcase.dashboard();
		}catch(Exception e) {
			throw(e);
		}
	}
	@Test(priority=3)
	public void wishListPage() throws Exception{
		testcase=new LoginAuthenticationTestcase(driver);
		try {
			testcase.wishListPage();
		}catch(Exception e) {
			throw(e);
		}
	}
	@Test(priority = 4)
	public void wishListCreate() throws Exception{
		testcase=new LoginAuthenticationTestcase(driver);
		try {
			testcase.wishListCreate();
		}catch(Exception e) {
			throw(e);
		}
	}
	@Test(priority = 5)
	public void viewWishList() throws Exception{
		testcase=new LoginAuthenticationTestcase(driver);
		try {
			testcase.wishListViews();
		}catch(Exception e) {
			throw(e);
		}
	}
	@AfterTest
	public void SendEmail() throws Exception{
		//driver.quit();
		Thread.sleep(1000);
		fileconfig=new PropertyFileConfig();
		File xmlfile=new File(fileconfig.getXmlfile());
		DocumentBuilderFactory builderfactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dobuilder=builderfactory.newDocumentBuilder();
		Document doc=dobuilder.parse(xmlfile);
		doc.getDocumentElement().normalize();
		NodeList list=doc.getElementsByTagName("suite");
		for(int i=0;i<list.getLength();i++) {
			Node node=list.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element=(Element) node;
				start_date=element.getAttribute("started-at");
				end_date=element.getAttribute("finished-at");
			}
		}
		NodeList lists=doc.getElementsByTagName("testng-results");
		for(int i=0;i<lists.getLength();i++) {
			Node node=lists.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element=(Element) node;
				pass=element.getAttribute("passed");
				fail=element.getAttribute("failed");
				skip=element.getAttribute("skipped");
				System.out.println(pass);
				System.out.println(fail);
				System.out.println(skip);

				
				total=String.valueOf((Integer.parseInt(pass)+Integer.parseInt(fail))+Integer.parseInt(skip));

				}
		}
		try {
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
			ImageHtmlEmail email=new ImageHtmlEmail();
			String htmlEmailTemplate = (
					"            <center > <table width=\"600\" style=\"width:600px\">\r\n" +
					"            <tbody>\r\n" +
					"                 <tr style=\"background-color:#fff;align=center\">\r\n" +
					"                    <td style=\"border:1px solid #dddee1;padding:24px;word-break:break-word;word-wrap:break-word\" colspan=\"2\">\r\n" +
					"                       <p>Hi,<br><br>Automation Demo Testing is completed. Here is the summary report.</p>\r\n" +
					"                        <table class=\"border\" width=\"400\" border=\"1\" bgcolor=\"#f5f7fa\" style=\"width:100%;background-color:#f5f7fa;border:1px solid #dddee1\">\r\n" +
					"                            <tbody>\r\n" +
					"                            <tr>\r\n" +
					"                                <tr> <td colspan=\"8\" align=\"center\">Testing Report</td></tr>\r\n"+
					"                                <tr> <td>Project Name</td>"+"<td colspan=\"8\">"+"Automation Demo Test"+"</td></tr>\r\n"+
					"                                <tr> <td>Browser</td>"+"<td colspan=\"8\">"+"chrome_Browser"+"</td></tr>\r\n"+
					"                               <tr> <td>Started Date</td>"+"<td colspan=\"8\">"+start_date+"</td></tr>\r\n"+
					"                                <tr> <td>End Date</td>"+"<td colspan=\"8\">"+end_date+"</td></tr>\r\n"+
					"                                <tr> <td width=\"20%\">Result</td>"+"<td width=\"10%\" style=\"color:Green\">Pass : "+pass+"</td>" +
					"                                <td width=\"10%\" style=\"color:red\">Fail : "+fail+"</td>"+
					"                                <td width=\"10%\" style=\"color:blue\">Skip : "+skip+"</td>" +
					"                                <td width=\"10%\" style=\"color:blue\">Total : "+total+"</td>" +
					"                                </tr>\r\n" +
					"                            </tbody>\r\n" +
					"                        </table>\r\n" +
					"                        <br>This email was sent automatically Don't Reply this.<br><br>Thanks,\r\n" +
					"                    </td>\r\n" +
					"                </tr>\r\n" +
					"            </tbody>\r\n" +
					"        </table>\r\n" +
					"    </center>");
			URL url=new URL("http://automationpractice.com/index.php");
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("srini0293vasan@gmail.com", "Temp#123"));
			email.setSSLOnConnect(true);
			email.setFrom("srini0293vasan@gmail.com");
			email.addTo("srini2093@gmail.com");
			//email.addCc("aadhavanr10@gmail.com");
			email.setHtmlMsg(htmlEmailTemplate);
			email.send();
			
//			Email email = new SimpleEmail();
//			
//			email.setSubject("TestMail");
//			email.setMsg("This is a test mail ... :-)");
//			email.addTo("foo@bar.com");
//			email.send();
		}catch(Exception e) {
			throw(e);
		}
	}
	@AfterSuite
	public void  emailsent() throws Exception
	{
	System.out.println("Email send succesfully");	
	}
	
	
//	public static void ExplicitWait(WebDriver driver,WebElement locater) throws Exception
//	{	
//
//		(new WebDriverWait(driver,30)).until(ExpectedConditions
//				.elementToBeClickable(locater));
//
//	}
//	

	
	
}


