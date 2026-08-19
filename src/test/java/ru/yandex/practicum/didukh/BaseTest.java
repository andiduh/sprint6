package ru.yandex.practicum.didukh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.didukh.pageobject.ScooterHomePage;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.openSite();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
