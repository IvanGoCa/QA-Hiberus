package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
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

import java.util.Collections;
import java.util.List;
import java.util.Random;
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
        Integer expectedSizeInventory = 6;
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
        List<WebElement> inventoryItems = null;
        Integer actualSizeInventory = null;

        inventoryItems = driver.findElements(By.className("inventory_item"));

        Assert.assertEquals("Resultado esperado: " + expectedSizeInventory + "\nResultado actual: " + actualSizeInventory,
                expectedSizeInventory, actualSizeInventory);

    }

    @Test
    public void addOneItemToCart() {
        Integer expectedSizeOfCart = 1;
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
        WebElement spanShoppingCartBadge = null;
        Integer actualSizeOfCart = 0;

        try {
            spanShoppingCartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
            actualSizeOfCart = Integer.parseInt(spanShoppingCartBadge.getText());
        } catch (NoSuchElementException e) {
            System.out.println("NO SE HA ENCONTRADO EL ELEMENTO DEL CARRITO:\n" + e.getMessage());
        }

        Assert.assertEquals("Resultado esperado: " + expectedSizeOfCart + "\nResultado actual: " + actualSizeOfCart, expectedSizeOfCart, actualSizeOfCart);
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
        WebElement buttonRemove = null;
        Boolean removeButtonIsEnabled = null;

        try {
            buttonRemove = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
            removeButtonIsEnabled = buttonRemove.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("NO SE HA ENCONTRADO EL BOTON DE REMOVER:\n" + e.getMessage());
        }

        Assert.assertTrue("EL BOTON ESTA DESHABILITADO", removeButtonIsEnabled);
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
        List<WebElement> spanShoppingCartBadge = null;
        Boolean cartNumberIsVisible = null;
        spanShoppingCartBadge = driver.findElements(By.xpath("//a[@class='shopping_cart_link']//child::span"));

        if (spanShoppingCartBadge.isEmpty())
            cartNumberIsVisible = Boolean.FALSE;
        else
            cartNumberIsVisible = Boolean.TRUE;

        Assert.assertFalse("EL NUMERO DEL CARRITO SIGUE APARECIENDO", cartNumberIsVisible);
    }

    @Test
    public void addThreeItemsToCart() {
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
        for (int i = 0; i < 3 && listItems.size() > 0; i++) {
            int randomIndex = random.nextInt(listItems.size());
            WebElement randomItem = listItems.get(randomIndex);
            WebElement button = randomItem.findElement(By.xpath(".//button[contains(@id, 'add-to-cart')]"));
            button.click();
            listItems.remove(randomIndex);
        }

        // 6. Validar que, en el icono del carrito, se han agregado los 3 productos.
        WebElement spanShoppingCartBadge = null;
        Integer expectedSizeOfCart = 3;
        Integer actualSizeOfCart = 0;

        try {
            spanShoppingCartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
            actualSizeOfCart = Integer.parseInt(spanShoppingCartBadge.getText());
        } catch (NoSuchElementException e) {
            System.out.println("NO SE HA ENCONTRADO EL ELEMENTO DEL CARRITO:\n" + e.getMessage());
        }

        Assert.assertEquals("Resultado esperado: " + expectedSizeOfCart + "\nResultado actual: " + actualSizeOfCart, expectedSizeOfCart, actualSizeOfCart);

    }

    @Test
    public void orderByZA(){
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

        // 5. Seleccionar el filtro NAME (Z TO A)
        List<WebElement> expectedItemsAZ = driver.findElements(By.className("inventory_item"));
        Collections.reverse(expectedItemsAZ);

        WebElement select = driver.findElement(By.xpath("//select[@class='product_sort_container' and @data-test='product_sort_container']"));
        select.click();
        WebElement optionZtoA = driver.findElement((By.xpath("//select[@class='product_sort_container' and @data-test='product_sort_container']//child::option[@value='za']")));
        optionZtoA.click();
        // 6. Validar que el filtro seleccionado, ordena por el orden alfabético de la Z a la A
        /* La idea es recoger el listado de los 6 items y comprobarlos cuando se recojan al reves que son los mismos
        Por lo que habrá que hacer la búsqueda 2 veces, antes y después */
        List<WebElement> actualItemsZA = driver.findElements(By.className("inventory_item"));

        Assert.assertEquals("LOS ITEMS ORDENADOS DE Z-A NO COINCIDEN CON LOS DE A-Z", expectedItemsAZ, actualItemsZA);
    }

    @Test
    public void orderInventoryByPriceSmallerToGreater() {
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

        // 5. Seleccionar el filtro PRICE (low to high)
        WebElement select = driver.findElement(By.xpath("//select[@class='product_sort_container' and @data-test='product_sort_container']"));
        select.click();
        WebElement priceOption = driver.findElement((By.xpath("//select[@class='product_sort_container' and @data-test='product_sort_container']//child::option[@value='lohi']")));
        priceOption.click();

        // 6. Validar que el filtro seleccionado, ordena por el orden de precio de menor a mayor
        List<WebElement> itemsPrize = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        Boolean pricesInOrder = Boolean.TRUE;
        try {
            if (itemsPrize.size() >= 2) {
                for (int i = 0; i < itemsPrize.size() - 1 && pricesInOrder; i++) {
                    Float currentPrice = Float.parseFloat(itemsPrize.get(i).getText().replaceAll("\\$", ""));
                    Float nextPrice = Float.parseFloat(itemsPrize.get(i + 1).getText().replaceAll("\\$", ""));
                    if (currentPrice > nextPrice) {
                        pricesInOrder = Boolean.FALSE;
                    }
                }
            } else {
                System.out.println("No hay suficientes elementos para comparar los precios.");
            }
        } catch(NoSuchElementException e) {
            System.out.println("NO SE HA ENCONTRADO EL ELEMENTO:\n" + e.getMessage());
        }

        Assert.assertTrue("LOS PRECIOS NO ESTAN ORDENADOS DE MENOR A MAYOR", pricesInOrder);
    }

    @Test
    public void orderInventoryByPriceGreaterToSmaller() {
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

        // 5. Seleccionar el filtro PRICE (high to low)
        WebElement select = driver.findElement(By.xpath("//select[@class='product_sort_container' and @data-test='product_sort_container']"));
        select.click();
        WebElement priceOption = driver.findElement((By.xpath("//select[@class='product_sort_container' and @data-test='product_sort_container']//child::option[@value='hilo']")));
        priceOption.click();

        // 6. Validar que el filtro seleccionado, ordena por el orden de precio de mayor a menor
        List<WebElement> itemsPrize = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        Boolean pricesInOrder = Boolean.TRUE;
        try {
            if (itemsPrize.size() >= 2) {
                for (int i = 0; i < itemsPrize.size() - 1 && pricesInOrder; i++) {
                    Float currentPrice = Float.parseFloat(itemsPrize.get(i).getText().replaceAll("\\$", ""));
                    Float nextPrice = Float.parseFloat(itemsPrize.get(i + 1).getText().replaceAll("\\$", ""));
                    if (currentPrice < nextPrice) {
                        pricesInOrder = Boolean.FALSE;
                    }
                }
            } else {
                System.out.println("No hay suficientes elementos para comparar los precios.");
            }
        } catch(NoSuchElementException e) {
            System.out.println("NO SE HA ENCONTRADO EL ELEMENTO:\n" + e.getMessage());
        }

        Assert.assertTrue("LOS PRECIOS NO ESTAN ORDENADOS DE MENOR A MAYOR", pricesInOrder);
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
