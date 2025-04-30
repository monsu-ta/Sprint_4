package com.project.test;

import com.project.page.objects.MainPage;
import com.project.data.OrderDataTest;
import com.project.page.objects.OrderFirstPage;
import com.project.page.objects.OrderSecondPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestPlaceOfOrder {
    private WebDriver driver;
    private final OrderDataTest testData;

    @Parameterized.Parameters(name = "{index}: Browser={0}, UseHeader={1}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"firefox", true, "Иван", "Иванов", "ул. Мира, 1", "Проспект Мира", "89991112233",
                        "03.04.2025", "сутки", "black"},

                {"chrome", false, "Василий", "Васильев", "проспект Ленина, 5", "Курская", "+79998887766",
                        "01.05.2025", "двое суток", "grey"},

                {"chrome", true, "Петр", "Петров", "ул. Пушкина, 10", "Спартак", "87776665544",
                        "15.06.2025", "семеро суток", "black"},

                {"firefox", false, "Сергей", "Сергеев", "ул. Гагарина, 42", "Черкизовская", "+78887776655",
                        "20.07.2025", "трое суток", "grey"}
        });
    }

    public TestPlaceOfOrder(String browser, boolean useHeaderButton, String name, String surname,
                                        String address, String metroStation, String phone,
                                        String deliveryDate, String rentalPeriod, String color) {
        this.testData = new OrderDataTest(browser, useHeaderButton, name, surname, address,
                metroStation, phone, deliveryDate, rentalPeriod, color);
    }

    @Before
    public void setUp() {
        String browser = testData.getBrowser().toLowerCase();

        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + testData.getBrowser());
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookieBanner();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void TestOrderForm() {
        MainPage mainPage = new MainPage(driver);
        OrderFirstPage firstPage;
        OrderSecondPage secondPage;

        if (testData.isUseHeaderButton()) {
            mainPage.clickOrderHeaderButton();
        } else {
            mainPage.clickOrderBigButton();
        }

        firstPage = new OrderFirstPage(driver);
        firstPage.fillName(testData.getName());
        firstPage.fillSurname(testData.getSurname());
        firstPage.fillAddress(testData.getAddress());
        firstPage.selectMetroStation(testData.getMetroStation());
        firstPage.fillPhone(testData.getPhone());
        firstPage.clickNextButton();

        secondPage = new OrderSecondPage(driver);
        secondPage.fillDate(testData.getDeliveryDate());
        secondPage.selectRentalPeriod(testData.getRentalPeriod());
        secondPage.selectColor(testData.getColor());
        secondPage.clickOrderButton();
        secondPage.clickYesButton();

        assertTrue("Должно появиться подтверждение заказа", secondPage.isOrderConfirmed());
    }
}