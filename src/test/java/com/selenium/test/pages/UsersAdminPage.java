package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

public class UsersAdminPage extends BasePage {

    @FindBy(xpath = ".//*[@id='side-menu']//span[contains (text(), 'Users')]")
    private WebElement userMenu;

    @FindBy(xpath = ".//*[@id='side-menu']//a[contains (text(), 'Watchtower Users')]")
    private WebElement watchrowerUser;

    @FindBy(xpath = ".//*[@id='page-wrapper']//h1[contains (text(), 'Fortress Users')]")
    private WebElement headerPage;

    @FindBy(xpath = ".//*/input")
    private WebElement searchInput;

    @FindBy(xpath = ".//*/a[contains (text(), 'Details')]")
    private WebElement userDetails;

    @FindBy(xpath = ".//*/span[contains (text(), 'Add Admin role')]")
    private WebElement adminRoleButtonEnable;

    @FindBy(xpath = ".//*/span[contains (text(), 'Remove Admin role')]")
    private WebElement adminRoleButtonDisable;

    @FindBy(xpath = ".//*/div[contains (text(), 'Role: Admin')]")
    private WebElement adminRole;


    public UsersAdminPage() {
        super(false);
    }

    @Override
    public void openPage() {
        userMenu.click();
        TimeUtils.waitForSecondsTread(1);
        watchrowerUser.click();
        TimeUtils.waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return headerPage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void findUser(String username) {
        searchInput.sendKeys(username);
        TimeUtils.waitForSecondsTread(5);
        userDetails.click();
        TimeUtils.waitForPageLoaded();
    }

    public void addAdminRole() {
        adminRoleButtonEnable.click();
        TimeUtils.waitForElement(10, By.xpath("//*/div[contains (text(), 'Role: Admin')]"));
    }

    public void removeAdminRole() {
        adminRoleButtonDisable.click();
        TimeUtils.waitForInvisibility(10, adminRole);
    }
}
