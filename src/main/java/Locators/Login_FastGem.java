package Locators;

import org.openqa.selenium.By;

public interface Login_FastGem {

    By fastgemUsername = By.id("modlgn-username");
    By fastgemPassword = By.id("modlgn-passwd");
    By loginButton = By.cssSelector("button.login-button");
}
