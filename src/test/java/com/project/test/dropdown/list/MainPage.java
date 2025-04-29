package com.project.test.dropdown.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By cookieLocator = By.id("rcc-confirm-button");
    private final By cardButtonLocator = By.xpath("//div[@id='accordion__heading-%d']");
    private final By cardContentLocator = By.xpath("//div[@id='accordion__panel-%d']/p");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void closeCookieBanner() {
        WebElement cookieButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(cookieLocator));
        cookieButton.click();
    }

    public void clickCardButton(int index) {
        By locator = By.xpath(String.format("//div[@id='accordion__heading-%d']", index));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public String getCardText(int index) {
        By locator = By.xpath(String.format("//div[@id='accordion__panel-%d']/p", index));
        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return content.getText();
    }

}
