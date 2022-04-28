package custom.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    public static WebDriverWait wait = new WebDriverWait(Manager.getCurrentDriver(), 120);
    public static WebDriver driver = Manager.getCurrentDriver();

    /**
     * метод отвечает за ожидания пока пропадет серое окно на странице
     */
    public static void waitInvisibleLoad() {
        Waits.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@aria-label='Результаты поиска']/div[not(@data-apiary-widget-name)]")));

    }

    /**
     * метод овечает за ожидание пока объект не станет видимым
     *
     * @param xpath - хpath объекта, который нужно подождать
     */
    public static void visibilityOfElementLocated(By xpath) {
        Waits.wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

    }

}
