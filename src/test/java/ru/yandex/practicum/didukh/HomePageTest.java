package ru.yandex.practicum.didukh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.didukh.pageobject.ScooterHomePage;
import ru.yandex.practicum.didukh.pageobject.TrackPage;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static ru.yandex.practicum.didukh.constants.URL;

public class HomePageTest extends BaseTest {

    private final String[] expectedAnswersList = new String[] {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    @ParameterizedTest
    @MethodSource({"numberOfQuestion"})
    public void clickFaqsButtonsOpenAnswers(int i) {

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);

        WebElement element = driver.findElement(objScooterHomePage.question(i));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        objScooterHomePage.clickButtonOfFaq(i);
        assertEquals(expectedAnswersList[i], objScooterHomePage.getTextOfAnswer(i));
    }

    @Test
    public void clickLogoYandexOpenYandexHomePage() {
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);

        objScooterHomePage.clickLogoYandex();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("ya.ru"));
    }

    @Test
    public void clickLogoScooterOpenHomePage() {
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);

        objScooterHomePage.clickOrderButtonInHeader();
        objScooterHomePage.clickLogoScooter();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        assertEquals(URL, Objects.requireNonNull(driver.getCurrentUrl()));
    }

    @ParameterizedTest
    @MethodSource({"incorrectNumberOfOrder"})
    public void inputIncorrectNumberOfOrderOpenPageNotOrder(String number) {
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        TrackPage objTrackPage = new TrackPage(driver);

        objScooterHomePage.checkOrderStatus(number);
        assertTrue(objTrackPage.isErrorTextDisplayed());
    }

    static Stream<Arguments> numberOfQuestion() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7)
        );
    }

    static Stream<Arguments> incorrectNumberOfOrder() {
        return Stream.of(
                Arguments.of(
                        "123456"),
                Arguments.of(
                        "9876")
        );
    }
}
