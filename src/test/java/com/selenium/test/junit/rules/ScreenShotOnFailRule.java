package com.selenium.test.junit.rules;

import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScreenShotOnFailRule implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    WebDriverFactory.takeScreenShot();;
                    throw t;
                }finally {
                    Logs logs = WebDriverFactory.getDriver().manage().logs();
                    LogEntries logEntries = logs.get(LogType.DRIVER);

                    for (LogEntry logEntry : logEntries) {
                        System.out.println(logEntry.getMessage());
                    }
                    WebDriverFactory.finishBrowser();
                }
            }
        };
    }
}


