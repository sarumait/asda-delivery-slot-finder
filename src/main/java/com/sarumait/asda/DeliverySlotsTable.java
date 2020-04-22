package com.sarumait.asda;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static com.sarumait.asda.Utils.waitForSpinnerToDisappear;
import static java.util.stream.Collectors.toList;

public class DeliverySlotsTable {

    private final WebDriver driver;
    private final WebElement tableBody;
    private final WebElement loadLaterSlotsButton;

    public DeliverySlotsTable(WebDriver driver) {
        this.driver = driver;
        this.tableBody = driver.findElement(By.className("co-slots__slots-container"));
        loadLaterSlotsButton = driver.findElement(By.className("slot-table-header__arrow--later"));
    }

    public List<String> findAvailableSlots(int maxDays) {
        int loadedDays = driver.findElements(By.className("co-slots__day")).size();
        while (loadedDays < maxDays && loadLaterSlotsButton.isEnabled()) {
            try {
                loadLaterSlotsButton.click();
            } catch (ElementClickInterceptedException e) {
                waitForSpinnerToDisappear(driver);
            }
        }

        return findAvailableSlots();
    }

    private List<String> findAvailableSlots() {
        List<List<WebElement>> tableRows = tableBody.findElements(By.className("co-slots__price-row")).stream()
                .map(row -> row.findElements(By.className("co-slots__book-button")))
                .collect(toList());
        int noOfColumnsInEachRow = tableRows.get(0).size();

        //Iterate by column
        return IntStream.range(0, noOfColumnsInEachRow)
                .mapToObj(i -> tableRows.stream().map(row -> row.get(i)))
                .flatMap(Function.identity())
                .map(btn -> btn.getAttribute("aria-label"))
                .filter(label -> label != null && !label.isEmpty())
                .collect(toList());
    }

}
