package com.sarumait.asda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class Utils {

    public static void waitForSpinnerToDisappear(WebDriver driver) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until(d -> not(invisibilityOfElementLocated(By.className("asda-spinner__overlay"))));
    }
}
