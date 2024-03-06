package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.CartPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CartPageSteps {

    CartPage cartPage;

    @When("elimino uno de ellos")
    public void elimino_uno_de_ellos() {
        init();
        cartPage.deleteOneProductFromBlackCart();
    }

    @Then("compruebo que en el carrito hay {string} elementos")
    public void compruebo_que_en_el_carrito_hay_elemento(String productCount) {
        init();
        Integer expectedProductCount = Integer.parseInt(productCount);
        Integer actualProductCount = cartPage.getProductCountFromBlackCart();

        Assert.assertEquals("Los productos esperados en el carrito no coinciden con los actuales", expectedProductCount, actualProductCount);
    }

    @Then("compruebo que en el carrito esta el elemento {string} {string} veces")
    public void compruebo_que_en_el_carrito_esta_el_elemento_veces(String productName, String productsAtCart) {
        init();
        Integer expectedAmount = Integer.parseInt(productsAtCart);
        Integer actualAmount = cartPage.getAmmountAtCartOf(productName);

        Assert.assertEquals("La cantidad del articulo '" + productName + "' en el carrito no coincide con el esperado", expectedAmount, actualAmount);
    }

    public void init() {
        PagesFactory pf = PagesFactory.getInstance();
        cartPage = pf.getCartPage();
    }

}
