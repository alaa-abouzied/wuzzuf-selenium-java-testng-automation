package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EducationPage extends BasePage {

    public EducationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='css-1dy69p2 eequ2uf0'])[1]")
    private WebElement educationalLevelField;


    @FindBy(css = "input[class=\"css-1a96k50 ek82ord0\"]")
    private WebElement fieldOfStudyField;

    @FindBy(css = "button[class=\"css-2dg8nw ezfki8j0\"]")
    private WebElement continueButton;

    @FindBy(css = "p[class=\"css-15yw7ce\"]")
    private WebElement experienceTitle;

    public void fillEducationForm(String fieldOfStudy, String university, String degreeDate, String grade) throws InterruptedException {
            waitForVisibility(educationalLevelField);
            educationalLevelField.click();


            fieldOfStudyField.click();
            fieldOfStudyField.sendKeys(fieldOfStudy);
            fieldOfStudyField.sendKeys(Keys.ENTER);

        selectReactInputFieldByIndex(0, university);
        selectReactInputFieldByIndex(1, degreeDate);   // Degree Date
        selectReactInputFieldByIndex(2, grade);

            click(continueButton);
        }
        public String returnExperiencePageTitle(){
            String experienceTitleStr= experienceTitle.getText();
            return experienceTitleStr;
        }

    private void selectReactInputFieldByIndex(int index, String value) throws InterruptedException {
        // Wait for React input to appear
        Thread.sleep(1000);

        // Select the Nth input with aria-autocomplete="list"
        WebElement input = driver.findElements(By.xpath("//input[@aria-autocomplete='list']")).get(index);
        input.click();
        input.sendKeys(value);
        Thread.sleep(500); // Wait for dropdown to appear
        input.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        input.sendKeys(Keys.ENTER);
    }
    }

