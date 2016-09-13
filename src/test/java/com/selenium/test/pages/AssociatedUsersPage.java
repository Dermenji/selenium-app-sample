package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

public class AssociatedUsersPage extends BasePage {

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[2]/div[2]/div[1]/a[2]")
    private WebElement associatedUsers;

    @FindBy(xpath = "//*/th[contains(text(), 'Remove')]")
    private WebElement removeLabel;

    @FindBy(xpath = "//*/button[contains(text(), 'Remove')]")
    private WebElement removeButton;

    public AssociatedUsersPage() {
        super(false);
    }

    @Override
    public void openPage() {
        associatedUsers.click();
        TimeUtils.waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return removeLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void removeUser() {
        removeButton.click();
    }
}
