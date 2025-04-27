package Project.DropdownList;

import Project.PageObject.LocatorsMainPage;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static Project.DropdownList.CardText.*;

@RunWith(Parameterized.class)
public class TestDropdownListChrome {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By cardLocator;
    private final By panelLocator;
    private final String expectedText;

    public TestDropdownListChrome(int cardIndex, String expectedText) {
        this.cardLocator = LocatorsMainPage.cardButton(cardIndex);
        this.panelLocator = LocatorsMainPage.cardContent(cardIndex);
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getCardData() {
        return Arrays.asList(new Object[][]{
                {0, firstCard},
                {1, secondCard},
                {2, thirdCard},
                {3, fourthCard},
                {4, fifthCard},
                {5, sixthCard},
                {6, seventhCard},
                {7, eighthCard}
        });
    }

    @Before
    public void openPage() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testCardInteraction() {
        clickCardButton();
        String actualText = getCardText();
        assertCardText(expectedText, actualText);
    }

    private void clickCardButton() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(cardLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    private String getCardText() {
        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(panelLocator));
        return content.getText();
    }

    private void assertCardText(String expected, String actual) {
        Assert.assertEquals("Текст в карточке не соответствует требованиям", expected, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
