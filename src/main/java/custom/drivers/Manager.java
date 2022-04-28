package custom.drivers;

import custom.units.ScreenAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class Manager {
    /**
     * переменная веб драйвера для запуска драйвера
     */
    private static WebDriver chromeDriver;

    public static WebDriver getCurrentDriver() {

        return chromeDriver;
    }

    /**
     * Иницилизация вебдрайвера и установка его настроек
     */
    public static void initChrome() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized"));
        try {
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
            ScreenAll handler = new ScreenAll();
            eventDriver.register(handler);
            chromeDriver = eventDriver;
        } catch (SessionNotCreatedException e) {
            Assertions.fail("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер.\n" + e);
        }
        setDriverDefaultSettings();

    }

    /**
     * установка неявноо ожидания и очистка куков
     */
    private static void setDriverDefaultSettings() {
        chromeDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        chromeDriver.manage().deleteAllCookies();
    }

    /**
     * закрытие браузера и обнуление хромдрайвера
     */
    public static void killCurrentDriver() {
        if (chromeDriver != null) {
            chromeDriver.quit();
            chromeDriver = null;
        }
    }
}

