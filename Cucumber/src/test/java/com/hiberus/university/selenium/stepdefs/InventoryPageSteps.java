package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.InventoryPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPageSteps {

    private InventoryPage inventoryPage;

    @Given("me encuentro en la pagina de inventario")
    public void me_encuentro_en_la_pagina_de_inventario(){
        initialize();
        inventoryPage.navigateTo(InventoryPage.INVENTORY_URL);
    }

    @When("anado al carrito el producto {string}")
    public void anado_al_carrito_el_producto(String productName){
        initialize();
        inventoryPage.clickButtonAddItemToCart(productName);
    }

    @When("elimino el producto {string}")
    public void elimino_el_producto(String productName) {
        initialize();
        inventoryPage.clickButtonRemoveItemFromCart(productName);
    }

    @When("anado al carrito {string} productos")
    public void anado_al_carrito_productos(String numOfProductsToAdd) {
        initialize();
        inventoryPage.addRandomItemsToCart(Integer.parseInt(numOfProductsToAdd));
    }

    @When("selecciono el filtro Z-A")
    public void selecciono_el_filtro_z_a() {
        initialize();
        inventoryPage.clickSelect();
        inventoryPage.clickOptionZA();
    }

    @When("selecciono el filtro Menor a Mayor")
    public void selecciono_el_filtro_menor_a_mayor() {
        initialize();
        inventoryPage.clickSelect();
        inventoryPage.clickOptionLoHi();
    }

    @When("selecciono el filtro Mayor a Menor")
    public void selecciono_el_filtro_mayor_a_menor() {
        initialize();
        inventoryPage.clickSelect();
        inventoryPage.clickOptionHiLo();
    }

    @When("hago click en el boton del carrito")
    public void hago_click_en_el_boton_del_carrito() {
        initialize();
        inventoryPage.clickCart();
    }

    @Then("valido que el numero de productos mostrados es igual a {string}")
    public void valido_que_el_numero_de_productos_mostrados_es_igual_a(String number) {
        initialize();
        Integer expectedTotalProduct = Integer.parseInt(number);

        Integer actualTotalProduct = inventoryPage.sizeOfInventory();

        Assert.assertEquals("El inventario actual no coincide con el esperado", expectedTotalProduct, actualTotalProduct);
    }

    @Then("valido que el producto {string} esta en el inventario")
    public void valido_que_el_producto_esta_en_el_inventario(String productName) {
        initialize();
        String foundItem = inventoryPage.getProductFromInventory(productName);

        Assert.assertNotNull("El producto: " + productName + " no existe en el inventario", foundItem);
    }

    @Then("valido que hay {string} productos en el carrito")
    public void valido_que_hay_productos_en_el_carrito(String numItems) {
        initialize();
        Integer expectedItems = Integer.parseInt(numItems);
        Integer itemsInCart = inventoryPage.getElementsInCart();

        Assert.assertNotNull("No hay elementos en el carrito", itemsInCart);
        Assert.assertEquals("El resultado esperado no coincide con el actual", expectedItems, itemsInCart);
    }

    @Then("valido que el producto no se encuentra en el carrito")
    public void valido_que_el_producto_no_se_encuentra_en_el_carrito() {
        initialize();
        Integer sizeOfCart = inventoryPage.getElementsInCart();

        Assert.assertNull("El carrito no esta vacio", sizeOfCart);
    }

    @Then("valido que los resultados van en orden inverso al alfabeto")
    public void valido_que_los_resultados_van_en_orden_inverso_al_alfabeto() {
        initialize();
        Boolean orderedZA = inventoryPage.isOrderedByZA();

        Assert.assertTrue("La lista no esta ordenada", orderedZA);
    }

    @Then("valido que los resultados van de precio menor a mayor")
    public void valido_que_los_resultados_van_de_precio_menor_a_mayor() {
        initialize();
        Boolean isOrdered = inventoryPage.isOrderedByLoHi();

        Assert.assertTrue("Los items no estan ordenados por precio de menor a mayor", isOrdered);
    }

    @Then("valido que los resultados van de precio mayor a menor")
    public void valido_que_los_resultados_van_de_precio_mayor_a_menor() {
        initialize();
        Boolean isOrdered = inventoryPage.isOrderedByHiLo();

        Assert.assertTrue("Los items no estan ordenados por precio de mayor a menor", isOrdered);
    }

    @Then("me encuentro en la home de la app")
    public void me_encuentro_en_la_home_de_la_app() {
        initialize();
        String actualURL = inventoryPage.getUrl();

        Assert.assertEquals("La prueba es invalida, no nos encontramos en la HOME de la aplicacion",
                "https://www.saucedemo.com/inventory.html", actualURL);
    }

    private void initialize(){
        PagesFactory pf = PagesFactory.getInstance();
        this.inventoryPage = pf.getInventoryPage();
    }
}
