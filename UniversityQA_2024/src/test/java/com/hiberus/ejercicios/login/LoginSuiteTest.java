package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("PRUEBA VALIDA");
        } else {
            System.out.println("** PRUEBA INVALIDA **");
            System.out.println("Valor actual: " + currentUrl);
            System.out.println("Resultado esperado: " + expectedUrl);
        }
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

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Boolean errorMessageIsEnabled = errorMessage.isEnabled();

        if (errorMessageIsEnabled) {
            System.out.println("PRUEBA VALIDA");
        } else {
            System.out.println("** PRUEBA INVALIDA **");
            System.out.println("Valor actual: " + errorMessageIsEnabled);
            System.out.println("Resultado esperado: " + Boolean.TRUE);
        }
    }

    @After
    public void tearDown() {
        //driver.close();
    }

}
