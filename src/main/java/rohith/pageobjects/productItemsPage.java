package rohith.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rohith.Abstract.Abstract;

public class productItemsPage extends Abstract {
	WebDriver driver;
	
	public productItemsPage(WebDriver driver)
	{
		super(driver);
		this .driver= driver;	
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='products']/table/tbody/tr/td[2]/p")
	List<WebElement> cartProducts;
	@FindBy(xpath="//div[@class='products']/table/tbody/tr/td[5]/p")
	List<WebElement> totalvalues;
	@FindBy(css=".promoCode")
	WebElement promocode;
	@FindBy(css=".promoBtn")
	WebElement promoButton;
	
	@FindBy(css=".discountPerc")
	WebElement discountPerc;
	@FindBy(css=".totAmt")
	WebElement totAmt;
	@FindBy(css=".discountAmt")
	WebElement discountAmt;
	@FindBy(xpath="//button[text()='Place Order']")
	WebElement placeOrder;
	By promoInfo= By.cssSelector(".promoInfo");
	public List<WebElement> cartProducts()
	{
	return cartProducts;
	}
	
	public boolean matchProducts(List<String> a)
	{
		boolean match=cartProducts().stream()
				.map(WebElement::getText)
				.map(productText->productText.split("-")[0].trim())
				.allMatch(productText -> a.contains(productText));
		return match;
	}
	
	public List<WebElement> productCostList()
	{
		return totalvalues;
	}
	
	public int productCost()
	{
		
		int total=productCostList().stream().map(totalvalue->totalvalue.getText()).mapToInt(Integer::parseInt).sum();
		return total;
	}
	
	public void sendPromoCode(String promotioncode)
	{
		promocode.sendKeys(promotioncode);
	
	}
	
	public void applyPromoButton()
	{
		
		promoButton.click();
	}
	
	public String discount()
	{
		waitForElementtoAppear(promoInfo);
		 String disc=discountPerc.getText();
		 return disc;
	}
	
	public int totalAmount()
	{
		int value=Integer.parseInt((totAmt.getText()));
		return value;
	}
	public double totalafterDiscount()
	{
		
		double expectedamount=	Double.parseDouble(discountAmt.getText());
		return expectedamount;
	}
	public LastPage placeOrder()
	{
		
		placeOrder.click();
		return new LastPage(driver);
	}

	
	

}
