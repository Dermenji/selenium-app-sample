package com.selenium.test.junit.tests;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.*;
import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnboxingTests {

    @FindBy(id = "wan_photo")
    private WebElement wan_photo;

    @FindBy(id = "success_img")
    private WebElement success_img;

    @FindBy(id = "info_single")
    private WebElement info_single;

    @FindBy(id = "confirm_button")
    private WebElement confirm_button;

    @FindBy(id = "pin")
    private WebElement pin;

    @FindBy(id = "mask")
    private WebElement mask;

    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    MyFortressPage myFortressPage;
    FRDPage frdPage;
    AssociationPage associationPage;

    String registrationCode;

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
        mainPage = new MainPage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        myFortressPage = new MyFortressPage();
        associationPage = new AssociationPage();
        frdPage = new FRDPage();
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
    }

    @Test
    public void A_testFactoryReset() {
        myFortressPage.openPage();
        myFortressPage.factoryReset();
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        assertTrue("Unboxing is not started", frdPage.isUnboxingPageOpened());
    }

    @Test
    public void B_testUnboxingProcess() {
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        assertTrue("Unboxing is not started", frdPage.isUnboxingPageOpened());
        frdPage.clickNext();
        TimeUtils.waitForElement(5, By.id("wan_photo"));
        frdPage.clickNext();
        TimeUtils.waitForElement(5, By.id("success_img"));
        frdPage.clickNext();
        TimeUtils.waitForElement(5,By.id("wan_photo"));
        frdPage.clickNext();
        TimeUtils.waitForElement(5, By.id("success_img"));
        frdPage.clickNext();
        frdPage.setUnboxingPinCode("1111", "1111");
        frdPage.clickNext();
        TimeUtils.waitForElement(5, By.id("info_single"));
        frdPage.setZipAndPhone("11111", "111", "111", "1111");
        frdPage.clickNext();
        TimeUtils.waitForElement(5, By.id("confirm_button"));
        frdPage.setSecurityLevel("med");
        TimeUtils.waitForElement(5, By.id("pin"));
        registrationCode = frdPage.getRegistrationCodeUnboxing();
        frdPage.clickNext();
        TimeUtils.waitForElement(10, By.id("mask"));
        mainPage.switchTab();
        associationPage.associateFRD(registrationCode);
        assertTrue("FRD is not associated", dashboardPage.isPageOpened());
    }
}
