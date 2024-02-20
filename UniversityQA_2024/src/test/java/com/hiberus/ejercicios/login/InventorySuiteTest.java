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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InventorySuiteTest {

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
    public void validateAllInventoryResults() {
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

        // 5. Validar que el número de productos mostrados es igual a 6.

        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
        Integer inventoryListSize = inventoryItems.size();

        if(inventoryListSize.equals(6)) {
            System.out.println("PRUEBA VALIDA");
        } else {
            System.out.println("** PRUEBA INVALIDA **");
            System.out.println("Valor actual: " + inventoryListSize);
            System.out.println("Resultado esperado: " + 6);
        }

    }

    @Test
    public void addOneItemToCart() {

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

        // 5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt

        WebElement buttonAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        buttonAddToCart.click();

        // 6. Validamos que en el icono del carrito se ha agregado el valor 1.
        WebElement spanShoppingCartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        Integer sizeOfCart = Integer.parseInt(spanShoppingCartBadge.getText());

        if (sizeOfCart.equals(1)) {
            System.out.println("PRUEBA VALIDA");
        } else {
            System.out.println("** PRUEBA INVALIDA **");
            System.out.println("Valor actual: " + spanShoppingCartBadge);
            System.out.println("Resultado esperado: " + 1);
        }
    }

    @Test
    public void visibilityDelteButton() {
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

        // 5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt

        WebElement buttonAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        buttonAddToCart.click();

        // 6. Validamos que, al agregar el producto, se visualiza el botón REMOVE
        WebElement buttonRemove = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        if (buttonRemove.isEnabled()) {
            System.out.println("PRUEBA VALIDA");
        } else {
            System.out.println("** PRUEBA INVALIDA **");
            System.out.println("Valor actual: " + buttonRemove.isEnabled());
            System.out.println("Resultado esperado: " + Boolean.FALSE);
        }
    }

    @Test
    public void deleteProductFromCart() {
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

        // 5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt

        WebElement buttonAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        buttonAddToCart.click();

        // 6. Eliminar producto del carrito.

        WebElement buttonRemove = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        buttonRemove.click();

        // 7. Validar que en el icono del carrito se ha eliminado el producto.
        List<WebElement> spanShoppingCartBadge = driver.findElements(By.xpath("//a[@class='shopping_cart_link']//child::span"));
        if (spanShoppingCartBadge.isEmpty()) {
            System.out.println("PRUEBA VALIDA");
        } else {
            System.out.println("** PRUEBA INVALIDA **");
            System.out.println("Valor actual: " + spanShoppingCartBadge.isEmpty());
            System.out.println("Resultado esperado: " + Boolean.TRUE);
        }
    }

    @After
    public void tearDown() {
        //driver.close();
    }

}
