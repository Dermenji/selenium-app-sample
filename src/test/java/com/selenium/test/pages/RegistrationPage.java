package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;
import java.util.UUID;

public class RegistrationPage extends BasePage {

    @FindBy(xpath = ".//*/label")
    private WebElement agree;

    @FindBy(xpath = ".//*/form/div[1]/input")
    private WebElement userName;

    @FindBy(xpath = ".//*/form/div[2]/input")
    private WebElement emailInput;

    @FindBy(xpath = ".//*/form/div[3]/input")
    private WebElement passwordInput;

    @FindBy(xpath = ".//*/form/div[4]/input")
    private WebElement confirmPassword;

    @FindBy(xpath = ".//*/form/button")
    private WebElement generateButton;

    @FindBy(xpath = ".//*/form/div[2]/span[2]")
    private WebElement errorMessage;

    @FindBy(xpath = ".//*/form/a")
    private WebElement createAccountButton;


    public RegistrationPage() {
        super(false);
    }

    @Override
    public void openPage() {
        createAccountButton.click();
        TimeUtils.waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return agree.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String registerNewAccount() {
        String random = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        String emailText = "w" + random + "@sirma.com";
        System.out.println(emailText);
        userName.sendKeys(emailText);
        emailInput.sendKeys(emailText);
        passwordInput.sendKeys("Q!1qwerty");
        confirmPassword.sendKeys("Q!1qwerty");
        TimeUtils.waitForSecondsTread(5);
        generateButton.click();
        TimeUtils.waitForSecondsTread(5);
        return emailText;
    }

    public void registerNewAccount(String email, String password) {
        userName.sendKeys(email);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPassword.sendKeys(password);
        TimeUtils.waitForSecondsTread(5);
        generateButton.click();
        TimeUtils.waitForSecondsTread(5);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
