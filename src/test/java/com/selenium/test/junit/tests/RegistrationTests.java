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

public class RegistrationTests {

    MainPage mainPage;
    LoginPage loginPage;
    AssociationPage associationPage;
    RegistrationPage registrationPage;

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
        mainPage = new MainPage();
        loginPage = new LoginPage();
        associationPage = new AssociationPage();
        registrationPage = new RegistrationPage();
    }

    @Test
    public void testRegisterNewAccountAndLogin() {
        mainPage.goToLoginPage();
        loginPage.goToRegistrationPage();
        String email = registrationPage.registerNewAccount();
        loginPage.login(email, TestsConfig.getConfig().getPassword());
        assertTrue("User can not login", associationPage.isPageOpened());
    }

    @Test
    public void testRegisterNewAccountWithAlreadyRegisteredUser() {
        mainPage.goToLoginPage();
        loginPage.goToRegistrationPage();
        registrationPage.registerNewAccount(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
        assertEquals("This username is already taken!", registrationPage.getErrorMessage());
    }
}
