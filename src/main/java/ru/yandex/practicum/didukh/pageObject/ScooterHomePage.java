package ru.yandex.practicum.didukh.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterHomePage {
    private final WebDriver driver;

    // Кнопки с вопросами
    private final By buttonsOfFAQ = By.xpath(".//div[@class='accordion__button']");
    // Кнопка Заказать в хедере
    private final By orderButtonInHeader = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    // Кнопка Заказать на странице
    private final By orderButtonInBody = By.className("Button_Middle__1CSJM");
    // Кнопка закрытия баннера с куки
    private final By cookieButton = By.className("App_CookieButton__3cvqF");
    // Логотип Самокат
    private final By logoScooter = By.xpath(".//img[@alt = 'Scooter']");
    //Логотип Яндекс
    private final By logoYandex = By.xpath(".//img[@alt = 'Yandex']");
    // Кнопка Статус заказа
    private final By buttonOrderStatus = By.xpath(".//button[text() = 'Статус заказа']");
    // Поле ввода Номера заказа
    private final By inputNumberOfOrder = By.xpath(".//input[@placeholder = 'Введите номер заказа']");
    // Кнопка Go!
    private final By buttonGo = By.xpath(".//button[text() = 'Go!']");
    // Вопросы по индексу
    public By question(int index) {
        return  By.id("accordion__heading-" + index);
    }
    // Ответы по индексу
    private By answer(int index) {
        return By.xpath(".//div[@aria-labelledby = 'accordion__heading-" + index + "']/p");
    }

    public ScooterHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void OpenSite() {
        driver.get("https://qa-scooter.education-services.ru/");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cookieButton));
        clickCookieButton();
    }
    public void clickButtonOfFAQ(int index) {
        driver.findElement(question(index)).click();
    }

    public int getButtonsOfFAQCount() {
        return driver.findElements(buttonsOfFAQ).size();
    }

    public String getTextOfAnswer(int index) {
        return driver.findElement(answer(index)).getText();
    }

    public void clickOrderButtonInHeader() {
        driver.findElement(orderButtonInHeader).click();
    }

    public void clickOrderButtonInBody() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonInBody));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(orderButtonInBody).click();
    }

    public void clickLogoYandex() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoYandex));
        driver.findElement(logoYandex).click();
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLogoScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoScooter));
        driver.findElement(logoScooter).click();
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void checkOrderStatus(String number) {
        driver.findElement(buttonOrderStatus).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonGo));
        driver.findElement(inputNumberOfOrder).sendKeys(number);
        driver.findElement(buttonGo).click();
    }
}
