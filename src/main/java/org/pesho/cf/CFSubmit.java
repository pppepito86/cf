package org.pesho.cf;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CFSubmit {

	public static final String SUBMIT_URL = "https://codeforces.com/problemset/submit";
	private static final By PROBLEM_ID_FIELD = By.name("submittedProblemCode");
	private static final By LANGUAGE_FIELD = By.name("programTypeId");
	private static final By SOURCE_FILE_FIELD = By.name("sourceFile");
	private static final By SUBMIT_BUTTON = By.xpath("//input[@type='submit']");

	private static final String C17_LANGUAGE_CODE = "54"; // GNU G++17 7.3.0
	private static final String C20_LANGUAGE_CODE = "73"; // GNU G++20 11.2.0 (64 bit, winlibs)

	public static void submit(WebDriver driver, String problemId, String file) {
		driver.navigate().to(SUBMIT_URL);
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON));

		driver.findElement(PROBLEM_ID_FIELD).sendKeys(problemId);
		new Select(driver.findElement(LANGUAGE_FIELD)).selectByValue(C17_LANGUAGE_CODE);
		driver.findElement(SOURCE_FILE_FIELD).sendKeys(file);
		driver.findElement(SUBMIT_BUTTON).submit();

		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.urlToBe("https://codeforces.com/problemset/status?my=on"));
	}

}
