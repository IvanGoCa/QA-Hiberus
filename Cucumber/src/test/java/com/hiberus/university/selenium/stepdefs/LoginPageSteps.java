package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.LoginPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps {
    @Given("me encuentro en la pagina de login")
    public void me_encuentro_en_la_pagina_de_login() {
        PagesFactory pf = PagesFactory.getInstance();
        LoginPage loginPage = pf.getLoginPage();
        loginPage.navigateTo(LoginPage.PAGE_URL);
    }

    @When("realizo el login con el user {string} y el pass {string}")
    public void realizo_el_login_con_el_user_y_el_pass(String username, String password) {
        PagesFactory pf = PagesFactory.getInstance();
        LoginPage loginPage = pf.getLoginPage();
        loginPage.doLogin(username, password);
    }

    @Then("visualizo el mensaje de error")
    public void visualizo_el_mensaje_de_error() {
        PagesFactory pf = PagesFactory.getInstance();
        LoginPage loginPage = pf.getLoginPage();

        Assert.assertTrue("No se ha mostrado el mensaje de error", loginPage.isErrorMessageDisplayed());
    }

    @Then("la app me redirige a la pagina de login")
    public void la_app_me_redirige_a_la_pagina_de_login() {
        PagesFactory pf = PagesFactory.getInstance();
        LoginPage loginPage = pf.getLoginPage();

        String expectedURL = LoginPage.PAGE_URL;
        String actualURL = loginPage.getPageUrl();

        Assert.assertEquals("La pagina no es la de login", expectedURL, actualURL);
    }
}
