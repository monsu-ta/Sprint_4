package Project.PageObject;

import org.openqa.selenium.By;

public class LocatorsSecondPageOrder {

    // Локатор поля выбора даты
    public static By dateFieldLocator() {
        return By.xpath("//input[@placeholder='* Когда привезти самокат']");
    }

    // Локатор списка периода
    public static By periodDropdownLocator() {
        return By.cssSelector(".Dropdown-control");
    }

    // Локатор варианта периода
    public static By periodOptionLocator(String period) {
        return By.xpath(String.format("//div[text()='%s']", period));
    }

    // Локатор выбора цвета "Чёрный жемчуг"
    public static By blackColorCheckboxLocator() {
        return By.cssSelector("input#black");
    }

    // Локатор выбора цвета "Серая безысходность"
    public static By greyColorCheckboxLocator() {
        return By.cssSelector("input#grey");
    }

    // Локатор кнопки "Заказать"
    public static By orderButtonLocator() {
        return By.xpath("//button[contains(., 'Заказать') and contains(@class, 'Button_Middle')]");
    }

    // Локатор кнопки подтверждения
    public static By confirmButtonLocator() {
        return By.xpath("//button[text()='Да']");
    }

    // Локатор окна подтвержденного заказа
    public static By successModalLocator() {
        return By.xpath("//div[contains(@class, 'Order_ModalHeader')]");
    }
}

