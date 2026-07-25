package ru.yandex.practicum.didukh.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFormPage {
    private final WebDriver driver;

    // Поле ввода Имя
    private final By inputName = By.xpath(".//input[@placeholder = '* Имя']");
    // Поле ввода Фамилия
    private final By inputSurname = By.xpath(".//input[@placeholder = '* Фамилия']");
    // Поле ввода Адрес
    private final By inputAddress = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    // Поле ввода Метро
    private final By inputMetroStation = By.xpath(".//input[@class = 'select-search__input']");
    // Поле ввода Телефон
    private final By inputPhoneNumber = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private final By buttonNext = By.xpath(".//button[text() = 'Далее']");
    // Текст ошибки для поля Имя
    private final By errorName = By.xpath(".//div[text() = 'Введите корректное имя']");
    // Текст ошибки для поля Фамилия
    private final By errorSurname = By.xpath(".//div[text() = 'Введите корректную фамилию']");
    // Текст ошибки для поля Адрес
    private final By errorAddress = By.xpath(".//div[text() = 'Введите корректный адрес']");
    // Текст ошибки для поля Метро
    private final By errorMetroStation = By.xpath(".//div[text() = 'Выберите станцию']");
    // Текст ошибки для поля Телефон
    private final By errorPhoneNumber = By.xpath(".//div[text() = 'Введите корректный номер']");

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForInputName() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputName));
    }

    public void setInputName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    public void setInputSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }

    public void setInputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void setMetroStation(String station) {
        driver.findElement(inputMetroStation).click();
        driver.findElement(inputMetroStation).sendKeys(station, Keys.DOWN, Keys.ENTER);
    }

    public void setInputPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    public void setOrderForm(String name, String surname, String address, String station, String phoneNumber) {
        waitForInputName();
        setInputName(name);
        setInputSurname(surname);
        setInputAddress(address);
        setMetroStation(station);
        setInputPhoneNumber(phoneNumber);
    }

    public void sendOrderForm(String name, String surname, String address, String station, String phoneNumber) {
        setOrderForm(name, surname, address, station, phoneNumber);
        clickButtonNext();
    }

    public boolean isErrorForNameDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorName))
                .isDisplayed();
    }

    public boolean isErrorForSurnameDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorSurname))
                .isDisplayed();
    }

    public boolean isErrorForAddressDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorAddress))
                .isDisplayed();
    }

    public boolean isErrorForMetroStationDisplayed(String station) {
        driver.findElement(inputMetroStation).sendKeys(station);
        clickButtonNext();
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMetroStation))
                .isDisplayed();
    }

    public boolean isErrorForPhoneNumberDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorPhoneNumber))
                .isDisplayed();
    }
}
