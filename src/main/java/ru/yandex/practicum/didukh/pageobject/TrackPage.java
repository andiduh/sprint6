package ru.yandex.practicum.didukh.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackPage {

    private final WebDriver driver;

    // Текст ошибки при некорректном вводе номера заказа
    private final By errorText = By.xpath(".//img[@alt ='Not found']");

    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isErrorTextDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorText));
        return driver.findElement(errorText).isDisplayed();
    }
}
