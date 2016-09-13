package com.selenium.test.junit.tests;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.DashboardPage;
import com.selenium.test.pages.LoginPage;
import com.selenium.test.pages.MainPage;
import com.selenium.test.pages.MyFortressPage;
import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MyFortressTestsAdmin {

    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    MyFortressPage myFortressPage;

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
        mainPage = new MainPage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        myFortressPage = new MyFortressPage();
        mainPage.goToLoginPage();
        loginPage.login("", "");
    }

    @Test
    public void testAddAdminRole() {

    }

    @Test
    public void testDeleteAdminRole() {

    }
}
