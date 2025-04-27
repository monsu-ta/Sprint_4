package Project.PlaceOfOrder;

import Project.PageObject.LocatorsFirstPageOrder;
import Project.PageObject.LocatorsMainPage;
import Project.PageObject.LocatorsOrderConfirmationPage;
import Project.PageObject.LocatorsSecondPageOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static Project.PageObject.LocatorsFirstPageOrder.nextButtonLocator;
import static Project.PageObject.LocatorsMainPage.orderBigButtonLocator;
import static Project.PageObject.LocatorsMainPage.orderHeaderButtonLocator;

@RunWith(Parameterized.class)
public class TestPlaceOfOrderChrome {

    private WebDriver driver;
    private WebDriverWait wait;
    private final OrderTestData testData;
    private final boolean useHeaderButton;

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {true, "Иван", "Иванов", "ул. Мира, 1", "Проспект Мира", "89991112233",
                        "03.04.2025", "сутки", "black"},

                {false, "Василий", "Васильев", "проспект Ленина, 5", "Курская", "+79998887766",
                        "01.05.2025", "двое суток", "grey"}
        });
    }

    public TestPlaceOfOrderChrome(boolean useHeaderButton, String name, String surname, String address,
                                  String metroStation, String phone,
                                  String deliveryDate, String rentalPeriod, String color) {
        this.useHeaderButton = useHeaderButton;
        this.testData = new OrderTestData(name, surname, address, metroStation, phone,
                deliveryDate, rentalPeriod, color);
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        closeCookieBanner();
    }

    private void closeCookieBanner() {
        WebElement cookieButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(LocatorsMainPage.cookieLocator));
        cookieButton.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testOrderForm() {
        // Выбор кнопки
        if (useHeaderButton) {
            clickOrderButtonHeader();
        } else {
            clickOrderBigButton();
        }

        fillFirstFormFields();
        clickNextButton();
        fillSecondFormFields();
    }

    // Методы первой страницы

    private void clickOrderButtonHeader() {
        WebElement orderButton = wait.until(ExpectedConditions
                .elementToBeClickable(orderHeaderButtonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderButton);
        orderButton.click();

    }

    private void clickOrderBigButton() {
        WebElement orderBigButton = wait.until(ExpectedConditions
                .elementToBeClickable(orderBigButtonLocator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                orderBigButton);
        wait.until(ExpectedConditions.elementToBeClickable(orderBigButton));
        orderBigButton.click();
    }

    private void fillFirstFormFields() {

        fillField(LocatorsFirstPageOrder.nameLocator(), testData.getName());
        fillField(LocatorsFirstPageOrder.surnameLocator(), testData.getSurname());
        fillField(LocatorsFirstPageOrder.addressLocator(), testData.getAddress());
        selectMetroStation(testData.getMetroStation());
        fillField(LocatorsFirstPageOrder.phoneLocator(), testData.getPhone());
    }

    private void fillField(By input, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(input));
        field.clear();
        field.sendKeys(value);
    }

    private void selectMetroStation(String station) {
        WebElement metroField = wait.until(ExpectedConditions
                .elementToBeClickable(LocatorsFirstPageOrder.metroLocator()));
        metroField.click();
        metroField.sendKeys(station);
        driver.findElement(By.xpath(String.format("//div[text()='%s']", station))).click();
    }

    private void clickNextButton() {
        WebElement nextButton = wait.until(ExpectedConditions
                .elementToBeClickable(nextButtonLocator()));
        nextButton.click();
    }

    // Методы второй страницы

    private void fillSecondFormFields() {
        fillDate(testData.getDeliveryDate());
        fillPeriod(testData.getRentalPeriod());
        selectColor(testData.getColor());
        clickToOrderButtonOrderPage();
        clickToYesButton();
    }

    private void fillDate(String date) {
        WebElement dateField = wait.until(ExpectedConditions
                .elementToBeClickable(LocatorsSecondPageOrder.dateFieldLocator()));
        dateField.sendKeys(date);
        driver.findElement(By.tagName("body")).click();
    }

    private void fillPeriod(String period) {
        WebElement listPeriod = wait.until(ExpectedConditions
                .elementToBeClickable(LocatorsSecondPageOrder.periodDropdownLocator()));
        listPeriod.click();
        driver.findElement(LocatorsSecondPageOrder.periodOptionLocator(period)).click();
    }

    private void selectColor(String color) {
        By colorLocator = color.equals("black")
                ? LocatorsSecondPageOrder.blackColorCheckboxLocator()
                : LocatorsSecondPageOrder.greyColorCheckboxLocator();
        driver.findElement(colorLocator).click();
    }

    private void clickToOrderButtonOrderPage() {
        WebElement orderButton = wait.until(ExpectedConditions
                .elementToBeClickable(LocatorsSecondPageOrder.orderButtonLocator()));
        orderButton.click();
    }

    private void clickToYesButton() {
        WebElement yesButton = wait.until(ExpectedConditions
                .elementToBeClickable(LocatorsOrderConfirmationPage.yesButtonLocator()));
        yesButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(LocatorsOrderConfirmationPage.statusButtonLocator()));
    }

}



