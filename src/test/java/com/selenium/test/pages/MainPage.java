package com.selenium.test.pages;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    private static final String PAGE_URL = "http://test.portal.fortresstower.com";

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[1]/div/nav/ul/li[2]/a")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[1]/div/nav/ul/li[2]/ul/li[1]/a")
    private WebElement signOut;

    @FindBy(xpath = ".//*[@id='side-menu']/li[2]/ul/li[2]/a")
    private WebElement myFortress;

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
        return loginButton.isDisplayed();
    }

    public void goToLoginPage(){
        loginButton.click();
        TimeUtils.waitForPageLoaded();
    }

    public void logOut() {
        loginButton.click();
        TimeUtils.waitForSeconds(1);
        signOut.click();
        TimeUtils.waitForPageLoaded();
    }

    public void goToMyFortressPage() {
        myFortress.click();
        TimeUtils.waitForPageLoaded();
    }

    public void goToDashBoardPage() {
        dashBoard.click();
        TimeUtils.waitForPageLoaded();
    }
}
