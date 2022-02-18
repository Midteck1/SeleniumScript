package TestAssignement;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SortingData {
	
ExtentHtmlReporter htmlReporter;
WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

	@BeforeTest
    public void startReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

	@Test
	public void sortByName() throws InterruptedException {
		test = extent.createTest("Test Case SortByName");
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver 2");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://mystifying-beaver-ee03b5.netlify.app/");
		driver.findElement(By.id("filter-input")).sendKeys("p");
		Select s=new Select(driver.findElement(By.id("sort-select")));
		s.selectByVisibleText("Name");
		//sorting by Name
		List<WebElement> listName=driver.findElements(By.xpath("//div[@class='table-data data-name']"));
		List<String> originalListName=	listName.stream().map(p->p.getText()).collect(Collectors.toList());
		//sorted Original list
		List<String> sortedListName=originalListName.stream().sorted().collect(Collectors.toList());
		System.out.println(originalListName);
		System.out.println(sortedListName );
		boolean bothEquals = originalListName.equals(sortedListName);
        if(bothEquals){
        	test.log(Status.PASS,  "Matches : "+ sortedListName + "And "+ originalListName);
        } else {
        	test.log(Status.FAIL, "Not Matches:  "+ sortedListName + "And "+ originalListName);
        }
       driver.close();
}
	@Test
	public void sortByScore() {
		test = extent.createTest("Test Case SortByScore");
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver 2");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://mystifying-beaver-ee03b5.netlify.app/");
		driver.findElement(By.id("filter-input")).sendKeys("p");
		Select s=new Select(driver.findElement(By.id("sort-select")));
		s.selectByVisibleText("Name");
		//Sorting by 
		List<WebElement>listScore=driver.findElements(By.xpath("//div[@class='table-data data-averageImpact']"));
		List<String>originalScoreList=listScore.stream().map(q->q.getText()).collect(Collectors.toList());
		//sorted OriginalList
		List<String> sortedScoreList=originalScoreList.stream().sorted().collect(Collectors.toList());
		System.out.println(originalScoreList);
		System.out.println(sortedScoreList );
		boolean bothEquals = originalScoreList.equals(sortedScoreList);
        if(bothEquals){
        	test.log(Status.PASS,  "Matches : "+ sortedScoreList + "And "+ originalScoreList);
        } else {
        	test.log(Status.FAIL, "Not Matches:  "+ sortedScoreList + "And "+ originalScoreList);
        }

        driver.close();
	}
	    @AfterMethod
		@AfterTest
	    public void tearDown() {
			driver.quit();
	        extent.flush();
	    }
	
}
