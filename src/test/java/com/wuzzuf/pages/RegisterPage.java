package com.wuzzuf.pages;

import com.wuzzuf.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class=\"css-1ith43a eswc9qo0\"]")
    private WebElement getStartedButton;

    @FindBy(css = "input[id=\"firstname\"]")
    private WebElement firstNameInput;

    @FindBy(css = "input[id=\"lastname\"]")
    private WebElement lastNameInput;

    @FindBy(css = "input[id=\"email\"]")
    private WebElement emailInput;

    @FindBy(css = "input[name=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css = "input[class=\"css-1itq9hi ek82ord0\"]")
    private WebElement jobTitleInput;

    @FindBy(css = "button[class=\"css-12g3p01 ezfki8j0\"]")
    private WebElement createAccountButton;

    @FindBy(css= "a[href=\"/setup/general-info?ref=signup-successful&sut=reg&login=1\"]")
    private WebElement signupWithoutCvButton;

    @FindBy(css="p[class=\"css-15yw7ce\"]")
    private WebElement nextPageTitle;

    public void goToRegisterForm(){
        getStartedButton.click();
    }


    public void fillRegistrationForm(String firstName, String lastName, String email, String password, String jobTitle) {
        waitForVisibility(firstNameInput);
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        type(passwordInput, password);
        type(jobTitleInput, jobTitle);
        click(createAccountButton);
    }

    public void continueWithoutCV(){
        click(signupWithoutCvButton);
    }

    public String returnNextPageTitle(){
        String titleStr= nextPageTitle.getText();
        return titleStr;
    }
}
