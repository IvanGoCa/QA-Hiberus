package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.CheckoutPage;
import com.hiberus.university.selenium.pages.HomePage;
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

    @When("introduzco los datos de facturacion: firstName {string}, lastName {string}, email {string}, " +
            "telephone {string}, company {string}, address1 {string}, address2 {string}, city {string}, " +
            "postCode {string}, country {string}, region {string}, radioButton {string}")
    public void introduzco_los_datos_de_facturacion_first_name_last_name_company_address1_city_post_code_country_region(
            String firstName, String lastName, String email, String telephone, String company, String address1,
            String address2, String city, String postCode, String country, String region, String radioButton) {

        init();
        boolean clickRadioButton = radioButton.equalsIgnoreCase("true");
        checkoutPage.writeBillingData(
                firstName, lastName, email, telephone,
                company, address1, address2, city, postCode,
                country, region, clickRadioButton);
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

    @When("le doy click a invitado y continuar")
    public void le_doy_click_a_invitado_y_continuar() {
        init();
        checkoutPage.clickDoCkeckoutAsGuest();
    }

    @When("confirmo el pedido")
    public void confirmo_el_pedido() {
        init();
        checkoutPage.clickConfirmOrder();
    }

    @Then("compruebo que el subtotal es el esperado")
    public void compruebo_que_el_subtotal_es() {
        init();
        Float expectedPrice = HomePage.priceWithoutTax;
        Float actualPrice = checkoutPage.getSubtotalPriceFloat();

        Assert.assertEquals("El subtotal no corresponde con el precio esperado", expectedPrice, actualPrice);
    }

    @Then("aparece el mensaje que muestra la confirmacion de pedidio {string}")
    public void aparece_el_mensaje_que_muestra_la_confirmacion_de_pedidio(String expectedMessage) {
        init();
        String actualMessage = checkoutPage.getConfirmOrderMessageText();

        Assert.assertEquals("Los mensajes de confirmacion de pedido no coinciden", expectedMessage, actualMessage);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        checkoutPage = pf.getCheckoutPage();
    }
}
