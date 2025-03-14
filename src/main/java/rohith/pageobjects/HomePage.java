package rohith.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath="//div/div/h4")
	List<WebElement> products;
	@FindBy(xpath="//div[@class='product-action']/button")
	List<WebElement> productlist;
	
	public void gotoLogin()
	{
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}
	
	public void seachProducts()
	{
	driver.findElement(By.cssSelector(".search-keyword")).sendKeys("c");
	}
	
	public List<WebElement> getProducts()
	{
	
	return products;
	}
	
	public int getlistProducts(List<String> productList,int count)
	{
		
		
	List<WebElement> products=	getProducts();
		for(int i=0;i<products.size();i++)
		{
			 String[] name=products.get(i).getText().split("-");
			 String newItem=name[0].trim();
			
			System.out.println(newItem);
			
			 
			 if(productList.contains(newItem))
			 {
				 
				 
				 productlist.get(i).click();
				 count++;
			 }
			 
		}
		return count;
		
	
	}
	
	

}
