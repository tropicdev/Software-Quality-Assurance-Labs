import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class SwagLabsTest {

    WebDriver browser;

    String url = "https://www.saucedemo.com/";

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void tearDown() {
        browser.quit();
    }

    @Test
    void testSiteLoads() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // Assert
        String expected = "Swag Labs";
        String actual = browser.getTitle();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testInvalidUsername() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter invalid username
        browser.findElement(By.id("user-name")).sendKeys("invalid_user");

        // enter valid password
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // get error message
        WebElement errorMessage = browser.findElement(By.cssSelector("h3[data-test='error']"));

        // Assert
        String expected = "Epic sadface: Username and password do not match any user in this service";

        String actual = String.valueOf(errorMessage.getText());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testInvalidPassword() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid username
        browser.findElement(By.id("user-name")).sendKeys("standard_user");

        // enter invalid password
        browser.findElement(By.id("password")).sendKeys("bad_pass");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // get error message
        WebElement errorMessage = browser.findElement(By.cssSelector("h3[data-test='error']"));

        // Assert
        String expected = "Epic sadface: Username and password do not match any user in this service";

        String actual = String.valueOf(errorMessage.getText());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testNoUsername() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid password
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // get error message
        WebElement errorMessage = browser.findElement(By.cssSelector("h3[data-test='error']"));

        // Assert
        String expected = "Epic sadface: Username is required";

        String actual = String.valueOf(errorMessage.getText());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testNoPassword() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid username
        browser.findElement(By.id("user-name")).sendKeys("standard_user");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // get error message
        WebElement errorMessage = browser.findElement(By.cssSelector("h3[data-test='error']"));

        // Assert
        String expected = "Epic sadface: Password is required";

        String actual = String.valueOf(errorMessage.getText());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testNoCredentials() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // click login button
        browser.findElement(By.id("login-button")).click();

        // get error message
        WebElement errorMessage = browser.findElement(By.cssSelector("h3[data-test='error']"));

        // Assert
        String expected = "Epic sadface: Username is required";

        String actual = String.valueOf(errorMessage.getText());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testValidUserCredentials() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid username
        browser.findElement(By.id("user-name")).sendKeys("standard_user");

        // enter valid password
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // Assert
        String expected = "https://www.saucedemo.com/inventory.html";
        String actual = browser.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testLogout() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid username
        browser.findElement(By.id("user-name")).sendKeys("standard_user");

        // enter valid password
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // click burger menu
        browser.findElement(By.id("react-burger-menu-btn")).click();

        // wait for elements to render

        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // click logout button
        browser.findElement(By.id("logout_sidebar_link")).click();

        // try to navigate to inventory page
        browser.navigate().to("https://www.saucedemo.com/inventory.html");

        // get error message
        WebElement errorMessage = browser.findElement(By.cssSelector("h3[data-test='error']"));

        // Assert
        String expected = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

        String actual = String.valueOf(errorMessage.getText());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAboutRedirect() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid username
        browser.findElement(By.id("user-name")).sendKeys("standard_user");

        // enter valid password
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // open burger menu
        browser.findElement(By.id("react-burger-menu-btn")).click();

        // wait for elements to render

        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // click about button
        browser.findElement(By.id("about_sidebar_link")).click();

        // Assert
        String expected = "https://saucelabs.com/";
        String actual = browser.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddToCart() {
        // Arrange
        browser = new ChromeDriver();

        // Act
        browser.navigate().to(url);

        // enter valid username
        browser.findElement(By.id("user-name")).sendKeys("standard_user");

        // enter valid password
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // click login button
        browser.findElement(By.id("login-button")).click();

        // click add to cart button
        browser.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // click shopping cart button
        browser.findElement(By.cssSelector("a[class='shopping_cart_link']")).click();

        // click item
        browser.findElement(By.id("item_4_title_link")).click();

        // Assert
        String expected = "https://www.saucedemo.com/inventory-item.html?id=4";
        String actual = browser.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }
}


