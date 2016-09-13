package com.selenium.test.configuration;

import com.selenium.test.configuration.properties.PropertiesLoader;
import com.selenium.test.configuration.properties.Property;
import com.selenium.test.configuration.properties.PropertyFile;
import com.selenium.test.webtestsbase.Browser;

@PropertyFile("config.properties")
public class TestsConfig {

    private static TestsConfig config;

    public static TestsConfig getConfig() {
        if (config == null) {
            config = new TestsConfig();
        }
        return config;
    }

    public TestsConfig() {
        PropertiesLoader.populate(this);
    }

    @Property("browser.name")
    private String browser = "firefox";

    @Property("browser.version")
    private String version = "";

    @Property("username")
    private String username = "";

    @Property("password")
    private String password = "";

    @Property("frd.url")
    private String frdUrl = "";



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFrdUrl() {
        return frdUrl;
    }

    public Browser getBrowser() {
        Browser browserForTests = Browser.getByName(browser);
        if (browserForTests != null) {
            return browserForTests;
        } else {
            throw new IllegalStateException("Browser name '" + browser + "' is not valid");
        }
    }

    public String getBrowserVersion() {
        return version;
    }
}
