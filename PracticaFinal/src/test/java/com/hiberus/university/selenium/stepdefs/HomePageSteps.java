package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.HomePage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Given;
import jdk.nashorn.internal.objects.annotations.Getter;

public class HomePageSteps {

    private HomePage homePage;

    @Given("me encuentro en la pagina home")
    public void me_encuentro_en_la_pagina_home() {
        init();
        homePage.navigateTo(HomePage.PAGE_URL);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        homePage = pf.getHomePage();
    }
}
