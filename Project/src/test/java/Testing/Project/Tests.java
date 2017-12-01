package Testing.Project;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.Home;
import Pages.SignIn;
import Pages.Valuation;

public class Tests {
	WebDriver driver;
	static ExtentReports report;
	String url = "https://www.autotrader.co.uk/";
	SpreadsheetReader reader;
	
	Home home;
	
	@BeforeClass
	public static void init() {
		report = new ExtentReports();
		String fileName = "TestReport" + ".html";
        String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
	}
	
	@Before
	public void setUp() {
		this.driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();
		reader = new SpreadsheetReader();
		this.home = new Home(driver);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@AfterClass
	public static void cleanUp() {
		report.flush();
	}
	
	@Ignore ("working") @Test
	public void failedSignIn() {
		ExtentTest failedLoginTest = report.createTest("Login test");
		failedLoginTest.log(Status.INFO, "Test to attempt to login with incorrect credentials.");
		
		failedLoginTest.log(Status.INFO, "Moving to login page");
		home.goSignIn();
		 
		SignIn signIn = new SignIn(driver);
		
		failedLoginTest.log(Status.INFO, "Entering incorrect credentials");
		signIn.attemptSignIn("wrong@wrong.com", "AlsoWrong");			//get rid of hard code with sheet
		
		if(driver.getCurrentUrl().equals("https://www.autotrader.co.uk/secure/signin/status/failed_login?after-signin-url=")) {
			failedLoginTest.log(Status.PASS, "Failed to login succesfully :)");
		} else {
			failedLoginTest.log(Status.FAIL, "Failed to fail to login :(");
		}
	}
	
	@Ignore ("working") @Test
	public void search() throws InterruptedException {
		ExtentTest searchTest = report.createTest("Search test");
		searchTest.log(Status.INFO, "Attempts to make a succesful search");
		
		searchTest.log(Status.INFO, "Entering search details");
		home.search("SO507AY", "vauxhall", "vectra");						//get rid of hard code
		
		searchTest.log(Status.INFO, "Attempting search...");
		home.clickSearch();
		
		if(!driver.getCurrentUrl().equals(url)) {
			searchTest.log(Status.PASS, "Made a search (need to find a way to see if it got the correct ammount)");
		} else {
			searchTest.log(Status.FAIL, "Failed to make a succesful search.");
		}
	}
	
	@Test
	public void twitter() {											//error from new tab? unsure. Should ask but lazy and can do other shit.
		ExtentTest twitterTest = report.createTest("Twitter test");
		twitterTest.log(Status.INFO, "Check if the twitter link works");
		
		twitterTest.log(Status.INFO, "clicking link");
		home.twitter();
		if(driver.getCurrentUrl().equals("https://twitter.com/AutoTrader_UK")) {
			twitterTest.log(Status.PASS, "twitter link works");
		} else {
			twitterTest.log(Status.FAIL, "twitter link somehow doesn't work like wtf dude");
		}
	}
	
	@Test
	public void signIn() {
		ExtentTest signInTest = report.createTest("Sign in test");
		signInTest.log(Status.INFO, "Sign in to a pre-made account");
		
		signInTest.log(Status.INFO, "Moving to sign in page");
		home.goSignIn();
		
		signInTest.log(Status.INFO, "Attempting to sign in");
		SignIn signIn = new SignIn(driver);
		signIn.attemptSignIn("test.mpadwick@gmail.com", "password1");
	}
	
	public void valuation() {
		ExtentTest valuationTest = report.createTest("Valuation");
		valuationTest.log(Status.INFO, "A test to check a cars value");
		
		valuationTest.log(Status.INFO, "Going to valuation page");
		home.goValuation();
		
		Valuation valuation = new Valuation(driver);
		
		valuationTest.log(Status.INFO, "entering details");
		valuation.getValuation("HN08 0DP", "10000");
																	//Placeholder for excel
		if(!driver.getCurrentUrl().equals("https://www.autotrader.co.uk/car-valuation")) {			//honestly I thought those 2 fields would be enough for it. Stupid website
			valuationTest.log(Status.PASS, "went where it should");
		} else {
			valuationTest.log(Status.FAIL, "not on right page :/");
		}
	}
}
