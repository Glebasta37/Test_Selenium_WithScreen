package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> provideCheckingMoneyList() {
        /**
         * Лист содержащий названия чекбоксов, которые нужно отметить
         */
        List<String> markNout = new ArrayList<>();
        markNout.add("HP");
        markNout.add("Lenovo");

        /**
         * Лист содержит значения для раздела фильтра Цена Р: Цена от и Цена после
         */
        List<String> priceFromAndTo = new ArrayList<>();
        priceFromAndTo.add("10000");
        priceFromAndTo.add("900000");
        return Stream.of(
                Arguments.of("Компьютеры", "Ноутбуки", priceFromAndTo, "Производитель", markNout, "12", "0")
        );
    }
}
