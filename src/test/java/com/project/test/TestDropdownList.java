package com.project.test;

import com.project.page.objects.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static com.project.data.CardContent.*;

@RunWith(Parameterized.class)
public class TestDropdownList {

        private WebDriver driver;
        private MainPage mainPage;
        private final String browser;
        private final int cardIndex;
        private final String expectedText;

        public TestDropdownList(String browser, int cardIndex, String expectedText) {
            this.browser = browser;
            this.cardIndex = cardIndex;
            this.expectedText = expectedText;
        }

    @Parameterized.Parameters(name = "{index}: Browser={0}, Card={1}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{

                {"chrome", 0, DAILY_RENTAL_PRICE},
                {"chrome", 1, SINGLE_SCOOTER_PER_ORDER},
                {"chrome", 2, RENTAL_START_TIME},
                {"chrome", 3, SAME_DAY_DELIVERY_UNAVAILABLE},
                {"chrome", 4, URGENT_SUPPORT_CONTACT},
                {"chrome", 5, SCOOTER_BATTERY_LIFE},
                {"chrome", 6, CANCELLATION_POLICY},
                {"chrome", 7, MOSCOW_AND_REGION_COVERAGE},


                {"firefox", 0, DAILY_RENTAL_PRICE},
                {"firefox", 1, SINGLE_SCOOTER_PER_ORDER},
                {"firefox", 2, RENTAL_START_TIME},
                {"firefox", 3, SAME_DAY_DELIVERY_UNAVAILABLE},
                {"firefox", 4, URGENT_SUPPORT_CONTACT},
                {"firefox", 5, SCOOTER_BATTERY_LIFE},
                {"firefox", 6, CANCELLATION_POLICY},
                {"firefox", 7, MOSCOW_AND_REGION_COVERAGE}
        });
    }

        @Before
        public void setUp() {
            if ("chrome".equals(browser)) {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }

            mainPage = new MainPage(driver);
            mainPage.open();
            mainPage.closeCookieBanner();
        }

        @Test
        public void testCardContent() {

            mainPage.clickCardButton(cardIndex);
            String actualText = mainPage.getCardText(cardIndex);
            Assert.assertEquals("Текст в карточке не соответствует требованиям",
                    expectedText, actualText);
        }

        @After
        public void tearDown() {
                driver.quit();
            }
        }


