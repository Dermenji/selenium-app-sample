package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

public class AssociationPage extends BasePage {

    @FindBy(xpath = ".//*[@id='side-menu']/li[2]/ul/li[5]/a")
    private WebElement associationPage;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div/div/div/div/div/form/div/input")
    private WebElement registrationInput;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div/div/div/div/div/form/button")
    private WebElement registrationButton;


    public AssociationPage() {
        super(true);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {
            WebElement con = getDriver().findElement(By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div/div/div/div/form/div/input"));
            return con.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void associateFRD(String code) {
        getDriver().navigate().refresh();
        TimeUtils.waitForElement(10, ".//*[@id='page-wrapper']/div[2]/div/div/div/div");
        registrationInput.sendKeys(code);
        TimeUtils.waitForSecondsTread(3);
        registrationButton.click();
        TimeUtils.waitForPageLoaded();
    }
}
