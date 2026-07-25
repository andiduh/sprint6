package ru.yandex.practicum.didukh;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.practicum.didukh.pageObject.OrderFormPage;
import ru.yandex.practicum.didukh.pageObject.RentPage;
import ru.yandex.practicum.didukh.pageObject.ScooterHomePage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends CommonTest {

    @ParameterizedTest
    @MethodSource({"infoForRent"})
    public void setOrderInHeader(String name, String surname, String address, String station, String phoneNumber,
                                 String date, String color, String comment) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        RentPage objRentPage = new RentPage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        objOrderFormPage.sendOrderForm(name, surname, address, station, phoneNumber);
        objRentPage.setRent(date, color, comment);
        assertTrue(objRentPage.checkTextOrderPlaced());
    }

    @ParameterizedTest
    @MethodSource({"infoForRent"})
    public void setOrderInBody(String name, String surname, String address, String station, String phoneNumber,
                               String date, String color, String comment) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        RentPage objRentPage = new RentPage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInBody();
        objOrderFormPage.sendOrderForm(name, surname, address, station, phoneNumber);
        objRentPage.setRent(date, color, comment);
        assertTrue(objRentPage.checkTextOrderPlaced());
    }

    static Stream<Arguments> infoForRent() {
        return Stream.of(
                Arguments.of(
                        "Анастасия",
                        "Иванова",
                        "Москва, ул. Ленина, д. 25",
                        "Черкизовская",
                        "89256523214",
                        "29.07.2026",
                        "чёрный жемчуг",
                        "Очень хочу проехать на самокате!"),
                Arguments.of(
                        "Денис",
                        "Сидоров",
                        "Москва, ул. Победы, д. 45",
                        "Строгино",
                        "89877755448",
                        "25.07.2027",
                        "серая безысходность",
                        "Черный жемчуг не привозите, хочу только серую безысходность.")
        );
    }
}
