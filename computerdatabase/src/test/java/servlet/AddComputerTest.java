package servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

class AddComputerTest {

	private static ChromeDriverService service;
	private static WebDriver driver;

	private static final String URL_DASHBOARD = "http://localhost:8080/computerdatabase/dashboard";
	private static final String URL_ADD_COMPUTER = "http://localhost:8080/computerdatabase/add";
	
	private static final String CANCEL_BUTTON_ID = "cancel_button";


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
				DesiredCapabilities.chrome());
	}

	@AfterAll
	public static void quitDriver() {
		service.stop();
		driver.quit();
	}


	@Test
	void testCancelButton() {
		driver.get(URL_ADD_COMPUTER);
		WebElement cancelButton = driver.findElement(By.id(CANCEL_BUTTON_ID));
		cancelButton.click();
		assertEquals(driver.getCurrentUrl(),URL_DASHBOARD);
	}

}
