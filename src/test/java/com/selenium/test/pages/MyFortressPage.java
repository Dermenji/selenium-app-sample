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

    @FindBy(xpath = ".//*/button[contains (text(), 'Show devices')]")
    private WebElement showDevices;

    @FindBy(xpath = ".//*/button[contains (text(), 'Restart')]")
    private WebElement restartButton;

    @FindBy(xpath = ".//*/button[contains (text(), 'Factory Reset')]")
    private WebElement factoryReset;

    @FindBy(xpath = "html/body/div[4]/div/div/div/div[3]/button[1]")
    private WebElement modalWindowRestart;

    @FindBys(@FindBy(xpath = ".//*/table[contains (@id, 'DataTables_Table_')]/tbody/tr"))
    private List<WebElement> rowCollection;

    @FindBy(xpath = ".//*[@id='side-menu']//a[contains (text(), 'My Fortress')]")
    private WebElement myFortress;

    @FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr/td[1]/div")
    private WebElement frdName;

    @FindBy(xpath = ".//*/table/tbody/tr/td[1]/form/div/input")
    private WebElement input;

    @FindBy(xpath = ".//*/button[contains(@type, 'submit')]")
    private WebElement inputSubmit;

    public MyFortressPage() {
        super(false);
    }

    @Override
    public void openPage() {
        myFortress.click();
        TimeUtils.waitForPageLoaded();
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
        clickResetAndWait();
        TimeUtils.waitForSecondsTread(10);
    }

    public void pdDoAction(String pdName, String action) {
        showDevices.click();
        TimeUtils.waitForElement(10, By.xpath(".//*/table[contains (@id, 'DataTables_Table_')]"));

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

        do {
            TimeUtils.waitForSecondsTread(10);
        } while (getPDstatus(pdName).equals("Waiting..."));

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

    public void factoryReset() {
        factoryReset.click();
        clickResetAndWait();
        TimeUtils.waitForSecondsTread(50);
    }

    private void clickResetAndWait() {
        TimeUtils.waitForElement(10, By.xpath("html/body/div[4]/div/div/div"));
        modalWindowRestart.click();
        TimeUtils.waitForElement(10, By.xpath(".//*[@id='toast-container']/div"));
    }

    public void editFRDname(String name) {
        frdName.click();
        TimeUtils.waitForElement(5, By.xpath(".//*/form/div/input"));
        input.clear();
        input.sendKeys(name);
        inputSubmit.click();
        TimeUtils.waitForSeconds(3);
    }

    public void editPDname(String name, String newName) {
        showDevices.click();
        TimeUtils.waitForElement(10, By.xpath(".//*/table[contains (@id, 'DataTables_Table_')]"));
        for (WebElement rowElement : rowCollection) {
            if (rowElement.getText().contains(name)) {
                List<WebElement> colCollection = rowElement.findElements(By.xpath("td"));
                colCollection.get(0).findElement(By.xpath("div")).click();
            }
        }
        TimeUtils.waitForElement(5, By.xpath(".//*/form/div/input"));
        input.clear();
        input.sendKeys(newName);
        inputSubmit.click();
        TimeUtils.waitForSeconds(3);
    }
}
