package org.pesho.cf;

public class Main2 {

	public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        CFNavigator2 cfNavigator = new CFNavigator2("C:\\Users\\pppep\\Downloads\\chromedriver_win32\\chromedriver.exe");
        cfNavigator.login("pppepito86", "Cf88482925");
        cfNavigator.submit("321E", "C:\\Users\\pppep\\Downloads\\bobi.cpp");
        cfNavigator.getLastVerdict();
        long endTime = System.currentTimeMillis();
        
        System.out.println(endTime-startTime);
        
//        if (driver instanceof JavascriptExecutor) {
//            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//a[@href=\"/problemset/submit\"]")));
//        }

	}
	
}
