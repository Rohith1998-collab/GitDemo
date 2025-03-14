package rohith.pageobjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LastPage {
	WebDriver driver;
	
	public LastPage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(tagName="select")
	WebElement country;
	@FindBy(css=".chkAgree")
	WebElement chkAgree;
	@FindBy(xpath="//button[text()='Proceed']")
	WebElement proceed;
	public void selectCountry()
	{
		
		WebElement ele=country;
		Select dropdown= new Select(ele);
		dropdown.selectByValue("India");
		chkAgree.click();
		proceed.click();
	}
	public void takeScreenshot() throws IOException
	{
		
		TakesScreenshot screen= (TakesScreenshot)driver;
		File file=screen.getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(file,new File("C:\\Users\\Rohith\\screenshot\\screenshot2.png"));
	}

}
