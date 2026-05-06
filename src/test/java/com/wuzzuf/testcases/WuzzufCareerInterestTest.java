package com.wuzzuf.testcases;

import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.CareerInterestsPage;
import org.testng.annotations.Test;

public class WuzzufCareerInterestTest extends BaseTest {

    @Test
    public void testUpdateCareerInterests() {

        CareerInterestsPage interestsPage = new CareerInterestsPage(driver);
        interestsPage.fillCareerInterests("100000");
    }
}
