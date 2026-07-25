package ru.yandex.practicum.didukh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.didukh.pageObject.ScooterHomePage;

public class CommonTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.OpenSite();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
