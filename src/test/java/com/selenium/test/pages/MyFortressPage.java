package com.selenium.test.pages;

import com.selenium.test.utils.TimeUtils;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

public class MyFortressPage extends BasePage {

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr/td[3]/button[1]")
    private WebElement showDevices;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr/td[4]/button[2]")
    private WebElement restartButton;

    @FindBy(xpath = "html/body/div[4]/div/div/div/div[3]/button[1]")
    private WebElement modalWindowRestart;

    @FindBys(@FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr[2]/td/div/table/tbody/tr"))
    private List<WebElement> rowCollection;


    public MyFortressPage() {
        super(true);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        try {
            WebElement con = getDriver().findElement(By.xpath(".//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/div[2]/table"));
            return con.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void restartDevice() {
        restartButton.click();
        TimeUtils.waitForElement(10, "html/body/div[4]/div/div/div");
        modalWindowRestart.click();
        TimeUtils.waitForElement(10, ".//*[@id='toast-container']/div");
        TimeUtils.waitForSecondsTread(5);
    }

    public void pdDoAction(String pdName, String action) {
        showDevices.click();
        TimeUtils.waitForElement(10, ".//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr[2]/td/div/table/tbody");

        rowCollection.stream().filter(rowElement -> rowElement.getText().contains(pdName)).forEach(rowElement -> {
            List<WebElement> colCollection = rowElement.findElements(By.xpath("td"));
            for (WebElement colElement : colCollection) {

                if (action.equals("Disallow") && colElement.getText().contains(action)) {
                    colElement.findElement(By.xpath("button[2]")).click();
                }

                if (action.equals("Allow") && colElement.getText().contains(action)) {
                    colElement.findElement(By.xpath("button[1]")).click();
                }

                if (action.equals("Remove") && colElement.getText().contains(action)) {
                    colElement.findElement(By.xpath("button[3]")).click();
                }

            }
        });
        TimeUtils.waitForSecondsTread(20);
    }

    public String getPDstatus(String pdName) {
        for (WebElement rowElement : rowCollection) {
            if (rowElement.getText().contains(pdName)) {
                List<WebElement> colCollection = rowElement.findElements(By.xpath("td"));
                return colCollection.get(5).getText();
            }
        }
        return null;
    }
}
