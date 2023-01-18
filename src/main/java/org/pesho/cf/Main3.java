package org.pesho.cf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Main3 {

	public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pppep\\Downloads\\chromedriver_win32\\chromedriver.exe");  
        
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        
        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://www.codeforces.com");
        
        driver.findElement(By.partialLinkText("Enter")).click();
        
        driver.findElement(By.id("handleOrEmail")).sendKeys("pppepito86");
        driver.findElement(By.id("password")).sendKeys("Cf88482925");
        
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        Thread.sleep(3000);
        
        driver.navigate().to("https://codeforces.com/problemset/problem/321/E");
        driver.findElement(By.xpath("//a[@href=\"/problemset/submit\"]")).click();
        Thread.sleep(3000);
        
        Select select = new Select(driver.findElement(By.name("programTypeId")));
        select.selectByValue("54"); //73
        driver.findElement(By.name("sourceFile")).sendKeys("C:\\Users\\pppep\\OneDrive\\Desktop\\nikola.cpp");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        
//        if (driver instanceof JavascriptExecutor) {
//            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//a[@href=\"/problemset/submit\"]")));
//        }

	}
	
}
