package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.MyAccountPage;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class MyAccountPageSteps {

    MyAccountPage myAccountPage;

    @Then("me encuentro en la pagina de MyAccount {string}")
    public void me_encuentro_en_la_pagina_de_my_account(String expectedUrl) {
        init();

        String actualUrl = myAccountPage.getActualUrl();

        Assert.assertEquals("Las URL no coinciden", expectedUrl, actualUrl);
    }

    private void init() {
        PagesFactory pf = PagesFactory.getInstance();
        myAccountPage = pf.getMyAccountPage();
    }

}
