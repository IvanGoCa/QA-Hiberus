package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.LoginPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps {

    private LoginPage loginPage;

    @When("introduzco el email {string} y la contrasena {string}")
    public void introduzco_el_email_y_la_contrasena(String email, String password) {
        init();
        loginPage.doLogin(email, password);
    }

    @Then("se muestra el mensaje de error {string}")
    public void se_muestra_el_mensaje_de_error(String expectedErrorMessage) {
        init();
        String actualErrorMessage = loginPage.getErrorMessageText();

        Assert.assertEquals("Los mensajes de error no coinciden", expectedErrorMessage, actualErrorMessage);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        loginPage = pf.getLoginPage();
    }
}
