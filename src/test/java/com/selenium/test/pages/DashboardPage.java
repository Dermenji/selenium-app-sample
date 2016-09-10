package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

public class DashboardPage extends BasePage {

	@FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[3]/div[6]/div/div[2]/h1[1]")
	private WebElement status;


    public DashboardPage(){
        super(true);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {
            WebElement con = getDriver().findElement(By.id("side-menu"));
            return con.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getFRDStatus() {
        return status.getText();
    }
}
