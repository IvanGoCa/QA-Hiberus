package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.CheckoutPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CheckoutPageSteps {

    private CheckoutPage checkoutPage;

    @When("relleno los datos FirstName {string} LastName {string} Zip {string}")
    public void relleno_los_datos_first_name_last_name_zip(String firstName, String lastName, String zipCode) {
        initialize();
        checkoutPage.doCheckout(firstName, lastName, zipCode);
    }

    @When("finalizo el checkout")
    public void finalizo_el_checkout() {
        checkoutPage.clickFinish();
    }

    @Then("valido que el precio total del pedido es el correcto")
    public void valido_que_el_precio_total_del_pedido_es_el_correcto() {
        Float expectedPrize = checkoutPage.getExpectedPrize();
        Float actualPrize = checkoutPage.getActualPrize();

        Assert.assertEquals("Los precios no coinciden", expectedPrize, actualPrize);
    }

    @Then("valido que el mensaje es {string}")
    public void valido_que_el_mensaje_es(String expectedMessage) {
        initialize();
        String actualMessage = checkoutPage.getActualMessage();

        Assert.assertEquals("Los mensajes no coinciden", expectedMessage, actualMessage);
    }

    private void initialize() {
        PagesFactory pf = PagesFactory.getInstance();
        checkoutPage = pf.getCheckoutPage();
    }

}
