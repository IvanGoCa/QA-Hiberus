package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.CartPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CartPageSteps {
    private CartPage cartPage;

    @When("elimino un producto de la lista")
    public void elimino_un_producto_de_la_lista() {
        initialize();
        cartPage.deleteOneProduct();
    }

    @When("presiono en el boton de checkout")
    public void presiono_en_el_boton_de_checkout() {
        initialize();
        cartPage.clickCheckout();
    }

    @Then("valido que desaparece de la lista")
    public void valido_que_desaparece_de_la_lista() {
        Boolean deleted = cartPage.checkIfProductIsDeleted();

        Assert.assertTrue("El producto sigue en la lista del carrito", deleted);
    }

    private void initialize() {
        PagesFactory pf = PagesFactory.getInstance();
        cartPage = pf.getCartPage();
    }
}
