package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.CheckoutPage;
import com.hiberus.university.selenium.pages.FeaturedProducts;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CheckoutPageSteps {
    CheckoutPage checkoutPage;

    @When("introduzco el email {string} y la contrasena {string} en el checkout")
    public void introduzco_el_email_y_la_contrasena_en_el_checkout(String email, String pass) {
        init();
        checkoutPage.doLogin(email, pass);
    }

    @When("introduzco los datos de facturacion: firstName {string}, lastName {string}, company {string}, address1 {string}, city {string}, postCode {string}, country {string}, region {string}")
    public void introduzco_los_datos_de_facturacion_first_name_last_name_company_address1_city_post_code_country_region(String firstName, String lastName, String company, String address1, String city, String postCode, String country, String region) {
        init();
        checkoutPage.writeBillingData(firstName, lastName, company, address1, city, postCode, country, region);
    }

    @When("hago click en el boton de continuar en el delivery details")
    public void hago_click_en_el_boton_de_continuar_en_el_delivery_detalis(){
        init();
        checkoutPage.clickContinueDeliveryDetails();
    }

    @When("hago click en el boton de continuar en el delivery method")
    public void hago_click_en_el_boton_de_continuar_en_el_delivery_method(){
        init();
        checkoutPage.clickContinueDeliveryMethod();
    }

    @When("hago click en el boton de continuar en el payment method aceptando los terminos y condiciones")
    public void hago_click_en_el_boton_de_continuar_en_el_payment_method_aceptando_los_terminos_y_condiciones() {
        init();
        checkoutPage.acceptPayment();
    }

    @Then("compruebo que el subtotal es el esperado")
    public void compruebo_que_el_subtotal_es() {
        init();
        Float expectedPrice = FeaturedProducts.prizeWithoutTax;
        Float actualPrice = checkoutPage.getSubtotalPriceFloat();

        Assert.assertEquals("El subtotal no corresponde con el precio esperado", expectedPrice, actualPrice);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        checkoutPage = pf.getCheckoutPage();
    }
}
