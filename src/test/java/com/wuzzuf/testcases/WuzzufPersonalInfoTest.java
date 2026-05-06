package com.wuzzuf.testcases;

import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.PersonalInfoPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WuzzufPersonalInfoTest extends BaseTest {

    @Test
    public void testUpdatePersonalInfo() {
//        driver.get("https://wuzzuf.net/settings/personal-info");

        PersonalInfoPage infoPage = new PersonalInfoPage(driver);

        infoPage.updatePersonalInfo(
                "10","January","1997","Cairo","Sahel","01556644778","Egypt");

        String actualEducationTitle = infoPage.returnEducationPageTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                actualEducationTitle.matches("Tell Us About Your Education"),
                "Expected page title to contain 'Tell Us About Your Education' but got: " + actualEducationTitle
        );
    }


}
