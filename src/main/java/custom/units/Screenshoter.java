package custom.units;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Screenshoter {
    /**
     * Метод отвечает за создание скриншотов
     * @param driver - вебдрайвер
     * @return - скриншот ввиде массива байт
     */
    @Attachment
    public static byte[] getScreen(WebDriver driver) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить файл на диск");
        }
        return new byte[0];
    }

}
