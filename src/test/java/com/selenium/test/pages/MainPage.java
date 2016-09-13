package com.selenium.test.pages;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MainPage extends BasePage {

    private static final String PAGE_URL = "http://test.portal.fortresstower.com";

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[1]/div/nav/ul/li[2]/a")
    private WebElement loginButton;

    @FindBy(id = "page-wrapper")
    private WebElement mainLabel;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[1]/div/nav/ul/li[2]/ul/li[1]/a")
    private WebElement signOut;


    @FindBy(xpath = ".//*[@id='side-menu']/li[2]/ul/li[1]/a")
    private WebElement dashBoard;

    public MainPage() {
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
        getDriver().manage().window().maximize();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return mainLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void goToLoginPage() {
        loginButton.click();
        TimeUtils.waitForPageLoaded();
    }

    public void logOut() {
        loginButton.click();
        TimeUtils.waitForSeconds(1);
        signOut.click();
        TimeUtils.waitForPageLoaded();
    }

    public void openNewTab(String url) {
        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        getDriver().get(url);
        TimeUtils.waitForPageLoaded();
    }

    public void switchTab() {
        Actions action = new Actions(getDriver());
        action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
    }

}
