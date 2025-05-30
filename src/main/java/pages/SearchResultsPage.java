package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private By searchResults = By.cssSelector("div.search-results .slds-col");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getResultCount() {
        List<WebElement> results = driver.findElements(searchResults);
        return results.size();
    }
}