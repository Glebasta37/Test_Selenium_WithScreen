import custom.drivers.Manager;
import custom.properties.TestData;
import custom.units.ScreenAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

import static steps.StepsAll.*;

public class AllTest extends BaseTest {
    @DisplayName("Проверка ядекс маркета")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#provideCheckingMoneyList")
    public void OpenList2(String section, String elementOfSection, List<String> priceFromAndPriceTo) {
        openSiteYandex(TestData.propsUrl.baseURLYandex(), Manager.getCurrentDriver());
        moveToYandexMarket();
        moveAndClick(section, elementOfSection);
        sendOptionsPrice(priceFromAndPriceTo);
    }



    }


