package org.pesho.cf;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CFResult {
	
	private static final int TIMEOUT_IN_SECONDS = 30;

	public static final String HISTORY_URL = "https://codeforces.com/problemset/status?my=on";
	private static final By LAST_SUBMISSION_ID_FIELD = By.xpath("//table/tbody/tr[2]/td[1]");
	
	public static final String SUBMISSION_URL = "https://codeforces.com/contest/%s/submission/%s";
	private static final By SUBMISSION_VERDICT_FIELD = By.xpath("//table/tbody/tr[2]/td[5]");

	public static String getLastSubmissionId(WebDriver driver) {
        driver.navigate().to(HISTORY_URL);
		new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(LAST_SUBMISSION_ID_FIELD));

        String submissionId = driver.findElement(LAST_SUBMISSION_ID_FIELD).getText();
        System.out.println("submission id: " + submissionId);
        return submissionId;
	}

	public static String getVerdict(WebDriver driver, String problemId, String submissionId) {
		String contestId = problemId.substring(0, problemId.length()-1);
        String submissionURL = String.format(SUBMISSION_URL,  contestId, submissionId);
        driver.navigate().to(submissionURL);
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(SUBMISSION_VERDICT_FIELD));

        String verdict = driver.findElement(SUBMISSION_VERDICT_FIELD).getText();
        System.out.println("verdict: " + verdict);
        return verdict;
	}

	public static String waitForVerdict(WebDriver driver, String problemId, String submissionId) {
		while (true) {
			String verdict = getVerdict(driver, problemId, submissionId);
			if (verdict == null) return verdict;
			if (isFinal(verdict)) return verdict;
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
	
	private static boolean isFinal(String verdict) {
		verdict = verdict.toLowerCase();
		return !verdict.contains("queuing") && !verdict.contains("running");
	}
	
	public static boolean isCorrect(String verdict) {
		verdict = verdict.toLowerCase();
		return "accepted".equals(verdict);
	}
	
}
