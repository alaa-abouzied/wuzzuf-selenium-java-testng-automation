package com.wuzzuf.testcases;

import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.ExperiencePage;
import com.wuzzuf.pages.ExpertisePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WuzzufExpertiseTest extends BaseTest {
    @Test
    public void testAddExpertise() throws InterruptedException {
        ExpertisePage expertisePage = new ExpertisePage(driver);
        expertisePage.fillExpertiseForm("Arabic","Fluent","Information Technology (IT)","Software Testing");
        String actualCareerTitle = expertisePage.returnCareerPageTitle();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                actualCareerTitle.matches("Tell Us About Your Career Interests"),
                "Expected page title to contain 'Tell Us About Your Career Interests' but got: " + actualCareerTitle
        );
    }
}
