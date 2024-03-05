package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.PagesFactory;
import com.hiberus.university.selenium.pages.RegisterPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class RegisterSteps {

    RegisterPage registerPage;

    @When("le doy al boton de continuar")
    public void le_doy_al_boton_de_continuar() {
        init();
        registerPage.clickContinueButton();
    }

    @When("hago click en el checkbox de PrivacyPolicy")
    public void hago_click_en_el_checkbox_de_privacy_policy() {
        init();
        registerPage.clickCheckboxPrivacyPolicy();
    }

    @When("realizo el registro con el firstName {string}, lastName {string}, email {string}, telephone {string}, password {string}")
    public void realizo_el_registro_con_el_first_name_last_name_email_telephone_password(String firstName, String lastName, String email, String telephone, String password) {
        init();
        registerPage.registerWithAllData(firstName, lastName, email, telephone, password);
    }

    @Then("aparece el mensaje de error {string}")
    public void aparece_el_mensaje_de_error(String expectedErrorMessage) {
        init();
        String actualErrorMessage = registerPage.getErrorMessageText();

        Assert.assertEquals("Los mensajes de error no coinciden", expectedErrorMessage, actualErrorMessage);
    }

    @Then("aparecen todos los mensajes de error de los inputs obligatorios")
    public void aparecen_todos_los_mensajes_de_error_de_los_inputs_obligatorios() {
        init();

        Assert.assertTrue("No aparecen todos los mensajes de error de los inputs obligatorios", registerPage.areAllErrorMessagesVisible());
    }

    @Then("aparece el siguiente mensaje {string}")
    public void aparece_el_siguiente_mensaje(String expectedMessage) {
        init();
        String actualMessage = registerPage.getSuccessMessageText();

        Assert.assertEquals("Los mensajes de registro no coinciden", expectedMessage, actualMessage);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        registerPage = pf.getRegisterPage();
    }

}
