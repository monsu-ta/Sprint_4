package Project.PageObject;

import org.openqa.selenium.By;

public class LocatorsMainPage {
    // Локатор кнопки куки
    public static By cookieLocator = By.cssSelector("#rcc-confirm-button");

    // Локатор маленькой кнопки заказа
    public static By orderHeaderButtonLocator = By.className("Button_Button__ra12g");

    // Локатор большой кнопки заказа
    public static By orderBigButtonLocator = By.cssSelector(".Button_Middle__1CSJM");

    // Локатор пунктов списка вопросов
    public static By cardButton(int index) {
        return By.id("accordion__heading-" + index);
    }

    // Локатор текстов ответов на вопросы
    public static By cardContent(int index) {
        return By.id("accordion__panel-" + index);
    }
}



