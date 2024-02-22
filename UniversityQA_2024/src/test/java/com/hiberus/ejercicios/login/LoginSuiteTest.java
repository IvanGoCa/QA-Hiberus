package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginSuiteTest {

    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver= new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }

    @Test
    public void loginTest() throws InterruptedException {

        // 1. Ir a la página https://www.saucedemo.com

        driver.get("https://www.saucedemo.com");

        // 2. Escribir el username standard_user

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        // 3. Escribir el password secret_sauce

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        // 4. Pulsar en el botón del Login.

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();

        // 5. Validar que hemos accedido correctamente a la página, comprobando que se muestra la URL https://www.saucedemo.com/inventory.html

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals("El valor actual es: " + currentUrl + "; El valor esperado es: " + expectedUrl, expectedUrl, currentUrl);
    }

    @Test
    public void loginIncorrectTest() {

        // 1. Ir a la página https://www.saucedemo.com

        driver.get("https://www.saucedemo.com");

        // 2. Escribir el username standard_use (Introducimos mal el usuario para forzar el error)

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_use");

        // 3. Escribir el password secret_sauce

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        // 4. Pulsar en el botón del Login

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();

        // 5. Validar que aparece el mensaje de error.
        WebElement errorMessage = null;
        Boolean errorMessageIsEnabled = null;
        try {
            errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            errorMessageIsEnabled = errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            errorMessageIsEnabled = Boolean.FALSE;
        }

        Assert.assertTrue("EL MENSAJE DE ERROR NO APARECE", errorMessageIsEnabled);
    }

    @Test
    public void assertArrayEquals() {
        String[] nombresEsperados = {"A", "B", "C"};
        String[] nombresActuales = {"A", "B", "C"};
        Assert.assertArrayEquals("** MAL **", nombresEsperados, nombresActuales);
    }

    @Test
    public void assertEquals() {
        Assert.assertEquals("** MAL **", "A", "A");
    }

    @Test
    public void assertFalse() {
        Assert.assertFalse("** MAL **", Boolean.FALSE);
    }

    @Test
    public void assertTrue() {
        Assert.assertTrue("** MAL **", Boolean.TRUE);
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull("** MAL **", "HOLA");
    }

    @Test
    public void assertNull() {
        Assert.assertNull("** MAL **", null);
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
