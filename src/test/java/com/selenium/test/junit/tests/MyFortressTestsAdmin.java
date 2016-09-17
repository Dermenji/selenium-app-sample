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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void A_testAddAdminRole() {
        usersAdminPage.openPage();
        usersAdminPage.findUser(TestsConfig.getConfig().getUsername());
        usersAdminPage.addAdminRole();
    }

    @Test
    public void B_testDeleteAdminRole() {
        usersAdminPage.openPage();
        usersAdminPage.findUser(TestsConfig.getConfig().getUsername());
        usersAdminPage.removeAdminRole();
    }

    @Test
    public void C_testDisableUser() {
        usersAdminPage.openPage();
        usersAdminPage.findUser(TestsConfig.getConfig().getUsername());
        usersAdminPage.disableUser();
        mainPage.logOut();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
        assertEquals("The user is disabled from system administrator.", loginPage.getErrorMessage());
    }

    @Test
    public void D_testEnableUser() {
        usersAdminPage.openPage();
        usersAdminPage.findUser(TestsConfig.getConfig().getUsername());
        usersAdminPage.enableUser();
        mainPage.logOut();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
        assertTrue("User can not login", dashboardPage.isPageOpened());
    }
}
