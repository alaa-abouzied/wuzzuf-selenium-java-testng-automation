package com.wuzzuf.testcases;

import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.AccountSettingsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WuzzufAccountSettingsTest extends BaseTest {

    @Test(priority = 1)
    public void clickOnDeleteAccountSuccess() throws InterruptedException {
        AccountSettingsPage settingsPage = new AccountSettingsPage(driver);

        settingsPage.clickOnProfileAndAccountSettings();

        settingsPage.DeleteAccount();
    }
    @Test(priority = 2)
    public void testUpdateAccountSettings() throws InterruptedException {
AccountSettingsPage settingsPage = new AccountSettingsPage(driver);

        settingsPage.confirmDeleteAccount();
    }
    @Test(priority = 3)
    public void verifyDelete() throws InterruptedException {
        AccountSettingsPage settingsPage = new AccountSettingsPage(driver);
        Assert.assertTrue(settingsPage.isDeleteSuccessMessageDisplayed(), "Account deletion success message not shown!");
        //        SoftAssert soft= new SoftAssert();
    }

    @Test(priority = 4)
    public void testDeleteAccount() throws InterruptedException {
        AccountSettingsPage settingsPage = new AccountSettingsPage(driver);
        Assert.assertTrue(settingsPage.isSuccessMessagePresentInPage(),
                "Success message not found in full page text.");
    }

}
