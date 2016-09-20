package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

public class DashboardPage extends BasePage {

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[3]/div[6]/div/div[2]/h1[1]")
    private WebElement status;

    @FindBy(xpath = ".//*[@id='side-menu']//a[contains(text(), 'Dashboard')]")
    private WebElement dashboardPage;

    @FindBy(xpath = ".//*[@id='page-wrapper']//p[contains(text(), 'Fortress device actions')]")
    private WebElement dashboardLabel;

    @FindBy(xpath = ".//*/button[contains(text(), 'Off')]")
    private WebElement offButton;

    @FindBy(xpath = ".//*/button[contains(text(), 'Low')]")
    private WebElement lowButton;

    @FindBy(xpath = ".//*/button[contains(text(), 'Medium')]")
    private WebElement mediumButton;

    @FindBy(xpath = ".//*/button[contains(text(), 'High')]")
    private WebElement highButton;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[3]/div[2]/div/div[2]/h1")
    private WebElement contectionsCount;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[3]/div[1]/div/div[2]/h1")
    private WebElement devicesCount;


    public DashboardPage() {
        super(false);
    }

    @Override
    public void openPage() {
        dashboardPage.click();
        TimeUtils.waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return dashboardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getFRDStatus() {
        return status.getText();
    }

    public void changeSecurityMode(String mode) {
        if (mode.equals("Off")){
            offButton.click();
        }
        if (mode.equals("Low")){
            lowButton.click();
        }
        if (mode.equals("Medium")){
            mediumButton.click();
        }
        if (mode.equals("High")){
            highButton.click();
        }

        TimeUtils.waitForSeconds(10);
    }

    public String getActivityCount() {
        return contectionsCount.getText();
    }

    public String getDevicesCount() {
        return devicesCount.getText();
    }
}
