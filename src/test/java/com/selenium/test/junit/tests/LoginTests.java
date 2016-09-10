package com.selenium.test.junit.tests;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.DashboardPage;
import com.selenium.test.pages.LoginPage;
import com.selenium.test.pages.MainPage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTests {

    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
        mainPage = new MainPage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @Test
    public void testLoginWithUserRole() {
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
        assertTrue("User can not login", dashboardPage.isPageOpened());
    }

    @Test
    public void testLoginWithAdminRole() {
        mainPage.goToLoginPage();
        loginPage.login("adminlogin@te.st", "P@ssw0rd");
        assertTrue("User can not login", dashboardPage.isPageOpened());
    }

    @Test
    public void testLoginWithWrongUsername() {
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername() + 1, TestsConfig.getConfig().getPassword());
        assertEquals("The user name or password is incorrect.", loginPage.getErrorMessage());
    }

    @Test
    public void testLoginWithWrongPassWord() {
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword() + 1);
        assertEquals("The user name or password is incorrect.", loginPage.getErrorMessage());
    }

    @Test
    public void testLoginOut() {
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
        mainPage.logOut();
        assertTrue("User can not logout", loginPage.isPageOpened());
    }
}
