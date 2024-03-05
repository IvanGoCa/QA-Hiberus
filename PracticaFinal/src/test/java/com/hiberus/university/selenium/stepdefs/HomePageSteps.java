package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.HomePage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import jdk.nashorn.internal.objects.annotations.Getter;

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

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        homePage = pf.getHomePage();
    }
}
