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


public class MyFortressTestsAdmin {

    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    MyFortressPage myFortressPage;
    UsersAdminPage usersAdminPage;

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
        mainPage = new MainPage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        myFortressPage = new MyFortressPage();
        usersAdminPage = new UsersAdminPage();
        mainPage.goToLoginPage();
        loginPage.login("adminlogin@te.st", "P@ssw0rd");
    }

    @Test
    public void testAddAdminRole() {
        usersAdminPage.openPage();
        usersAdminPage.findUser(TestsConfig.getConfig().getUsername());
        usersAdminPage.addAdminRole();
    }

    @Test
    public void testDeleteAdminRole() {
        usersAdminPage.openPage();
        usersAdminPage.findUser(TestsConfig.getConfig().getUsername());
        usersAdminPage.removeAdminRole();
    }
}
