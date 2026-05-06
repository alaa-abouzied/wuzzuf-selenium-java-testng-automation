package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.*;

public class PersonalInfoPage extends BasePage {

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id=\"react-select-2-input\"]")
    private WebElement birthdateDayField;

    @FindBy(xpath ="//*[@id=\"react-select-3-input\"]")
    private WebElement birthdateMonthField;

    @FindBy(xpath = "//*[@id=\"react-select-4-input\"]")
    private WebElement birthdateYearField;

    @FindBy(xpath = "//div[@class='css-bhwo3q e1kea1u61' and text()='Female']")
    private WebElement femaleOption;

    @FindBy(xpath = "(//div[@class='css-rpi6b5 e1g066lk4']//div[contains(@class,'css-1dgicot-container')])[1]//input[@aria-autocomplete='list']")
    private WebElement nationalityField;

    @FindBy(xpath = "//*[@id=\"react-select-7-input\"]")
    private WebElement cityField;

    @FindBy(xpath = "//label[contains(text(),'Area')]/following::input[contains(@id,'react-select') and @type='text'][1]")
    private WebElement areaField;


    @FindBy(css = "input[type=\"tel\"]")
    private WebElement phoneNumberField;

    @FindBy(css = "div[class=\"css-x1i4hg\"] button[type=\"button\"]")
    private WebElement saveButton;

    @FindBy(css = "p[class=\"css-15yw7ce\"]")
    private WebElement educationPageTitle;

    public void updatePersonalInfo(
            String birthDay, String birthMonth, String birthYear,
            String city, String area, String phone, String nationality
    ) {
        waitForVisibility(birthdateDayField);
        birthdateDayField.click();
        birthdateDayField.sendKeys(birthDay);
        birthdateDayField.sendKeys(Keys.ENTER);

        birthdateMonthField.click();
        birthdateMonthField.sendKeys(birthMonth);
        birthdateMonthField.sendKeys(Keys.ENTER);

        birthdateYearField.click();
        birthdateYearField.sendKeys(birthYear);
        birthdateYearField.sendKeys(Keys.ENTER);

        femaleOption.click();
        nationalityField.click();
        nationalityField.sendKeys(nationality);
        nationalityField.sendKeys(Keys.ENTER);

        cityField.clear();
        cityField.sendKeys(city);
        cityField.sendKeys(Keys.ENTER);

        waitForVisibility(areaField);
        selectReactOptionWithDelay(areaField, area);

        phoneNumberField.clear();
        phoneNumberField.sendKeys(phone);

       saveButton.click();
    }

    public String returnEducationPageTitle(){
        String educationTitleStr= educationPageTitle.getText();
        return educationTitleStr;
    }


}
