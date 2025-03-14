package Rohith.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rohith.pageobjects.HomePage;
import rohith.pageobjects.cartPage;

public class BaseTest {
      

	public WebDriver driver;
      public HomePage homepage;

	public static void main(String[] args) {
		

	}
	
	public WebDriver driverInitialization() throws IOException
	{
		
		Properties properties= new Properties();
		FileInputStream file= new FileInputStream("C:\\Users\\Rohith\\my-app\\src\\main\\java\\rohith\\resources\\Global.properties");
		properties.load(file);
		//C:\Users\Rohith\my-app\src\main\java\rohith\resources\Global.properties
		String browserName=properties.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			 driver= new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			
			driver= new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public HomePage  launchApplication() throws IOException
	{
		
		driver=driverInitialization();
		 homepage= new HomePage(driver);
		homepage.gotoLogin();
		return homepage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void close()
	{
		
		driver.close();
	}
	
	public List<String> dataReader(String filePth) throws IOException
	{
		 List<String> products = new ArrayList<>();
		 
		 List<String> lines= Files.readAllLines(Paths.get(filePth));
		 for (String line : lines) {
	            // Split the line by commas (or use tab if necessary "\t")
	            String[] productArray = line.split(",");  // Use "\t" for tab-delimited files
	            
	            // Add each product to the list (trim whitespace)
	            for (String product : productArray) {
	                products.add(product.trim());
	            }
	        }
		 
		 return products;
		
	}
	
	public List<String> readdatafromJson(String filePth) throws StreamReadException, DatabindException, IOException
	{
		ObjectMapper objectmapper= new ObjectMapper();
        List<String> productList = objectmapper.readValue(Paths.get(filePth).toFile(), List.class);
        return productList;
		
		
	}

}
