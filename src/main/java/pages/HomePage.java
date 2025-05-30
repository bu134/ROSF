package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By searchIcon = By.cssSelector("button[data-target='#search-box']");
    private By searchInput = By.id("head-search-box");
    private By shareArticleTitle = By.cssSelector("#main-content .post__social--top.post__social--has-title > h2");
    private By linkedLink = By.xpath("//div[@class='post__social post__social--v2 post__social--top post__social--has-title']//a[contains(@href,'linkedin')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchResultsPage search(String keyword) {
        driver.findElement(searchIcon).click();
        driver.findElement(searchInput).sendKeys(keyword, Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public String shareArticles(){
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println("Exception while pausing : " + e.getMessage());
        }
        return driver.findElement(shareArticleTitle).getText();
    }

    public Boolean verifySocialMediaIconsAndLinks(){
        return driver.findElement(linkedLink).isEnabled();
    }

    public String fetchSocialMediaLabel(){
        return driver.findElement(linkedLink).getAttribute("aria-label");
    }
}