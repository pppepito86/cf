package org.pesho.cf;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CFNavigator {

	private WebDriver driver;

	public CFNavigator(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);  
		
		ChromeOptions options = new ChromeOptions();
//		options.setHeadless(true);
		driver = new ChromeDriver(options);
	}

	public void login(String username, String password) {
		CFLogin.login(driver, username, password);
	}
	
	public String submit(String problemId, String file) {
		CFSubmit.submit(driver, problemId, file);
		return CFResult.getLastSubmissionId(driver);
	}
	
	public String waitForVerdict(String problemId, String submissionId) {
		return CFResult.waitForVerdict(driver, problemId, submissionId);
	}
	
}
