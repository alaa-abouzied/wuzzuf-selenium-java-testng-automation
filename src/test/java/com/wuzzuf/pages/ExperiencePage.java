package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExperiencePage extends BasePage {

    public ExperiencePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class=\"css-olenq2 eequ2uf0\"])[1]")
    private WebElement experienceTypeField;


//    WebElement workThereField = driver.findElements(By.xpath("//input[@type=\"checkbox\"]")).get(0);
//    @FindBy(xpath = "//input[@type=\"checkbox\"]")
//    private WebElement workThereField;

    @FindBy(xpath = "//div //button[@name=\"Save_Work_Experience\"]")
    private WebElement saveBtn;

    @FindBy(xpath = "(//div[@class=\"css-x1i4hg\"]//button)[2]")
    private WebElement continueButton;


    public void fillExperienceForm(String experienceYears, String jobTitle, String company, String jobCategory, String startMonth, String startYear)throws InterruptedException {


        selectReactInputFieldByIndex(0, experienceYears);

        selectReactInputFieldByIndex1(0,jobTitle);
        selectReactInputFieldByIndex1(1,company);

        selectReactInputFieldByIndex(1, jobCategory);


        experienceTypeField.click();

        selectReactInputFieldByIndex(2, startMonth);

        selectReactInputFieldByIndex(3, startYear);

        WebElement workThereCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']")).get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workThereCheckbox);
        Thread.sleep(500); // tiny wait
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", workThereCheckbox);

        click(saveBtn);
        waitForVisibility(continueButton);
        Thread.sleep(500);
        continueButton.click();
        continueButton.sendKeys(Keys.ENTER);
    }

    public String returnExpertisePageTitle(){
        WebElement expertiseTitle=driver.findElement(By.xpath("//p[@class=\"css-15yw7ce\"]"));
        String expertiseTitleStr= expertiseTitle.getText();
        return expertiseTitleStr;
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
    public void selectReactInputFieldByIndex1(int index1, String value1) throws InterruptedException {
        // Wait for React input to appear
        Thread.sleep(1000);

        // Select the Nth input with aria-autocomplete="list"
        WebElement input2 = driver.findElements(By.xpath("//input[@class=\"css-1n6f2sr e1n2h7jb1\"]")).get(index1);

        input2.click();
        input2.sendKeys(value1);
        Thread.sleep(1000); // Wait for dropdown to appear
        input2.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        input2.sendKeys(Keys.ENTER);
    }

}
