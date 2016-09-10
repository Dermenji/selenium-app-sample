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
import static org.junit.Assert.assertTrue;

public class MyFortressTests {

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
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
    }

    @Test
    public void testFRDRestart() {
        mainPage.goToMyFortressPage();
        myFortressPage.restartDevice();
        mainPage.goToDashBoardPage();
        assertEquals("Disconnected", dashboardPage.getFRDStatus());
        do {
            TimeUtils.waitForSeconds(10);
        } while ("Disconnected".equals(dashboardPage.getFRDStatus()));
        assertEquals("Online", dashboardPage.getFRDStatus());
    }

    @Test
    public void testBlockPD() {
        mainPage.goToMyFortressPage();
        myFortressPage.pdDoAction("maria", "Disallow");
        assertEquals("Connected Blocked", myFortressPage.getPDstatus("maria"));
    }

    @Test
    public void testUnBlockPD() {
        mainPage.goToMyFortressPage();
        myFortressPage.pdDoAction("maria", "Allow");
        assertEquals("Connected", myFortressPage.getPDstatus("maria"));
    }
}