package servlet;


import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;


class DashBoardTest extends TestCase {

	private static ChromeDriverService service;
	private WebDriver driver;

	
	 @BeforeClass
	  public static void createAndStartService() {
	    service = new ChromeDriverService.Builder()
	        .usingAnyFreePort()
	        .build();
	    try {
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
			fail("driver couldn't start");
		}
	  }

	  @AfterClass
	  public static void stopService() {
	    service.stop();
	  }

	  @Before
	  public void createDriver() {
	    driver = new RemoteWebDriver(service.getUrl(),
	        DesiredCapabilities.chrome());
	  }

	  @After
	  public void quitDriver() {
	    driver.quit();
	  }
	
	@Test
	void test() {
		
		String url = "http://localhost:8080/computerdatabase/dashboard";
		driver.get(url);
	}

}
