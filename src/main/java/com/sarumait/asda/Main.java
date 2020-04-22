package com.sarumait.asda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

public class Main {

    public static final int MAX_SEARCH_DAYS = 30;

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        String remoteDriverAddress = getMandatorySystemProperty("remoteDriverAddress");
        String username = getMandatorySystemProperty("asdaUsername");
        String password = getMandatorySystemProperty("asdaPassword");

        WebDriver driver = getRemoteWebDriver(remoteDriverAddress);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            System.out.println("Singing in...");
            DeliverySlotsTable deliverySlotsTable = LoginPage.get(driver)
                    .setUsername(username)
                    .setPassword(password)
                    .login()
                    .getDeliverySlotsTable();


            System.out.println("Finding available delivery slots...");
            List<String> availableDeliverySlots = deliverySlotsTable.findAvailableSlots(MAX_SEARCH_DAYS);
            if (!availableDeliverySlots.isEmpty()) {
                System.out.println("SLOTS FOUND!!");
                System.out.println(availableDeliverySlots);
            } else {
                System.out.println("No slots available");
            }


        } finally {
            driver.close();
        }
    }


    private static WebDriver getRemoteWebDriver(String remoteDriverAddress) throws MalformedURLException {
        RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteDriverAddress), DesiredCapabilities.chrome());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    private static String getMandatorySystemProperty(String key) {
        String value = System.getProperty(key);
        requireNonNull(value, key + " must be specified");
        return value;
    }
}
