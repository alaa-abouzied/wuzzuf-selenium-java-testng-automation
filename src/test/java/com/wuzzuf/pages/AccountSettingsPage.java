package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountSettingsPage extends BasePage {

    public AccountSettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'e1j4u6fw0')]/div")
    private WebElement profileIconElement;

    @FindBy(xpath = "//a[@href='/settings/account']")
    private WebElement accountSettingsOption;

    public void clickOnProfileAndAccountSettings() throws InterruptedException {
        Thread.sleep(100);
        waitForVisibility(profileIconElement);
        profileIconElement.click();
        waitForVisibility(accountSettingsOption);
        accountSettingsOption.click();
    }

    @FindBy(xpath = "//button[normalize-space()='Delete My Account']")
    private WebElement deleteAccButton;

    @FindBy(xpath = "(//input[@type='checkbox' and contains(@class,'css')])[5]")
    private WebElement confirmCheckbox;

    public void DeleteAccount(){
        waitForVisibility(deleteAccButton);
        deleteAccButton.click();
    }
    private final By checkboxLocator = By.xpath("//input[@name='confirmationCheck']");
    private final By deleteButtonLocator = By.xpath("(//button[normalize-space()='Delete My Account'])[last()]");


public void confirmDeleteAccount() throws InterruptedException {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // 1. Scroll to bottom to make all elements visible
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    Thread.sleep(1000);

    // 2. Locate the checkbox and click it
    WebElement checkbox = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));

    // 3. JS click + dispatch change event (for React)
    js.executeScript("arguments[0].click();", checkbox);
    js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }))", checkbox);
    System.out.println("Checkbox clicked and change event dispatched.");

    // Wait for React to update UI
    Thread.sleep(1000);

    // 4. Locate delete button
    WebElement deleteButton = driver.findElement(deleteButtonLocator);

    // 5. Force enable it if not already
    if (!deleteButton.isEnabled()) {
        System.out.println("Button is not enabled, forcing it...");
        js.executeScript("arguments[0].removeAttribute('disabled');", deleteButton);
    }

    // 6. Scroll to it and click via full JS override
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", deleteButton);
    js.executeScript("arguments[0].focus();", deleteButton);
    js.executeScript("arguments[0].click();", deleteButton);
//    deleteButton.click();

    System.out.println("Delete button clicked via JS.");
}



//    private final By successMessageLocator = By.xpath("//div[@class='css-1htfnu4']//h3");
    By genericSuccessText = By.xpath("//*[contains(text(),'deleted') or contains(text(),'Confirmation')]");

    public boolean isDeleteSuccessMessageDisplayed() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(genericSuccessText));

            // Optional scroll into view
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", message);

            String msg = message.getText();
            System.out.println("Success message text: " + msg);

            return msg.contains("Confirmation") || msg.contains("deleted");
        } catch (TimeoutException e) {
            System.out.println("Success message not found in time.");
            return false;
        }
    }

    public boolean isSuccessMessagePresentInPage() {
        String pageText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Full page text:\n" + pageText);

        return pageText.contains("successfully") || pageText.contains("Confirmation Account Deletion");
    }





}