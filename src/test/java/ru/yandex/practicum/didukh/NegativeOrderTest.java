package ru.yandex.practicum.didukh;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.practicum.didukh.pageobject.OrderFormPage;
import ru.yandex.practicum.didukh.pageobject.ScooterHomePage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeOrderTest extends BaseTest {

    @ParameterizedTest
    @MethodSource({"incorrectName"})
    public void setIncorrectNameDisplayError (String name) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        objOrderFormPage.setOrderForm(name, "Иванова", "Москва, ул. Ленина, д. 25", "Черкизовская", "89256523214");
        assertTrue(objOrderFormPage.isErrorForNameDisplayed());
    }

    @ParameterizedTest
    @MethodSource({"incorrectSurname"})
    public void setIncorrectSurnameDisplayError (String surname) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        objOrderFormPage.setOrderForm("Анастасия", surname, "Москва, ул. Ленина, д. 25", "Черкизовская", "89256523214");
        assertTrue(objOrderFormPage.isErrorForSurnameDisplayed());
    }

    @ParameterizedTest
    @MethodSource({"incorrectAddress"})
    public void setIncorrectAddressDisplayError (String address) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        objOrderFormPage.setOrderForm("Анастасия", "Иванова", address, "Черкизовская", "89256523214");
        assertTrue(objOrderFormPage.isErrorForAddressDisplayed());
    }

    @ParameterizedTest
    @MethodSource({"incorrectMetroStation"})
    public void setIncorrectMetroStationDisplayError (String metroStation) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        assertTrue(objOrderFormPage.isErrorForMetroStationDisplayed(metroStation));
    }

    @ParameterizedTest
    @MethodSource({"incorrectPhoneNumber"})
    public void setIncorrectPhoneNumberDisplayError (String phoneNumber) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        objOrderFormPage.setOrderForm("Анастасия", "Иванова", "Москва, ул. Ленина, д. 25", "Черкизовская", phoneNumber);
        objOrderFormPage.clickButtonNext();
        assertTrue(objOrderFormPage.isErrorForPhoneNumberDisplayed());
    }

    static Stream<Arguments> incorrectName() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("Я"),
                Arguments.of("english"),
                Arguments.of("123456")
        );
    }

    static Stream<Arguments> incorrectSurname() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("Я"),
                Arguments.of("english"),
                Arguments.of("123456"),
                Arguments.of("Салтыков Щедрин")
        );
    }

    static Stream<Arguments> incorrectAddress() {
        return Stream.of(
                Arguments.of("Я"),
                Arguments.of("english")
        );
    }

    static Stream<Arguments> incorrectMetroStation() {
        return Stream.of(
                Arguments.of("Янино1145"),
                Arguments.of("english"),
                Arguments.of("123456")
        );
    }

    static Stream<Arguments> incorrectPhoneNumber() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("Я"),
                Arguments.of("english"),
                Arguments.of("8925653214"),
                Arguments.of("89256532147896")
        );
    }
}
