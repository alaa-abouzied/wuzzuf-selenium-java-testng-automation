package com.wuzzuf.testcases;

import com.wuzzuf.base.BaseTest;
import com.wuzzuf.pages.JobSearchPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WuzzufJobSearchTest extends BaseTest {

    @Test(priority = 1)
    public void testSearchAndOpenJob() {
        JobSearchPage searchPage = new JobSearchPage(driver);
        searchPage.searchJobs("Software Engineer");
//        searchPage.openFirstJobResult();
    }
    @Test(priority = 2)
    public void testSearchResultsContainSoftwareEngineer() throws InterruptedException {

        JobSearchPage resultsPage = new JobSearchPage(driver);
        List<String> jobTitles = resultsPage.getAllJobTitles();

        SoftAssert softAssert = new SoftAssert();

        for (String title : jobTitles) {
            System.out.println("Job Found: " + title);
            String lowerTitle = title.toLowerCase();

            boolean isRelevant = lowerTitle.contains("software") && (
                    lowerTitle.contains("engineer") ||
                            lowerTitle.contains("testing") ||
                            lowerTitle.contains("qc") ||
                            lowerTitle.contains("quality control")
            );

            softAssert.assertTrue(isRelevant, "Unexpected Job Title: " + title);
        }

        int count1 = resultsPage.getSearchResultsCount();
        System.out.println("Search Results Count: " + count1);

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(count1 > 0, "Expected search results count > 0 but got: " + count1);

        resultsPage.filterByPastWeek();

       int count2 = resultsPage.getSearchResultsCount();
        System.out.println("Search Results Count After Filtering: " + count2);

        soft.assertTrue(count2 > 0, "Expected search results count > 0 but got: " + count2);

        softAssert.assertAll();
        resultsPage.openFirstJob();
        resultsPage.switchToNewTab();
        resultsPage.applyForJobAndSave();
        resultsPage.clickSaveAndApplyLaterBtn();
        resultsPage.clickOnSaved();

    }
    @Test(priority = 3)
    public void testJobIsSaved() {
        JobSearchPage jobSearchPage = new JobSearchPage(driver);
        int savedCount = jobSearchPage.getSavedJobsCount();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(savedCount > 0, "Expected at least one saved job, but got: " + savedCount);
        System.out.println("Saved Count Jobs: "+savedCount);
        softAssert.assertAll();
    }


}
