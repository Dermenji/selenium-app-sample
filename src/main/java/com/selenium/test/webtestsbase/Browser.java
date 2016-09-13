package com.selenium.test.webtestsbase;

public enum Browser {
    FIREFOX("firefox"),
    CHROME("chrome"),
    IE10("ie10"),
    SAFARI("safari");
    private String browserName;

    private Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static Browser getByName(String name) {
        for (Browser browser : values()) {
            if (browser.getBrowserName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        return null;
    }


}
