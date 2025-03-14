package Rohith.tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SubmitOrderE2E {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		String [] names= {"Brocolli","Cauliflower","Corn"};
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.findElement(By.cssSelector(".search-keyword")).sendKeys("c");
		//Actions action= new Actions(driver);
		//action.moveToElement(driver.findElement(By.cssSelector(".search-keyword"))).click().keyDown(Keys.SHIFT).sendKeys("b").sendKeys(Keys.ENTER).build().perform();
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/h4")));
		Thread.sleep(3000);
List<WebElement> products=driver.findElements(By.xpath("//div/div/h4"));
List a=Arrays.asList(names);

		int count=0;
		for(int i=0;i<products.size();i++)
		{
			 String[] name=products.get(i).getText().split("-");
			 String newItem=name[0].trim();
			 System.out.println(newItem);
			
			
			 
			 if(a.contains(newItem))
			 {
				 
				 driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				 count++;
			 }
			 
		}
		
		System.out.println(count);
		
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		int c=Integer.parseInt(driver.findElement(By.cssSelector(".cart-info table tbody tr:nth-child(1) td:nth-child(3)")).getText());
		Assert.assertEquals(count, c);
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class='products']/table/tbody/tr/td[2]/p"));
		boolean match=cartProducts.stream()
		.map(WebElement::getText)
		.map(productText->productText.split("-")[0].trim())
		.allMatch(productText -> a.contains(productText));
		
		System.out.println(match);
	List<WebElement> totalvalues=driver.findElements(By.xpath("//div[@class='products']/table/tbody/tr/td[5]/p"));
	int total=totalvalues.stream().map(totalvalue->totalvalue.getText()).mapToInt(Integer::parseInt).sum();
	  driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
		// Actions action = new Actions(driver);
		 //action.moveToElement(driver.findElement(By.cssSelector(".promoCode"))).click().sendKeys("rahulshettyacademy").sendKeys(Keys.ENTER).build().perform();
		 driver.findElement(By.cssSelector(".promoBtn")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
		 String disc=driver.findElement(By.cssSelector(".discountPerc")).getText();
		int value=Integer.parseInt((driver.findElement(By.cssSelector(".totAmt")).getText()));
		
		
		int disc1=Integer.parseInt(disc.replace("%","").trim());
		double Toatldisc= value*(disc1/100.0);
		double TotalamountAfterdisc= value-Toatldisc;
		System.out.println(TotalamountAfterdisc);
	double expectedamount=	Double.parseDouble(driver.findElement(By.cssSelector(".discountAmt")).getText());
		Assert.assertEquals(expectedamount, TotalamountAfterdisc);
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		WebElement ele=driver.findElement(By.tagName("select"));
		Select dropdown= new Select(ele);
		dropdown.selectByValue("India");
		driver.findElement(By.cssSelector(".chkAgree")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		
		TakesScreenshot screen= (TakesScreenshot)driver;
		File file=screen.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,new File("C:\\Users\\Rohith\\screenshot\\screenshot.png"));
		
		
	}

}