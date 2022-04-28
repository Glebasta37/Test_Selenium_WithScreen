package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class YandexPage {
    /**
     * переменная  хранит ссылку на Яндекс марке
     */
    private final WebElement buttonMarket;
    private final WebDriver webDriver;

    public YandexPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.buttonMarket = webDriver.findElement(By.xpath("//a[@data-id='market']"));
    }

    /**
     * Метод кликает по ссылке и переходит на новую вкладку
     */
    public void clickLinkAndSwitchNextWindow() {
        buttonMarket.click();
        Set<String> tabs = webDriver.getWindowHandles();
        for (String tab : tabs)
            webDriver.switchTo().window(tab);
    }
}
