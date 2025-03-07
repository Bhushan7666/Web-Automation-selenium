package Demo;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

 
       public class OrangeHRM { 
 
       public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
       public WebDriver driver; 
      @BeforeTest 

       public void setup() 
 
      { 

        System.out.println("Before Test executed"); 

        // TODO Auto-generated method stub 
 
        driver=new ChromeDriver(); 
 
        //maximise windows 

        driver.manage().window().maximize();
		//open url 
		 
		driver.get(baseUrl); 
		 //timer i kept as 60 you can keep 40 
		 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds (60)); 
		
		} 
		
		@Test(priority = 2) 
		
		public void loginTestWithValidCredential() 
		 { 
		//find username and enter username "Admin" 
	
		driver.findElement(By.xpath("//input[@placeholder= 'Username']")).sendKeys("Admin"); 
		
		//find password and enter password admin123 
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123"); 
		
		//login button click 
		
		driver.findElement(By.xpath("//button[@type='submit']")).submit(); 
	
		// Verify if the login was successful by checking the page title 
		
		//Verify if the login was successful by checking the page title 
		
		String pageTitle = driver.getTitle(); 
		 
		/* if (pageTitle.equals("OrangeHRM")) { 
		
		System.out.println("Login successful!"); 
		} else { 
		System.out.println("Login failed!"); 
		 
		}*/
		
		Assert.assertEquals("OrangeHRM", pageTitle); 
		 
		
		} 
		 
		@Test(priority = 1) 
		
		public void doLoginWithInvalidCredential() throws InterruptedException
		{ 
	
		//find username and enter username "Admin" 
		
		
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin"); 
		
		//find password and enter invalid password 
		
	
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1234");//wrong password 
		//login button click 
		driver.findElement(By.xpath("//button[@type='submit']")).submit(); 
		String message_expected = "Invalid credentials"; 
		String message_actual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		
		//Assert.assertTrue(message_actual.contains (message_expected)); 
		Assert.assertEquals(message_expected, message_actual); 
		
		Thread.sleep(1500); 
		} 
		
		public void logout() throws InterruptedException 
		{ 
		
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click(); 
		 
		//driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click(); 
		
		List <WebElement> elementlist = driver.findElements (By.xpath("//a[@class='oxd-userdropdown-link']"));
		
		for (int i=0; i<elementlist.size(); i++) 
		{ 
		
		Thread.sleep(1000); 
		
		System.out.println(i + ":" + elementlist.get(i).getText()); 
		} 
		
		elementlist.get(3).click();//click on logout 
		} 
		
		@AfterTest 
		
		public void tearDown() throws InterruptedException 
		{ 
		
		logout(); 
		
				
		Thread.sleep(5000);//wait for 5 secs before quit 
		driver.close(); 
		
		driver.quit(); 
		     
		         }
       }
		  
