package Project.PageObject;

import org.openqa.selenium.By;

public class LocatorsFirstPageOrder {
    // Локатор поля "Имя"
    public static By nameLocator() {
        return By.cssSelector(".Order_Form__17u6u > div:nth-child(1) > input:nth-child(1)");
    }
    // Локатор поля "Фамилия"
    public static By surnameLocator() {
        return By.cssSelector("div.Input_InputContainer__3NykH:nth-child(2) > input:nth-child(1)");
    }
    // Локатор поля "Адрес"
    public static By addressLocator() {
        return By.cssSelector("div.Input_InputContainer__3NykH:nth-child(3) > input:nth-child(1)");
    }
    // Локатор списка "Метро"
    public static By metroLocator() {
        return By.xpath("//input[@placeholder='* Станция метро']");
    }
    // Локатор поля "Телефон"
    public static By phoneLocator() {
        return By.cssSelector("div.Input_InputContainer__3NykH:nth-child(5) > input:nth-child(1)");
    }
    // Локатор кнопки "Далее"
    public static By nextButtonLocator() {
        return By.cssSelector(".Button_Middle__1CSJM");
    }
}
