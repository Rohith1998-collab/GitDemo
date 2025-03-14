package Rohith.tests;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Rohith.TestComponents.BaseTest;
import rohith.pageobjects.cartPage;
import rohith.pageobjects.productItemsPage;

public class productsValidation extends BaseTest {
	
	@Test
	public void verifyaddedProducts() throws IOException {
		List<String> productList=dataReader("C:\\Users\\Rohith\\my-app\\src\\main\\java\\rohith\\resources\\productsdata.csv");
		
		
		homepage.seachProducts();
		int c=homepage.getlistProducts(productList, 0);
		cartPage cartpage= new cartPage(driver);
		cartpage.addToCart();
		productItemsPage productitemspage=cartpage.proceedToCheckOut();
		List<WebElement>cartProducts=productitemspage.cartProducts();
		List<String> expectedList=cartProducts.stream().map(p->p.getText()).map(productText->productText.split("-")[0].trim()).collect(Collectors.toList());
		
		productList=productList.stream().sorted().toList();
		expectedList=expectedList.stream().sorted().toList();
		Assert.assertEquals(productList,expectedList);
		
		
	}
	@Test
	public void getallProducts() throws InterruptedException
	{
		homepage.seachProducts();
		Thread.sleep(3000);
		List<WebElement> products=homepage.getProducts();
		List<String> productsList=products.stream().map(WebElement::getText).map(p->p.split("-")[0].trim()).collect(Collectors.toList());
		System.out.println(productsList);
		
	}
	
	

}
