package Project.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocatorsOrderConfirmationPage {
    // Кнопка подтверждения заказа
    public static By yesButtonLocator() {
        return By.cssSelector("div.Order_Buttons__1xGrp:nth-child(2) > button:nth-child(2)");
    }

    // Кнопка проверки статуса
    public static By statusButtonLocator() {
        return By.cssSelector(".Order_NextButton__1_rCA > button:nth-child(1)");
    }

}
