package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.HomePage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.Assert;

public class HomePageSteps {

    private HomePage homePage;

    @Given("me encuentro en la pagina home")
    public void me_encuentro_en_la_pagina_home() {
        init();
        homePage.navigateTo(HomePage.PAGE_URL);
    }

    @When("hago click en el boton de registro")
    public void hago_click_en_el_boton_de_registro() {
        init();
        homePage.goToRegisterPage();
    }

    @When("hago click en el boton de login")
    public void hago_click_en_el_boton_de_login() {
        init();
        homePage.goToLoginPage();
    }

    @When("hago click en el boton de checkout")
    public void hago_click_en_el_boton_checkout() {
        init();
        homePage.goToCheckoutPage();
    }

    @When("anado el producto de feature {string} al carrito {string} veces")
    public void anado_el_producto_de_feature_al_carrito_veces(String productNameToClick, String timesToAdd) {
        init();
        homePage.addProductToCartByName(productNameToClick, Integer.parseInt(timesToAdd));
    }

    @When("anado {string} productos al carrito")
    public void anado_productos_al_carrito(String products) {
        init();
        homePage.addRandomProductsToCart(Integer.parseInt(products));
    }

    @Then("en el apartado featured hay {string} productos")
    public void en_el_apartado_featured_hay_productos(String numString) {
        init();
        Integer expectedNum = Integer.parseInt(numString);
        Integer actualNum = homePage.getProductCount();

        Assert.assertEquals("El numero de productos en el apartado 'Featured' no corresponde con el esperado", expectedNum, actualNum);
    }

    @Then("compruebo que el producto {string} existe")
    public void compruebo_que_el_producto_existe(String expectedProductName) {
        init();
        Boolean productExists = homePage.findProductByName(expectedProductName);

        Assert.assertTrue("El producto '"+ expectedProductName +"' no existe", productExists);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        homePage = pf.getHomePage();
    }
}
