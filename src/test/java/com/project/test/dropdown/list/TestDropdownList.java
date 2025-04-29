package com.project.test.dropdown.list;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static com.project.test.dropdown.list.CardText.*;

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

                {"chrome", 0, dailyRentalPrice},
                {"chrome", 1, singleScooterPerOrder},
                {"chrome", 2, rentalStartTime},
                {"chrome", 3, sameDayDeliveryUnavailable},
                {"chrome", 4, urgentSupportContact},
                {"chrome", 5, scooterBatteryLife},
                {"chrome", 6, cancellationPolicy},
                {"chrome", 7, moscowAndRegionCoverage},


                {"firefox", 0, dailyRentalPrice},
                {"firefox", 1, singleScooterPerOrder},
                {"firefox", 2, rentalStartTime},
                {"firefox", 3, sameDayDeliveryUnavailable},
                {"firefox", 4, urgentSupportContact},
                {"firefox", 5, scooterBatteryLife},
                {"firefox", 6, cancellationPolicy},
                {"firefox", 7, moscowAndRegionCoverage}
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


