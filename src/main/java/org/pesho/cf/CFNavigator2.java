package org.pesho.cf;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CFNavigator2 {

	private WebDriver driver;

	public CFNavigator2(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);  
		
		ChromeOptions options = new ChromeOptions();
//		options.setHeadless(true);
		driver = new ChromeDriver(options);
	}

	public void login(String username, String password) {
        driver.navigate().to("http://www.codeforces.com");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Enter")));
        
        driver.findElement(By.partialLinkText("Enter")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
        
        driver.findElement(By.id("handleOrEmail")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"/profile/"+username+"\"]")));
	}
	
	public void submit(String problemId, String file) {
        driver.navigate().to("https://codeforces.com/problemset/submit");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"/problemset/submit\"]")));

        driver.findElement(By.name("submittedProblemCode")).sendKeys(problemId);
        Select select = new Select(driver.findElement(By.name("programTypeId")));
        select.selectByValue("54"); //GNU G++17 7.3.0
//        select.selectByValue("73"); //GNU G++20 11.2.0 (64 bit, winlibs)
        driver.findElement(By.name("sourceFile")).sendKeys(file);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]/th[1]"), "#"));
	}
	
	public void submitAlternative(String problemId, String file) {
		String contestId = problemId.substring(0, problemId.length()-1);
		String problemNumber = problemId.substring(problemId.length()-1);
		driver.navigate().to("https://codeforces.com/problemset/problem/"+contestId+"/"+problemNumber);
		driver.findElement(By.xpath("//a[@href=\"/problemset/submit\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
		
		Select select = new Select(driver.findElement(By.name("programTypeId")));
		select.selectByValue("54"); //73
		driver.findElement(By.name("sourceFile")).sendKeys(file);
		driver.findElement(By.xpath("//input[@type='submit']")).submit();
	}

	public String getLastVerdict() {
        driver.navigate().to("https://codeforces.com/problemset/status?my=on");
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[2]/td[1]")));

        String submissionId = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]")).getText();
        System.out.println("submission id: " + submissionId);
        
        String problemId = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText().split(" ")[0];
        String contestId = problemId.substring(0, problemId.length()-1);
        System.out.println("contest id: " + contestId);
        
        driver.navigate().to("https://codeforces.com/contest/" + contestId + "/submission/" + submissionId);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[2]/td[5]")));

        String verdict = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText();
        System.out.println("verdict: " + verdict);
        return verdict;
	}
	
}
