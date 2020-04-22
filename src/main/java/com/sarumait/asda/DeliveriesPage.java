package com.sarumait.asda;

import org.openqa.selenium.WebDriver;

import static com.sarumait.asda.Utils.waitForSpinnerToDisappear;

public class DeliveriesPage {

    private final WebDriver driver;

    private DeliveriesPage(WebDriver driver) {
        this.driver = driver;
    }

    public DeliverySlotsTable getDeliverySlotsTable() {
        return new DeliverySlotsTable(driver);
    }

    public static DeliveriesPage waitForLoad(WebDriver driver) {
        waitForSpinnerToDisappear(driver);
        return new DeliveriesPage(driver);
    }

}
