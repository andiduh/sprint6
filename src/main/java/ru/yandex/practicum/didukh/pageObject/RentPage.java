package ru.yandex.practicum.didukh.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class RentPage {
    private final WebDriver driver;

    // Поле ввода Даты
    private final By inputDate = By.xpath("//input[@placeholder = '* Когда привезти самокат']");
    // Поле Срок аренды
    private final By dropdownTenancy = By.xpath(".//div[@class = 'Dropdown-control']");
    // Выпадающий список поля Срок аренды
    private final By dropdownOptions = By.xpath("//div[contains(@class,'Dropdown-option')]");
    // Вариант чек-бокса Цвета - чёрный жемчуг
    private final By checkboxColorBlack = By.xpath(".//label[@for = 'black']");
    // Вариант чек-бокса Цвета - серая безысходность
    private final By checkboxColorGrey = By.xpath(".//label[@for = 'grey']");
    // Поле ввода Комментарий
    private final By inputComment = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    // Кнопка Заказать
    private final By buttonOrder = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка Да
    private final By buttonYes = By.xpath(".//button[text() = 'Да']");
    // Текст оформления заказа
    private final By textOrderPlaced = By.xpath("//div[text() = 'Заказ оформлен']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setInputDate(String date) {
        driver.findElement(inputDate).click();
        driver.findElement(inputDate).sendKeys(date, Keys.ENTER);
    }

    public void waitForSelectTenancy() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(dropdownOptions));
    }

    public void waitForButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonOrder));
    }

    public void selectRandomOptionOfTenancy() {
        driver.findElement(dropdownTenancy).click();
        waitForSelectTenancy();

        List<WebElement> options = driver.findElements(dropdownOptions);

        Random random = new Random();

        options.get(random.nextInt(options.size())).click();
    }

    public void setCheckboxColor(String color) {
        if ("чёрный жемчуг".equals(color)) {
            driver.findElement(checkboxColorBlack).click();
        } else {
            driver.findElement(checkboxColorGrey).click();
        }
    }

    public void setInputComment(String comment) {
        driver.findElement(inputComment).click();
        driver.findElement(inputComment).sendKeys(comment);
    }

    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    public void waitForConfirmationOfOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonYes));
    }

    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

    public void setConfirmationOfOrder() {
        waitForConfirmationOfOrder();
        clickButtonYes();
    }

    public boolean checkTextOrderPlaced() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textOrderPlaced));
        return driver.findElement(textOrderPlaced).isDisplayed();
    }
    public void setRent(String date, String color, String comment) {
        waitForButtonOrder();
        setInputDate(date);
        selectRandomOptionOfTenancy();
        setCheckboxColor(color);
        setInputComment(comment);
        clickButtonOrder();
        setConfirmationOfOrder();
    }
}
