package org.pesho.cf;

public class Main {

	public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        CFNavigator cfNavigator = new CFNavigator("C:\\Users\\pppep\\Downloads\\chromedriver_win32\\chromedriver.exe");
        cfNavigator.login("user", "password");
        String submissionId = cfNavigator.submit("321E", "C:\\Users\\pppep\\Downloads\\bobi.cpp");
        System.out.println("Submission id: " + submissionId);
        String verdict = cfNavigator.waitForVerdict("321E", submissionId);
        System.out.println("Verdict is: " + verdict);
        
        long endTime = System.currentTimeMillis();
        
        System.out.println(endTime-startTime);
        
//        if (driver instanceof JavascriptExecutor) {
//            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//a[@href=\"/problemset/submit\"]")));
//        }

	}
	
}
