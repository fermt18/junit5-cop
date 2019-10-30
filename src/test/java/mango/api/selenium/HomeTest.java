package mango.api.selenium;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
class HomeTest {

    @Test
    void testWithChrome(ChromeDriver chrome) {
        chrome.get("https://shop.mango.com/es");
        assertTrue(chrome.getTitle().startsWith("Mango España | Moda y ropa online"));
    }

    @Disabled("Firefox and Opera not installed")
    @Test
    void testWithFirefoxAndOpera(FirefoxDriver firefox,
                                 OperaDriver opera) {
        firefox.get("http://www.seleniumhq.org/");
        opera.get("http://junit.org/junit5/");
        assertTrue(firefox.getTitle().startsWith("Selenium"));
        assertTrue(opera.getTitle().equals("JUnit 5"));
    }
}
