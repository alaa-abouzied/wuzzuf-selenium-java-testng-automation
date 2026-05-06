package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExpertisePage extends BasePage {
    public ExpertisePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueButton;

    //    WebElement addButton = driver.findElement(By.xpath("//button[1]"));
//    WebElement continueButton=driver.findElement(By.xpath("//div[@class=\"css-x1i4hg\"]//button[@class=\"css-2dg8nw ezfki8j0\"]"));
    public void fillExpertiseForm(String language,String proficiency,String skill1, String skill2)throws InterruptedException {
        Thread.sleep(100);
        selectReactInputFieldByIndex(0,language);
        selectReactInputFieldByIndex(1,proficiency);
        waitForVisibility(addButton);
        addButton.click();
        selectReactInputFieldByIndex(2,skill1);
        selectReactInputFieldByIndex(2,skill2);

        waitForVisibility(continueButton);
        continueButton.click();
//        continueButton.sendKeys(Keys.ENTER);
    }

    public void selectReactInputFieldByIndex(int index, String value) throws InterruptedException {
        // Wait for React input to appear
        Thread.sleep(1000);

        // Select the Nth input with aria-autocomplete="list"
        WebElement input = driver.findElements(By.xpath("//input[@aria-autocomplete='list']")).get(index);

        input.click();
        input.sendKeys(value);
        Thread.sleep(1000); // Wait for dropdown to appear
        input.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        input.sendKeys(Keys.ENTER);
    }

    public String returnCareerPageTitle() throws InterruptedException {
        Thread.sleep(500);
        WebElement carrerTitle=driver.findElement(By.xpath("//p[@class=\"css-15yw7ce\"]"));
        String careerTitleStr= carrerTitle.getText();
        return careerTitleStr;
    }



}
