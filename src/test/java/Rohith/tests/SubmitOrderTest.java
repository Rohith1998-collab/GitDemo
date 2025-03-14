package Rohith.tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Rohith.TestComponents.BaseTest;
import rohith.pageobjects.HomePage;
import rohith.pageobjects.LastPage;
import rohith.pageobjects.cartPage;
import rohith.pageobjects.productItemsPage;

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
public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider="giveData",groups="smoke")
	public void submitTest(String promotioncode ) throws InterruptedException, IOException {
		
		//List<String> productList=dataReader("C:\\Users\\Rohith\\my-app\\src\\main\\java\\rohith\\resources\\productsdata.csv");
		List<String> productList=readdatafromJson("C:\\Users\\Rohith\\my-app\\src\\main\\java\\rohith\\resources\\products.json");
		
		int count=0;
		
		homepage.seachProducts();
		int expectedcount=homepage.getlistProducts(productList, 0);
		Thread.sleep(2000);
		cartPage cartpage= new cartPage(driver);
		cartpage.addToCart();
		int actualcount=cartpage.productCounter();
		System.out.println("The count of the products added is "+expectedcount);
		Assert.assertEquals(expectedcount, actualcount);
		productItemsPage productitemspage=cartpage.proceedToCheckOut();
		boolean match=productitemspage.matchProducts(productList);
		System.out.println(match);
		int total=productitemspage.productCost();
		productitemspage.sendPromoCode(promotioncode);
		productitemspage.applyPromoButton();
		int value=productitemspage.totalAmount();
		String disc=productitemspage.discount();
		
		System.out.println(disc);
		System.out.println(value);
		 	
		int disc1=Integer.parseInt(disc.replace("%","").trim());
		double Toatldisc= value*(disc1/100.0);
		double TotalamountAfterdisc= value-Toatldisc;
		System.out.println(TotalamountAfterdisc);
		double expectedamount=productitemspage.totalafterDiscount();
	
		Assert.assertEquals(expectedamount, TotalamountAfterdisc);
		LastPage lastpage=	productitemspage.placeOrder();
		lastpage.selectCountry();
		lastpage.takeScreenshot();
		
		
		
	}
    
    @DataProvider
    public String[] giveData()
    {
    	
    	//return new Object[][] { { "rahulshettyacademy" }};
    	return new String[] {"cucmber","corn"};
    }

}