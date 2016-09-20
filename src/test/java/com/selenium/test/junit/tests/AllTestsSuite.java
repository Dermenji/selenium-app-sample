package com.selenium.test.junit.tests;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import junit.framework.TestSuite;
import junit.framework.Test;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTests.class,
        RegistrationTests.class,
        MyFortressTestsAdmin.class,
        MyFortressTests.class,
        UnboxingTests.class
})
public class AllTestsSuite {
}
