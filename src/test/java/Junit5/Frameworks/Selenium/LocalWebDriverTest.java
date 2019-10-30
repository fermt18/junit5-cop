package Junit5.Frameworks.Selenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

@ExtendWith(SeleniumExtension.class)
public class LocalWebDriverTest {

    @Test
    public void testWithChrome(ChromeDriver chrome) {
        chrome.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertTrue(chrome.getTitle().startsWith("Selenium-Jupiter"));
    }

    @Disabled("Firefox and Opera not installed")
    @Test
    public void testWithFirefoxAndOpera(FirefoxDriver firefox,
                                        OperaDriver opera) {
        firefox.get("http://www.seleniumhq.org/");
        opera.get("http://junit.org/junit5/");
        assertTrue(firefox.getTitle().startsWith("Selenium"));
        assertTrue(opera.getTitle().equals("JUnit 5"));
    }

    @Disabled("Throws ParameterResolutionException")
    @Test
    public void testWithHeadlessBrowsers(HtmlUnitDriver htmlUnit,
                                         PhantomJSDriver phantomjs) {
        htmlUnit.get("https://bonigarcia.github.io/selenium-jupiter/");
        phantomjs.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertTrue(htmlUnit.getTitle().contains("JUnit 5 extension"));
        assertNotNull(phantomjs.getPageSource());
    }

}
