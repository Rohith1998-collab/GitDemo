package rohith.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage {
	WebDriver driver;
	public cartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
			
	}
	
	@FindBy(css="img[alt='Cart']")
	WebElement cartclick;
	@FindBy(css=".cart-info table tbody tr:nth-child(1) td:nth-child(3)")
	WebElement counter;
	@FindBy(xpath="//button[text()='PROCEED TO CHECKOUT']")
	WebElement checkOut;
	
	public void addToCart()
	{
		
		cartclick.click();
	}
	
	public int productCounter()
	{
		
		int c=Integer.parseInt(counter.getText());
		return c;
	}
	public productItemsPage proceedToCheckOut()
	{
		checkOut.click();
		return  new productItemsPage(driver);
	}

}
