package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CartTestSuite {

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

        // 5. Agregar al carrito los 3 productos elegidos al azar.

        List<WebElement> listItems = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        Random random = new Random();
        for (int i = 0; i < 2 && listItems.size() > 0; i++) {
            int randomIndex = random.nextInt(listItems.size());
            WebElement randomItem = listItems.get(randomIndex);
            WebElement button = randomItem.findElement(By.xpath(".//button[contains(@id, 'add-to-cart')]"));
            button.click();
            listItems.remove(randomIndex);
        }

//        6. Ir al carrito.
        WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cart.click();
//        7. Eliminar uno de los productos
        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_list']//child::div[@class='cart_item']"));
        int randomIndex = random.nextInt(cartItems.size());
        WebElement itemRemoved = cartItems.get(randomIndex);
        WebElement removeButton = itemRemoved.findElement(By.xpath(".//child::button"));
        removeButton.click();

//        8. Validar que el producto eliminado, no aparece en el carrito
        cartItems.clear();
        cartItems = driver.findElements(By.xpath("//div[@class='cart_list']//child::div[@class='cart_item']"));

        Boolean itemStillExists = Boolean.FALSE;
        try {
            for (WebElement item : cartItems) {
                if (itemRemoved.equals(item))
                    itemStillExists = Boolean.TRUE;
            }
        } catch (NoSuchElementException e){
            System.out.println("NO EXISTE EL ELEMENTO:\n" + e.getMessage());
            itemStillExists = Boolean.TRUE;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("FALTAN PRODUCTOS EN EL CARRITO\n" + e.getMessage());
            itemStillExists = Boolean.TRUE;
        }

        Assert.assertFalse("EL PRODUCTO SIGUE EN EL CARRTITO", itemStillExists);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
