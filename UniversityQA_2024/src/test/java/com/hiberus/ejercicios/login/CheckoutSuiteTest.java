package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CheckoutSuiteTest {

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
    public void checkFinalPriceOfProducts() {
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
//        List<WebElement> selectedItems = new ArrayList<WebElement>();
        Random random = new Random();
        Float expectedPrize = 0F;
        for (int i = 0; i < 3 && !listItems.isEmpty(); i++) {
            int randomIndex = random.nextInt(listItems.size());
            WebElement randomItem = listItems.get(randomIndex);
            WebElement button = randomItem.findElement(By.xpath(".//button[contains(@id, 'add-to-cart')]"));
            button.click();

//            selectedItems.add(randomItem);
            expectedPrize += Float.parseFloat(randomItem.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText().replaceAll("\\$", ""));
            listItems.remove(randomIndex);
        }

        // 6. Ir al carrito.
        WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cart.click();

//        7. Realizar el Checkout del producto.
        WebElement buttonCheckout = driver.findElement(By.id("checkout"));
        buttonCheckout.click();

//        8. Rellenar datos del checkout y continuar.
        WebElement inputFirstName = driver.findElement(By.id("first-name"));
        WebElement inputLastName = driver.findElement(By.id("last-name"));
        WebElement inputZipCode = driver.findElement(By.id("postal-code"));

        inputFirstName.sendKeys("Ivan");
        inputLastName.sendKeys("Gomez");
        inputZipCode.sendKeys("40200");

        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

//        9. 10. Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario
        WebElement actualPrizeWebElement = driver.findElement(By.className("summary_subtotal_label"));
        Float actualPrize = Float.parseFloat(actualPrizeWebElement.getText().replaceAll("Item total: \\$", ""));

        Assert.assertEquals("LOS PRECIOS NO COINCIDEN", expectedPrize, actualPrize);
    }

    @Test
    public void placeAnOrder() {
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

        int randomIndex = random.nextInt(listItems.size());
        WebElement randomItem = listItems.get(randomIndex);
        WebElement button = randomItem.findElement(By.xpath(".//button[contains(@id, 'add-to-cart')]"));
        button.click();

        // 6. Ir al carrito.
        WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cart.click();

//        7. Realizar el Checkout del producto.
        WebElement buttonCheckout = driver.findElement(By.id("checkout"));
        buttonCheckout.click();

//        8. Rellenar datos del checkout y continuar.
        WebElement inputFirstName = driver.findElement(By.id("first-name"));
        WebElement inputLastName = driver.findElement(By.id("last-name"));
        WebElement inputZipCode = driver.findElement(By.id("postal-code"));

        inputFirstName.sendKeys("Ivan");
        inputLastName.sendKeys("Gomez");
        inputZipCode.sendKeys("40200");

        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

//        9. Finalizar Checkout
        WebElement buttonFinish = driver.findElement(By.id("finish"));
        buttonFinish.click();

//        10. Validar que el pedido a finalizado correctamente mostrando el mensaje “Your order has been dispatched, and will arrive just as fast as the pony can get there!”
        String expectedPhrase = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        WebElement actualPhraseWebElement = driver.findElement(By.xpath("//div[@class='complete-text']"));
        String actualPhrase = actualPhraseWebElement.getText();

        Assert.assertEquals("LAS FRASES NO COINCIDEN", expectedPhrase, actualPhrase);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
