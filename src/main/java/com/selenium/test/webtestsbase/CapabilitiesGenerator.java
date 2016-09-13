package com.selenium.test.webtestsbase;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class CapabilitiesGenerator {

    public static DesiredCapabilities getDefaultCapabilities(Browser browser) {
        switch (browser) {
            case FIREFOX:
                LoggingPreferences logs = new LoggingPreferences();
                logs.enable(LogType.BROWSER, Level.OFF);
                logs.enable(LogType.CLIENT, Level.SEVERE);
                logs.enable(LogType.DRIVER, Level.WARNING);
                logs.enable(LogType.PERFORMANCE, Level.INFO);
                logs.enable(LogType.SERVER, Level.ALL);

                DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
                // DesiredCapabilities.firefox()
                return desiredCapabilities;
            case CHROME:
                if (System.getProperty("webdriver.chrome.driver") == null) {
                    throw new IllegalStateException("System variable 'webdriver.chrome.driver' should be set to path for executable driver");
                }
                return DesiredCapabilities.chrome();
            case IE10:
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setVersion("10");
                return caps;
            case SAFARI:
                return new DesiredCapabilities();
            default:
                throw new IllegalStateException("Browser is not supported");
        }
    }
}
