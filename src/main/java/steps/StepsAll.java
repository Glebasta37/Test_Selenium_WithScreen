package steps;

import Pages.YandexMarket;
import Pages.YandexPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class StepsAll {
    private static WebDriver driver;
    private static YandexMarket yandexMarket;
    private static String elementInSearch;

    /**
     * В шаге переходим на указанный url
     * @param url - url для перехода на сайт
     * @param chromeDriver - хромдрайвер
     */

    @Step("Переходим на сайт: {url}")
    public static void openSiteYandex(String url, WebDriver chromeDriver) {
        driver = chromeDriver;
        chromeDriver.get(url);

    }

    /**
     * в шаге переходим на Яндекс Маркет
     */
    @Step("Переходим на Яндекс Маркет")
    public static void moveToYandexMarket() {
        YandexPage yandexPage = new YandexPage(driver);
        yandexPage.clickLinkAndSwitchNextWindow();

    }

    /**
     * В наше навожим курсор на раздел Маркета и далее кликаем по нужному подразделу
     * @param section - раздле маркета
     * @param elementOfSection - подраздел
     */
    @Step("Наводим курсор на: {section}, Переходим в раздел: {elementOfSection}")
    public static void moveAndClick(String section, String elementOfSection) {
        yandexMarket = new YandexMarket(driver);
        yandexMarket.clickLink();
        yandexMarket.moveAndClick(section,elementOfSection);

    }

    /**
     * в шаге задаем цену продукта
     * @param priceFromAndPriceTo - лист содержат цену от и цену до
     */
    @Step("Задать параметр «Цена, Р» от  {priceFrom} до {priceTo} рублей")
    public static void sendOptionsPrice(List<String> priceFromAndPriceTo) {
        yandexMarket.sendPrice(priceFromAndPriceTo);

    }

    /**
     * В шаге выбираем в филтре в разделе Производитель нужные бернды
     * @param header - заголовок раздела фильтра
     * @param brands - нужные бренды для установки фильтра
     */
    @Step("Выбрать производителя {brands}")
    public static void selectManufacturerAndBrand(String header, List<String> brands)  {
        yandexMarket.clickCheckbox(header, brands);

    }

    /**
     *В шаге устанавливаем фильтр на кол-во элементов на страинце
     * @param count - параметр фильтра
     */
    @Step("Установить количество показываемых элементов на страницу: {count}")
    public static void installCountElementsOnPageSearch(String count) {
        yandexMarket.switchCountOfVisibleItems(count);
    }

    /**
     * В шаге проверяем действительно ли кол-во элементов соответствует заданному значению
     * @param count - значение с которым сравниваем кол-во элементов на странице
     */
    @Step("Проверить, что на странице отображалось {count} элементов ")
    public static void validateCountElementsOnPageSearch(String count) {
        yandexMarket.checkCountElementsInSearch(Integer.parseInt(count));
    }

    /**
     * В шаге запоминаем элемент выборки согласно значению
     * @param indexElement - инекс элемента на транице выборки
     */
    @Step("Запомнить наименование первого значения в списке")
    public static void savingFirstElementAndSendHimInFieldSearch(String indexElement) {
        elementInSearch = yandexMarket.getElementInMarketByNumber(Integer.parseInt(indexElement));

    }

    /**
     * В шаге вводим сохраненный элемент в поисковую строку
     */
    @Step("В поисковую строку ввести запомненное значение.")
    public static void searchElementInNewSearchPage() {
        yandexMarket.searchElementInMarketByName(elementInSearch);
    }

    /**
     * В шаге проверяем есть в новой поисковой выборке сохраненый ранее элемент
     */
    @Step("Проверить, что в результатах поиска есть товар с  наименованием равным сохраненному значению")
    public static void validateFieldSearchContainsSaveElement() {
        yandexMarket.validateElementByPresent(elementInSearch);
    }

}