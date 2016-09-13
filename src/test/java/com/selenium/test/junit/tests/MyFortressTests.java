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

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.$;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyFortressTests {

    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    MyFortressPage myFortressPage;
    AssociatedUsersPage associatedUsersPage;
    AssociationPage associationPage;
    FRDPage frdPage;

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
        associatedUsersPage = new AssociatedUsersPage();
        frdPage = new FRDPage();
        mainPage.goToLoginPage();
        loginPage.login(TestsConfig.getConfig().getUsername(), TestsConfig.getConfig().getPassword());
    }

    @Test
    public void A_testFRDRestart() {
        myFortressPage.openPage();
        myFortressPage.restartDevice();
        dashboardPage.openPage();
        assertEquals("Disconnected", dashboardPage.getFRDStatus());
        do {
            TimeUtils.waitForSeconds(10);
        } while ("Disconnected".equals(dashboardPage.getFRDStatus()));
        assertEquals("Online", dashboardPage.getFRDStatus());
    }

    @Test
    public void B_testBlockPD() {
        myFortressPage.openPage();
        myFortressPage.pdDoAction("maria", "Disallow");
        assertEquals("Connected Blocked", myFortressPage.getPDstatus("maria"));
    }

    @Test
    public void C_testUnBlockPD() {
        myFortressPage.openPage();
        myFortressPage.pdDoAction("maria", "Allow");
        assertEquals("Connected", myFortressPage.getPDstatus("maria"));
    }

    @Test
    public void D_testDeleteAssosiatedFRDWithUser() {
        associatedUsersPage.openPage();
        associatedUsersPage.removeUser();
        dashboardPage.openPage();
        assertTrue("Assotion page is not loaded", associationPage.isPageOpened());

    }

    @Test
    public void E_testAssosiateFRDWithUser() {
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        String code = frdPage.getRegistrationCode();
        mainPage.switchTab();
        associationPage.associateFRD(code);
        assertTrue("FRD is not associated", dashboardPage.isPageOpened());
    }


    // @Test
    public void testUpdateFRDDetails() {

    }

    // @Test
    public void testSecurityModeChange() {

    }

    // @Test
    public void testActivityCountOnDashboard() {

    }

    // @Test
    public void testPrioritySet() {

    }

    // @Test
    public void testDevicesCountOnDashboard() {

    }
}
