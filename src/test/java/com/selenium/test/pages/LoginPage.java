package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

public class LoginPage extends BasePage {

    @FindBy(xpath = ".//input[contains (@type, 'email')]")
    private WebElement userName;

    @FindBy(xpath = ".//input[contains (@type, 'password')]")
    private WebElement password;

    @FindBy(xpath = ".//*/button")
    private WebElement loginButton;

    @FindBy(xpath = ".//*/form/span")
    private WebElement errorMessage;

    public LoginPage() {
        super(false);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {
            return loginButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void login(String user, String pass) {
        userName.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        TimeUtils.waitForSecondsTread(5);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }


}
