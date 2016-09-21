package com.selenium.test.junit.tests;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.*;
import com.selenium.test.utils.RestDeviceClient;
import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.*;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void beforeClass() {
        String id = RestDeviceClient.addPD();
        RestDeviceClient.changeStatus(id, "192.168.66.111", "true");
    }

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
    public void A_testBlockPD() {
        myFortressPage.openPage();
        myFortressPage.pdDoAction("Test", "Disallow");
        assertEquals("Connected Blocked", myFortressPage.getPDstatus("Test"));
    }

    @Test
    public void B_testUnBlockPD() {
        myFortressPage.openPage();
        myFortressPage.pdDoAction("Test", "Allow");
        assertEquals("Connected", myFortressPage.getPDstatus("Test"));
    }

    @Test
    public void C_testDeleteAssosiatedFRDWithUser() {
        associatedUsersPage.openPage();
        associatedUsersPage.removeUser();
        dashboardPage.openPage();
        assertTrue("Assotion page is not loaded", associationPage.isPageOpened());

    }

    @Test
    public void D_testAssosiateFRDWithUser() {
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        String code = frdPage.getRegistrationCode();
        mainPage.switchTab();
        associationPage.associateFRD(code);
        assertTrue("FRD is not associated", dashboardPage.isPageOpened());
    }

    @Test
    public void E_testSecurityModeChange() {
        dashboardPage.changeSecurityMode("Low");
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        String mode = frdPage.getSecurityMode();
        assertEquals("LOW", mode);
    }

    @Test
    public void F_testActivityCountOnDashboard() {
        String dashActivity = dashboardPage.getActivityCount();
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        String frdActivity = frdPage.getActivityCount();
        assertEquals(frdActivity, dashActivity);
    }

    @Test
    public void G_testDevicesCountOnDashboard() {
        String dashDevices = dashboardPage.getDevicesCount();
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        String frdDevices = frdPage.getDevicesCount();
        assertEquals(dashDevices, frdDevices);
    }

    @Test
    public void H_testChangeFRDname() {
        myFortressPage.openPage();
        myFortressPage.editFRDname("Test");
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        String fbName = frdPage.getFBname();
        assertEquals("Test", fbName);
    }

    @Test
    public void I_testChangePDname() {
        myFortressPage.openPage();
        myFortressPage.editPDname("Test", "newTest");
        mainPage.openNewTab(TestsConfig.getConfig().getFrdUrl());
        assertTrue("PD is not renamed", frdPage.isPDinTheList("newTest"));
    }

    @Test
    public void J_testFRDRestart() {
        myFortressPage.openPage();
        myFortressPage.restartDevice();
        dashboardPage.openPage();
        assertEquals("Disconnected", dashboardPage.getFRDStatus());
        do {
            TimeUtils.waitForSeconds(10);
        } while ("Disconnected".equals(dashboardPage.getFRDStatus()));
        assertEquals("Online", dashboardPage.getFRDStatus());
    }

    //@Test
    public void F_testUpdateFRDDetails() {
    }

    // @Test
    public void I_testPrioritySet() {
    }
}
