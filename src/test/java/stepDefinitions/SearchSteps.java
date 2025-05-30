package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.DriverFactory;
import utils.ExtentReportManager;

public class SearchSteps {

    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @Before
    public void setUp(io.cucumber.java.Scenario scenario) {
        driver = DriverFactory.initDriver();
        ExtentReportManager.initReport();
        ExtentReportManager.createTest(scenario.getName());
    }

    @Given("I launch the Salesforce blog page")
    public void i_launch_the_salesforce_blog_page() {
        driver.get("https://www.salesforce.com/in/blog/website-development/");
        homePage = new HomePage(driver);
        ExtentReportManager.logPass("Launched Salesforce blog page");
    }

    @When("I search for the keyword {string}")
    public void i_search_for_the_keyword(String keyword) {
        searchResultsPage = homePage.search(keyword);
    }

    @Then("I should see search results displayed")
    public void i_should_see_search_results_displayed() {
        Assert.assertTrue("Search results are not displayed", searchResultsPage.getResultCount() > 0);
    }

    @Then("I should see Share Article Section")
    public void i_should_see_share_article_section() {
        if(homePage.shareArticles().isEmpty()){
            ExtentReportManager.logFail("No Share Article Header found");
        }else{
            ExtentReportManager.logPass("Share Article Header: " + homePage.shareArticles());
        }
        Assert.assertTrue("Share Article Header", homePage.shareArticles().contains("Share article"));

    }

    @Then("I verify the social media links available")
    public void i_should_see_social_media_icons_and_links() {
        if (homePage.verifySocialMediaIconsAndLinks()){
            ExtentReportManager.logPass("Linked Label: " + homePage.fetchSocialMediaLabel());
        }else{
            ExtentReportManager.logFail("Linked Social Media Link not found");
        }
        Assert.assertTrue("Verify Linked In Link", homePage.verifySocialMediaIconsAndLinks());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        ExtentReportManager.flushReport();
    }
}