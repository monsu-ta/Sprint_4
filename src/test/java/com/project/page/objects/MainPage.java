package com.project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieLocator = By.id("rcc-confirm-button");
    private final By orderHeaderButtonLocator = By.className("Header_Link__1TAG7");
    private final By orderBigButtonLocator = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Заказать')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void closeCookieBanner() {
        WebElement cookieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieLocator));
        cookieButton.click();
    }

    public void clickOrderHeaderButton() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(orderHeaderButtonLocator));
        scrollAndClick(orderButton);
    }

    public void clickOrderBigButton() {
        WebElement orderBigButton = wait.until(ExpectedConditions.elementToBeClickable(orderBigButtonLocator));
        scrollAndClick(orderBigButton);
    }

    private void scrollAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}

