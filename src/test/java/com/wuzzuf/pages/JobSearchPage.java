package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class JobSearchPage extends BasePage {
    private final By jobTitlesLocator = By.xpath("//h2//a");
    public JobSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Search by Job Title, Keywords or Location (e.g. Sales in Cairo)\"]")
    private WebElement searchBoxField;

    @FindBy(xpath = "//div[@class=\"css-150a24d\"]//button")
    private WebElement searchButton;

    @FindBy(xpath = "//strong[normalize-space(following-sibling::text()[1])='Jobs found']\n")
    public WebElement resultCountElement1;

    @FindBy(xpath = "(//aside//div)[17]")
    private WebElement datePostedElement;
    @FindBy(xpath = "//span[normalize-space(text())='Past week']/ancestor::label/input[@type='radio']")
    private WebElement pastWeekRadio;

    @FindBy(xpath = "(//div[@class=\"css-pkv5jc\"]//h2//a)[1]")
    private WebElement firstJob;




    public void searchJobs(String keyword) {
        waitForVisibility(searchBoxField);
        type(searchBoxField, keyword);
        click(searchButton);
    }

    public List<String> getAllJobTitles() {
        waitForVisibility(driver.findElement(jobTitlesLocator));
        return driver.findElements(jobTitlesLocator)
                .stream()
                .map(WebElement::getText)
                .toList();
    }


    public int getSearchResultsCount() {
        waitForVisibility(resultCountElement1);
        String countText = resultCountElement1.getText().trim();  // should be "1419"
        System.out.println("Raw Count Text: " + countText);
        try {
            return Integer.parseInt(countText.replaceAll(",", "")); // removes comma if present (e.g., 1,419)
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse search results count from text: " + countText);
        }
    }

    public void filterByPastWeek() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForVisibility(datePostedElement);
        datePostedElement.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[normalize-space(text())='Past week']/ancestor::label/input[@type='radio']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pastWeekRadio);

        try {
            Thread.sleep(3000); // Optional small wait for visual confirmation/results to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openFirstJob(){
        waitForVisibility(firstJob);
        firstJob.click();
    }

    public void applyForJobAndSave() throws InterruptedException {
        System.out.println("Title of new tab: " + driver.getCurrentUrl());
        Thread.sleep(1000);
        WebElement closeBtn=driver.findElement(By.xpath("//div[@class=\"css-tjo4qw\"]//button"));
        closeBtn.click();

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (WebElement btn : buttons) {
            try {
                if (btn.getText().trim().equalsIgnoreCase("Apply For Job")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Apply button not found or not clickable");
    }

    public void clickSaveAndApplyLaterBtn() throws InterruptedException {
        Thread.sleep(100);
        WebElement saveAndApplyBtn= driver.findElement(By.xpath("//button[normalize-space(text())='Save and Apply later']\n"));
        saveAndApplyBtn.click();
    }

    public void clickOnSaved(){

        WebElement savedBtn=driver.findElement(By.xpath("//a[@aria-current=\"page\"]"));
        waitForVisibility(savedBtn);
        savedBtn.click();
    }
    @FindBy(xpath = "//a[@href='/saved']//span[@class='css-1x0wra0 eoyjyou0']")
    private WebElement savedCountElement;

    public int getSavedJobsCount() {
        waitForVisibility(savedCountElement);
        String countText = savedCountElement.getText().trim(); // e.g., "1"
        try {
            return Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse saved jobs count: " + countText);
        }
    }

}