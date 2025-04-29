package com.project.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderSecondPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dateFieldLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By periodDropdownLocator = By.className("Dropdown-placeholder");
    private final By blackColorCheckboxLocator = By.id("black");
    private final By greyColorCheckboxLocator = By.id("grey");
    private final By orderButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private final By yesButtonLocator = By.xpath("//button[text()='Да']");
    private final By statusButtonLocator = By.xpath("//button[text()='Посмотреть статус']");

    public OrderSecondPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillDate(String date) {
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(dateFieldLocator));
        dateField.sendKeys(date);
        driver.findElement(By.tagName("body")).click();
    }

    public void selectRentalPeriod(String period) {
        WebElement listPeriod = wait.until(ExpectedConditions.elementToBeClickable(periodDropdownLocator));
        listPeriod.click();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", period))).click();
    }

    public void selectColor(String color) {
        By colorLocator = color.equals("black") ? blackColorCheckboxLocator : greyColorCheckboxLocator;
        driver.findElement(colorLocator).click();
    }

    public void clickOrderButton() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(orderButtonLocator));
        orderButton.click();
    }

    public void clickYesButton() {
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(yesButtonLocator));
        yesButton.click();
    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.elementToBeClickable(statusButtonLocator)).isDisplayed();
    }
}
