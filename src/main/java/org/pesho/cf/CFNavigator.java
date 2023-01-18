package org.pesho.cf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CFNavigator {

	private WebDriver driver;

	public CFNavigator(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);

		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		options.addArguments("--no-sandbox");
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
