package servlet;



import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;


class DashBoardTest extends TestCase {

	private static ChromeDriverService service;
	private static WebDriver driver;
	
	private static final String URL_DASHBOARD = "http://localhost:8080/computerdatabase/dashboard";
	private static final String URL_ADD_COMPUTER = "http://localhost:8080/computerdatabase/add";
	
	private static final String PREVIOUS_BUTTON_ID = "previous";
	private static final String NEXT_BUTTON_ID = "next";
	private static final String RESULTS_ID = "results";
	private static final String ADD_COMPUTER_ID = "addComputer";
	private static final String SEARCHBOX_ID = "searchbox";
	private static final String NAME_COMPUTER_ID = "name";
	private static final String SEARCH_BUTTON_ID = "searchsubmit";

	  @BeforeAll
	  public static void createDriver() {
		  service = new ChromeDriverService.Builder()
			    	.usingDriverExecutable(new File("/usr/lib/chromium-browser/chromedriver"))
			        .usingAnyFreePort()
			        .build();
			    try {
					service.start();
				} catch (IOException e) {
					e.printStackTrace();
					fail("service couldn't start");
				}
	    driver = new RemoteWebDriver(service.getUrl(),
	    		new ChromeOptions());
	  }

	  @AfterAll
	  public static void quitDriver() {
	    driver.quit();
	    service.stop();
	  }
	
	 
	@Test
	public void listNotEmptyTest() {
		driver.get(URL_DASHBOARD);
		WebElement results = driver.findElement(By.id(RESULTS_ID));
		List<WebElement> list = results.findElements(By.tagName("tr"));
		assertNotEquals(Integer.valueOf(0).toString(),Integer.valueOf(list.size()).toString());
	}
	  
	  
	@Test
	public void nextTest() {
		driver.get(URL_DASHBOARD);
		WebElement nextButton = driver.findElement(By.id(NEXT_BUTTON_ID));
		WebElement results = driver.findElement(By.id(RESULTS_ID));
		List<WebElement> list = results.findElements(By.tagName("tr"));
		nextButton.click();
		results = driver.findElement(By.id(RESULTS_ID));
		List<WebElement> list2 = results.findElements(By.tagName("tr"));
		assertNotEquals(list,list2);
	}
	
	@Test
	public void previousTest() {
		driver.get(URL_DASHBOARD+"?index=10");
		WebElement previousButton = driver.findElement(By.id(PREVIOUS_BUTTON_ID));
		WebElement results = driver.findElement(By.id(RESULTS_ID));
		List<WebElement> list = results.findElements(By.tagName("tr"));
		previousButton.click();
		results = driver.findElement(By.id(RESULTS_ID));
		List<WebElement> list2 = results.findElements(By.tagName("tr"));
		assertNotEquals(list,list2);
	}
	
	@Test
	public void addComputerButtonTest() {
		driver.get(URL_DASHBOARD);
		WebElement addButton = driver.findElement(By.id(ADD_COMPUTER_ID));
		addButton.click();
		assertEquals(driver.getCurrentUrl(),URL_ADD_COMPUTER);
	}
	
	@Test
	public void searchBoxTest() {
		driver.get(URL_DASHBOARD);
		WebElement searchBox = driver.findElement(By.id(SEARCHBOX_ID));
		searchBox.sendKeys("name");
		WebElement searchButton = driver.findElement(By.id(SEARCH_BUTTON_ID));
		searchButton.click();
		
		WebElement results = driver.findElement(By.id(RESULTS_ID));
		List<WebElement> list = results.findElements(By.id(NAME_COMPUTER_ID));
		list.stream().forEach(e -> assertEquals("name",e.getText()));

	}

}
