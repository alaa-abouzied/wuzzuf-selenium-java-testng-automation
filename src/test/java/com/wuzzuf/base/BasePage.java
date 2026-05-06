package com.wuzzuf.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }



    public void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void selectReactOptionWithDelay(WebElement inputField, String typedText) {
        inputField.click();
        inputField.clear();
        inputField.sendKeys(typedText);

        try {
            Thread.sleep(1200); // ⏳ Wait for React to fully render dropdown
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inputField.sendKeys(Keys.ENTER); // ⌨️ Trigger the actual selection
    }

    public void selectReactInputFieldByIndex1(int index, String value) throws InterruptedException {
        // Wait for React input to appear
        Thread.sleep(1000);

        // Select the Nth input with aria-autocomplete="list"
        WebElement input = driver.findElements(By.xpath("//input[@aria-autocomplete='list']")).get(index);
        input.click();
        input.sendKeys(value);
        Thread.sleep(1500); // Wait for dropdown to appear
        input.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        input.sendKeys(Keys.ENTER);
    }
    public void selectReactDropdownOption(By inputLocator, String value) throws InterruptedException {
        WebElement input = driver.findElement(inputLocator);
        input.click();
        input.sendKeys(value);
        Thread.sleep(1000);
        input.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(300);
        input.sendKeys(Keys.ENTER);
    }
    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }





    public String getPageTitle() {
        return driver.getTitle();
    }
}
