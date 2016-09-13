package com.selenium.test.junit.tests;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.*;
import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnboxingTests {

    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    MyFortressPage myFortressPage;
    FRDPage frdPage;

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
        frdPage = new FRDPage();
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
    }

    @Test
    public void testFactoryReset() {
        mainPage.goToMyFortressPage();
        myFortressPage.factoryReset();
        mainPage.openNewTab("http://192.168.66.228:8081");
        assertTrue("Unboxing is not started", frdPage.isUnboxingPageOpened());
    }

    @Test
    public void testUnboxingProcess() {
        mainPage.openNewTab("http://192.168.66.228:8081");
        assertTrue("Unboxing is not started", frdPage.isUnboxingPageOpened());
        frdPage.clickNext();
        TimeUtils.waitForElementId(5, "wan_photo");
        frdPage.clickNext();
        TimeUtils.waitForElementId(5, "success_img");
        frdPage.clickNext();
        TimeUtils.waitForElementId(5, "wan_photo");
        frdPage.clickNext();
        TimeUtils.waitForElementId(5, "success_img");
        frdPage.clickNext();
        frdPage.setUnboxingPinCode("1111", "1111");
        frdPage.clickNext();
        TimeUtils.waitForElementId(5, "info_single");
        frdPage.setZipAndPhone("11111", "111", "111", "1111");
        frdPage.clickNext();
        TimeUtils.waitForElementId(5, "confirm_button");
        frdPage.setSecurityLevel("med");
        TimeUtils.waitForElementId(5, "pin");
        registrationCode = frdPage.getRegistrationCode();
        frdPage.clickNext();
        TimeUtils.waitForElementId(10, "mask");
        mainPage.switchTab();
        myFortressPage.associateFRD(registrationCode);
        assertTrue("FRD is not associated", dashboardPage.isPageOpened());
    }

}
