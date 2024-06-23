import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.StepDefinitions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnit {

    @Test
    void simpleTest() {
        assertEquals(2, 1 + 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level"})
    void parameterizedTest(String word) {
        assertTrue(isPalindrome(word));
    }

    boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
    @Test
    void testOnlyOnSpecificJavaVersion() {
       // System.out.println(System.getProperty("java.version"));
        Assumptions.assumeTrue("17.0.6".equals(System.getProperty("java.version")),
                () -> "not Java 17");

        assertEquals(2, 1 + 1);
    }
    @Test
    void testOnlyOnCiServer() {
        Assumptions.assumeTrue("6".equals(System.getenv("PROCESSOR_LEVEL")),
                () -> "Test not not not");

        assertThat(1 + 1).isEqualTo(2);
    }

    @Test
    void testSelen(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textBox = driver.findElement(By.name("my-text"));
        assertEquals(true, textBox.isEnabled());
        driver.quit();

    }

    @Test
    void testSelen2(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("AAAA");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();
        assertEquals("Received!", message.getText());
        driver.quit();
    }
    @Test
    void testSelen3(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("AAAA!");
        assertEquals("AAAA!", textBox.getAttribute("value"));
        driver.quit();
    }
}
