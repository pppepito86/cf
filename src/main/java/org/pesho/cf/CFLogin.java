package org.pesho.cf;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CFLogin {

	public static final String LOGIN_URL = "https://codeforces.com/enter";
	private static final By USERNAME_FIELD = By.id("handleOrEmail");
	private static final By PASSWORD_FIELD = By.id("password");
	private static final By SUBMIT_BUTTON = By.xpath("//input[@type='submit']");
	
	public static void login(WebDriver driver, String username, String password) {
        driver.navigate().to(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON));
        
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(SUBMIT_BUTTON).submit();
        
        By expectedElement = By.xpath("//a[@href=\"/profile/"+username+"\"]");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
	}
	
}
