package servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	private static final String INTRODUCED_TEXTBOX_ID = "introduced";
	private static final String DISCONTINUED_TEXTBOX_ID = "discontinued";

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
		driver.quit();
		service.stop();
	}


	@Test
	void testCancelButton() {
		driver.get(URL_ADD_COMPUTER);
		WebElement cancelButton = driver.findElement(By.id(CANCEL_BUTTON_ID));
		cancelButton.click();
		assertEquals(driver.getCurrentUrl(),URL_DASHBOARD);
	}
	
	@Test
	void disableIsDiscontinuedAtStart() {
		driver.get(URL_ADD_COMPUTER);
		WebElement discontinuedBox = driver.findElement(By.id(DISCONTINUED_TEXTBOX_ID));
		assertTrue("true".equals(discontinuedBox.getAttribute("disabled")));
	}
	
	@Test
	void goodIntroducedEnableDiscontinued() {
		driver.get(URL_ADD_COMPUTER);
		WebElement introducedBox = driver.findElement(By.id(INTRODUCED_TEXTBOX_ID));
		introducedBox.sendKeys("12/12/1222");
		WebElement discontinuedBox = driver.findElement(By.id(DISCONTINUED_TEXTBOX_ID));
		assertEquals("false",discontinuedBox.getAttribute("disabled"));
	}
	
	@Test
	void badIntroducedDoNotEnableDiscontinued() {
		driver.get(URL_ADD_COMPUTER);
		WebElement introducedBox = driver.findElement(By.id(INTRODUCED_TEXTBOX_ID));
		introducedBox.sendKeys("12/112/2");
		WebElement discontinuedBox = driver.findElement(By.id(DISCONTINUED_TEXTBOX_ID));
		assertEquals("true",discontinuedBox.getAttribute("disabled"));
	}
	
	@Test
	void badIntroducedDisableDiscontinued() {
		driver.get(URL_ADD_COMPUTER);
		WebElement introducedBox = driver.findElement(By.id(INTRODUCED_TEXTBOX_ID));
		introducedBox.sendKeys("12/12/1222");
		WebElement discontinuedBox = driver.findElement(By.id(DISCONTINUED_TEXTBOX_ID));
		assertTrue("false".equals(discontinuedBox.getAttribute("disabled")));
		introducedBox.sendKeys("12111/1233/1222");
		assertTrue("true".equals(discontinuedBox.getAttribute("disabled")));
		
	}

}
