package Pages;

import custom.units.Screenshoter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static custom.drivers.Waits.visibilityOfElementLocated;
import static custom.drivers.Waits.waitInvisibleLoad;

public class YandexMarket {
    /**
     * переменная отвеает за сохранение в ней Вебдрайвера
     */
    protected WebDriver driver;
    /**
     * переменная отвечает за сохранения в ней ссылки на Маркет Яндекса
     */
    protected WebElement market;
    /**
     * переменная отвечает за сохранения в пареметров неявного ожидания
     */
    protected WebDriverWait wait;

    /**
     * Конструктор класса
     *
     * @param driver - веб драйвер
     */
    public YandexMarket(WebDriver driver) {
        this.driver = driver;
        this.market = driver.findElement(By.xpath("//button[@id='catalogPopupButton']"));
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Метод используется для наведения на элемент "Раздел" после чего кликает по заданной "Категории"
     *
     * @param moveToElement - элемент для наведения курсора
     * @param clickElement  - элемент для клика
     */

    public void moveAndClick(String moveToElement, String clickElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='" + moveToElement + "']")))
                .build()
                .perform();
        driver.findElement(By.xpath("//a[text()='" + clickElement + "']")).click();

    }

    /**
     * Метод кликает и переходит на Яндекс маркет
     */
    public void clickLink() {
        this.market.click();
    }

    /**
     * Метод отвечает за ввод цены в раздел Цена Р
     *
     * @param priceFromAndTo - cписок задержащий 2 элемента: Цена от, Цена до
     */
    public void sendPrice(List<String> priceFromAndTo) {
        checkExistObjectOnPageAndRefresh(By.xpath("//div[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@type='button']"));
        List<WebElement> fieldPrice = driver.findElements(By.xpath("//legend[contains(.,'Цена')]//following-sibling::div//input[@type='text']"));
        for (int i = 0; i < priceFromAndTo.size(); i++) {
            fieldPrice.get(i).sendKeys(priceFromAndTo.get(i));

        }
        waitInvisibleLoad();

    }

    /**
     * метод отвечает есть ли элемент на странице, если да, то ожидает прогрузки элементав списка
     *
     * @param xpathButton - xpath элемента кнопки "Показать все"
     */
    public void clockOnButton(By xpathButton, By xpath) {
        if (driver.findElements((xpathButton)).size() > 0) {
            driver.findElement(xpathButton).click();
            visibilityOfElementLocated(xpath);
        }

    }

    /**
     * Метод проверяет есть ли элемент на странице и если его нет, кидает Ассерт
     *
     * @param xpathButton - xpath элемента для поиска на странице
     */
    public void checkExistObjectOnPageAndRefresh(By xpathButton) {
        Assertions.assertTrue(driver.findElements((xpathButton)).size() > 0, "На странице нет кнопки Показать 48, дальнейшее прохождение теста невозможно");
    }

    /**
     * Метод проверяет включел ли чекбок или нет
     *
     * @param header  - заголовок раздела фильтра
     * @param element - элемент для проверки (чекбокс)
     * @return если true - значит чек бокс включен, если false - значит чекбокс выключен
     */
    public boolean isValidateCheckBoxOn(String header, String element) {
        String idHeader = driver.findElement(By.xpath("//legend[contains(.,'" + header + "')]/..")).getAttribute("data-autotest-id");
        String idCheckbox = driver.findElement(By.xpath("//legend[text()='" + header + "']/..//label//input[contains(@name,' " + element + "')]")).getAttribute("id");
        String hrefWithGlfilter = driver.findElement(By.xpath("//a[@data-auto='intent-link']")).getAttribute("href");
        return hrefWithGlfilter.contains(idCheckbox.replace(idHeader + "_", "")) && hrefWithGlfilter.contains(idHeader);
    }

    /**
     * Метод отвеачет за поиск категории филтра и выбор нужных чекбоксов
     *
     * @param header - заголовок поля филтра
     * @param brand  - нужные чекбоксы для клика
     */
    public void clickCheckbox(String header, List<String> brand) {
        By buttonShowAll = By.xpath("//legend[text()='Производитель']//following-sibling::footer/button[text()='Показать всё']");
        By xpath = By.xpath("//input[@name='Поле поиска']");
        clockOnButton(buttonShowAll, xpath);
        WebElement elementSearch = driver.findElement(xpath);
        for (String element : brand) {
            elementSearch.clear();
            elementSearch.sendKeys(element);
            if (!isValidateCheckBoxOn(header, element)) {
                driver.findElement(By.xpath("//legend[text()='" + header + "']/..//label//div//span[text()='" + element + "']")).click();
            }

        }
        waitInvisibleLoad();
    }

    /**
     * Метод отвечает за переключения списка показаных элементов на странице
     *
     * @param count - количество элементов на странице
     */
    public void switchCountOfVisibleItems(String count) {
        WebElement button = driver.findElement(By.xpath("//div[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@type='button']"));
        button.click();
        List<WebElement> listButton = button.findElements(By.xpath("//following::div//button[contains(.,'Показывать')]"));
        listButton.stream().filter(x -> x.getText().contains(count)).forEach(WebElement::click);
        waitInvisibleLoad();
    }

    /**
     * Метод проверяет соответствует ли размерность списка поиска заданному значению
     *
     * @param count -  значение для проверки размерности списка
     */
    public void checkCountElementsInSearch(Integer count) {
        Assertions.assertEquals((int) count, getSearchResult().size(), "Колиичество элементов в выборке не соответствует заданному значению " + count);
    }

    /**
     * Метод возвращает список элементов поиска или применения фильтра
     *
     * @return - возвращает список элементов после применения поиска или фильтра
     */
    public List<WebElement> getSearchResult() {
        return driver.findElements(By.xpath("//div[@data-zone-name='SearchPartition']//a[@data-node-name='title']"));
    }

    /**
     * Метод возвращает n-ый элемент из выборки
     *
     * @param indexElement - номер элемента, который мы берем из выборке
     * @return - елемент под номером indexElement
     */

    public String getElementInMarketByNumber(Integer indexElement) {
        return getSearchResult().get(indexElement).getText();
    }

    /**
     * Метод добавляет элемент в поисковую строку и нажимает поиск
     *
     * @param name - элемент для поиска
     */
    public void searchElementInMarketByName(String name) {
        driver.findElement(By.xpath("//input[@id='header-search']")).sendKeys(name);
        driver.findElement(By.xpath("//span[contains(.,'Найти')]")).click();
        visibilityOfElementLocated(By.xpath("//div[@data-zone-name='SearchPartition']//a[@data-node-name='title']"));
    }

    /**
     * Метод проверяет есть ли переданный элемент в выборке после его поиска
     *
     * @param name - элемент для проверки в поиске
     */
    public void validateElementByPresent(String name) {
        List<WebElement> listElements = getSearchResult();
        Assertions.assertTrue(listElements
                .stream()
                .anyMatch(x -> x.getText().contains(name)), "Сохраненного элемента нет на странице поиска");
    }

}







