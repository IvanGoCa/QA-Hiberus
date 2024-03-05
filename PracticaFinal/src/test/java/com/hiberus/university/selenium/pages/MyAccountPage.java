package com.hiberus.university.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends BasePage {

    public static final String PAGE_URL = "https://opencart.abstracta.us/index.php?route=account/account";

    MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getActualUrl() {
        return getDriver().getCurrentUrl();
    }
}
