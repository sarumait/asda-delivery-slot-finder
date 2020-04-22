package com.sarumait.asda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
    private static final String LOGIN_URL = "https://www.asda.com/login?redirect_uri=https://groceries.asda.com/checkout/book-slot?tab=deliver&origin=/";
    private final WebDriver driver;

    private LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage setUsername(String username) {
        sendKeys(By.className("email-phone-input"), username);
        return this;
    }

    public LoginPage setPassword(String password) {
        sendKeys(By.id("password"), password);
        return this;
    }

    public DeliveriesPage login() {
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
        submitButton.click();
        return DeliveriesPage.waitForLoad(driver);
    }

    private void sendKeys(By locator, String keys) {
        WebElement usernameElement = driver.findElement(locator);
        new Actions(driver).moveToElement(usernameElement).click().sendKeys(keys).perform();
    }

    public static LoginPage get(WebDriver driver) {
        driver.get(LOGIN_URL);
        return new LoginPage(driver);
    }

}
