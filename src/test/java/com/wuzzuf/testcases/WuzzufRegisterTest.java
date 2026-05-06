package com.wuzzuf.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.RegisterPage;

public class WuzzufRegisterTest extends BaseTest {

    @Test
    public void testUserCanRegister() {
        driver.get("https://wuzzuf.net/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillRegistrationForm(
                "Alaa",
                "Abouzied",
                "alaa" + System.currentTimeMillis() + "@test.com",
                "Test1234!",
                "QA Engineer"
        );

        registerPage.continueWithoutCV();
        // Soft assertion for title
        String actualTitle = registerPage.returnNextPageTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                actualTitle.matches("Tell Us About Yourself"),
                "Expected page title to contain 'Tell Us About Yourself' but got: " + actualTitle
        );

        softAssert.assertAll();
    }
}
