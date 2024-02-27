package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.CheckoutPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutPageSteps {

    private CheckoutPage checkoutPage;

    @When("relleno los datos FirstName {string} LastName {string} Zip {string}")
    public void relleno_los_datos_first_name_last_name_zip(String firstName, String lastName, String zipCode) {
        initialize();
        checkoutPage.doCheckout(firstName, lastName, zipCode);
    }

    @Then("valido que el precio total del pedido es el correcto")
    public void valido_que_el_precio_total_del_pedido_es_el_correcto() {
        
    }

    private void initialize() {
        PagesFactory pf = PagesFactory.getInstance();
        checkoutPage = pf.getCheckoutPage();
    }

}
