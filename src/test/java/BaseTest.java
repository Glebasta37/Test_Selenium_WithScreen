import custom.drivers.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public class BaseTest {
    protected WebDriver chromeDriver;

    /**
     * Действия в методе выполняются перед стартов тестов
     */



    @BeforeEach
    public void beforeTest() {
        Manager.initChrome();
        chromeDriver = Manager.getCurrentDriver();
    }

    /**
     * Действия в методе выполняются после тестов
     */
    @AfterEach
    public void closeChromeDriver() {
        Manager.killCurrentDriver();
    }
}
