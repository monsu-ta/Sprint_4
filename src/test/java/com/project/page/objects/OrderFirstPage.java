package com.project.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

    public class OrderFirstPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By nameLocator = By.xpath("//input[@placeholder='* Имя']");
        private final By surnameLocator = By.xpath("//input[@placeholder='* Фамилия']");
        private final By addressLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
        private final By metroLocator = By.xpath("//input[@placeholder='* Станция метро']");
        private final By phoneLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
        private final By nextButtonLocator = By.xpath("//button[text()='Далее']");

        private final String metroStation = "//div[text()='%s']";

        public OrderFirstPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        public void fillName(String name) {
            fillField(nameLocator, name);
        }

        public void fillSurname(String surname) {
            fillField(surnameLocator, surname);
        }

        public void fillAddress(String address) {
            fillField(addressLocator, address);
        }

        public void selectMetroStation(String station) {
            WebElement metroField = wait.until(ExpectedConditions.elementToBeClickable(metroLocator));
            metroField.click();
            metroField.sendKeys(station);
            driver.findElement(By.xpath(String.format(metroStation, station))).click();
        }

        public void fillPhone(String phone) {
            fillField(phoneLocator, phone);
        }

        public void clickNextButton() {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
            nextButton.click();
        }

        private void fillField(By locator, String value) {
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
            field.clear();
            field.sendKeys(value);
        }
    }

