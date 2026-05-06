package com.wuzzuf.testcases;

import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.EducationPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WuzzufEducationTest extends BaseTest {

    @Test
    public void testFillEducationForm() throws InterruptedException {
        // You must already be logged in and redirected to the education step
//        driver.get("https://wuzzuf.net/setup/education?sut=reg&login=1");

        EducationPage educationPage = new EducationPage(driver);
//        educationPage.fillEmailAndPass("alaa@testalaa.com","test1234");
        educationPage.fillEducationForm(
                "Computer Science","Cairo University (CU)","2023","A / Excellent / 85 -100%");

        String actualExperienceTitle = educationPage.returnExperiencePageTitle();

        //System.out.println("Experience title: " + actualExperienceTitle);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                actualExperienceTitle.matches("Tell Us About Your Experience"),
                "Expected page title to contain 'Tell Us About Your Experience' but got: " + actualExperienceTitle
        );
    }
}
