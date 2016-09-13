package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FRDPage extends BasePage {

    @FindBy(id = "settingsIcon")
    private WebElement settingsIcon;

    @FindBy(xpath = "html/body")
    private WebElement mainPage;

    @FindBy(id = "next_button")
    private WebElement nextButton;

    @FindBy(id = "new_pin")
    private WebElement newPinCode;

    @FindBy(id = "zip_input")
    private WebElement zipInput;

    @FindBy(id = "phone_number")
    private WebElement phoneNumber;

    @FindBy(id = "confirm_pin")
    private WebElement confirmPinCode;

    @FindBy(id = "phone_prefix")
    private WebElement phonePrefix;

    @FindBy(id = "phone_area")
    private WebElement phoneArea;

    @FindBy(id = "low_button")
    private WebElement lowButton;

    @FindBy(id = "med_button")
    private WebElement medButton;

    @FindBy(id = "high_button")
    private WebElement highButton;

    @FindBy(id = "confirm_button")
    private WebElement confirmButton;

    @FindBy(id = "pin")
    private WebElement registrationCode;

    @FindBy(xpath= ".//*/td[contains (text(), 'registration code')]/../td[2]")
    private WebElement registrationCodeSettings;

	@FindBy(id = "mask")
	private WebElement mask;

	@FindBy(id = "kbd-input")
	private WebElement kbdInput;

	@FindBy(id = "unlock")
	private WebElement unlock;


    public FRDPage() {
        super(true);
    }

    @Override
    public void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        try {
            return mainPage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean isUnboxingPageOpened() {
        try {
            return nextButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickNext() {
        nextButton.click();
    }

    public void setUnboxingPinCode(String newPin, String oldPin) {
        newPinCode.sendKeys(newPin);
        confirmPinCode.sendKeys(oldPin);
    }

    public void setZipAndPhone(String zip, String area, String prefix, String number) {
        zipInput.sendKeys(zip);
        phoneArea.sendKeys(area);
        phonePrefix.sendKeys(prefix);
        phoneNumber.sendKeys(number);
    }

    public void setSecurityLevel(String level) {
        if (level.equals("low")) {
            lowButton.click();
        }
        if (level.equals("med")) {
            medButton.click();
        }
        if (level.equals("high")) {
            highButton.click();
        }

        confirmButton.click();
    }

    public String getRegistrationCodeUnboxing() {
        return registrationCode.getText();
    }

    public String getRegistrationCode() {
        lockOn();
        openSettingsScreen();
        return registrationCodeSettings.getText();
    }

    private void lockOn() {
        TimeUtils.waitForSecondsTread(5);
        if (mask.isDisplayed()){
            mask.click();
            kbdInput.sendKeys("1111");
            unlock.click();
        }
        TimeUtils.waitForSecondsTread(2);
    }

    private void openSettingsScreen() {
        settingsIcon.click();
        TimeUtils.waitForPageLoaded();
    }
}
