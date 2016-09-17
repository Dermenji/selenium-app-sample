package com.selenium.test.utils;

import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static void waitForSeconds(int timeoutInSeconds) {
        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }

    public static void waitForSecondsTread(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElement(int timeoutInSeconds, By by) {
        WebElement element = (new WebDriverWait(WebDriverFactory.getDriver(), timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForInvisibility(int timeoutInSeconds, By by) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitForPageLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getDriver();

        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            return;
        }

        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(1000);
                WebDriverFactory.getDriver().navigate().refresh();
                System.out.println("Try to load page");
            } catch (InterruptedException e) {
            }
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                System.out.println("Can not load page");
                break;
            }
        }
    }

}
