package acadmianTask;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookMyShow 
{
	public static void signInBookMyShowUsingYopmail() throws Exception 
    {
        /*---------------------------- Open BookMyShow url --------------------------------*/
		String URL = "https://in.bookmyshow.com/explore/home";
		
		System.setProperty("webdriver.chrome.driver","F:\\SELENIUM - jarfiles\\ChromeDriver\\chromedriver-win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.get(URL);
	    /*----------------------------------------------------------------------------------*/
		
		/*----- Selecting Bengluru & clicking on Signin button & selecting the (Continue with Email) option &
		 puting the email (selauto@yopmail.com) to the input field and then click on continue -------------*/
		
		driver.findElement(By.xpath("//ul[@class='bwc__sc-ttnkwg-15 gZrltM']/li//img[@alt='BANG']")).click();
		
		driver.findElement(By.xpath("//div[text()='Sign in']")).click();
          
		driver.findElement(By.xpath("//div[contains(text(),'Continue with Email')]")).click();

		String emailAdrs = "selauto@yopmail.com";
		driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys(emailAdrs);
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		
		/*--------------------------------------------------------------------------------------------------*/

		/*------- Opening the new tab with (yopmail.com) url and performing operation on the new tab ----------*/
		((JavascriptExecutor)driver).executeScript("window.open('https://yopmail.com/')");
	
		String crl = driver.getCurrentUrl();
		System.out.println(crl);
		
		Set<String> windowHandles = driver.getWindowHandles();
		String[] switchtab = windowHandles.toArray(new String[windowHandles.size()]);
		driver.switchTo().window(switchtab[1]);
		
		String crl1 = driver.getCurrentUrl();
		System.out.println(crl1);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Enter your inbox here']")).sendKeys("selauto@yopmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Check Inbox @yopmail.com']")).click();
		/*--------------------------------------------------------------------------------------------------------*/
		
	    /*-------------------------------------------------- Getting the otp ------------------------------------*/
		Thread.sleep(3000);
		WebElement otpEmail = driver.findElement(By.xpath("//td[text()=' Please enter the below OTP to continue to BookMyShow.']//..//following-sibling::tr//following-sibling::tbody//tr//td"));
		String emailContent = otpEmail.getText();
		System.out.println(emailContent);
		
		Set<String> windowHandlesbms = driver.getWindowHandles();
		String[] switchtabtobms = windowHandlesbms.toArray(new String[windowHandlesbms.size()]);
		driver.switchTo().window(switchtab[0]);
	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@maxlength='6']")).sendKeys(emailContent);
		/*--------------------------------------------------------------------------------------------------------*/
    }
    
    public static void main(String[] args) throws Exception 
     {
		BookMyShow bs = new BookMyShow();
		bs.signInBookMyShowUsingYopmail();
	 }
}
