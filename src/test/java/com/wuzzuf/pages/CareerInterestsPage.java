package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareerInterestsPage extends BasePage {

    public CareerInterestsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//div[@name=\"careerLevel\"]//div)[3]")
    private WebElement careerLevelField;

    @FindBy(xpath = "(//div//button[@name=\"workTypes\"])[1]")
    private WebElement workTypeField;

    @FindBy(xpath = "(//div//button[@name=\"workplaces\"])[3]")
    private WebElement workplaceField;

    @FindBy(xpath = "(//div[@class=\"css-1pwt1s8 ediq4wm0\"]//div)[1]")
    private WebElement interestField;

    @FindBy(xpath = "//input[@name=\"minimumSalary\"]")
    private WebElement minSalaryField;

    @FindBy(xpath = "//button[@class=\"css-pkb4uu ezfki8j0\"]")
    private WebElement getStartedButton;

    @FindBy(xpath = "//button[@class=\"css-qtxznh\"]")
    private WebElement cancelButton;

    public void fillCareerInterests(String salary) {
        waitForVisibility(careerLevelField);
        careerLevelField.click();
        waitForVisibility(workTypeField);
        workTypeField.click();
        waitForVisibility(workplaceField);
        workplaceField.click();
        waitForVisibility(interestField);
        interestField.click();
        waitForVisibility(minSalaryField);
        minSalaryField.sendKeys(salary);

        click(getStartedButton);

        waitForVisibility(cancelButton);
        cancelButton.click();
    }
}
