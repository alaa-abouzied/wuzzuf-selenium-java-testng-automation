package com.wuzzuf.testcases;
import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.ExperiencePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WuzzufExperienceTest extends BaseTest {

    @Test
    public void testAddExperience()  throws InterruptedException {
//        driver.get("https://wuzzuf.net/setup/experience?sut=reg&login=1");
        ExperiencePage experiencePage = new ExperiencePage(driver);
//        experiencePage.fillEmailAndPass("alaa@testalaa.com","test1234");
        experiencePage.fillExperienceForm (
                "3 years","Software Tester","Valeo","IT/Software Development","March","2023"
        );

        String actualExpertiseTitle = experiencePage.returnExpertisePageTitle();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                actualExpertiseTitle.matches("Tell Us About Your Expertise"),
                "Expected page title to contain 'Tell Us About Your Expertise' but got: " + actualExpertiseTitle
        );
    }
}
