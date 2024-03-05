package com.hiberus.university.selenium.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HomePage extends BasePage{

    public static final String PAGE_URL = "https://opencart.abstracta.us/index.php?route=common/home";

    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement MyAccountButton;

    @FindBy(xpath = "//a[@title='My Account']//following-sibling::ul//child::li//child::a[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@title='My Account']//following-sibling::ul//child::li//child::a[text()='Login']")
    private WebElement loginButton;

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void goToRegisterPage() {
        click(getMyAccountButton());
        click(getRegisterButton());
    }

    public void goToLoginPage() {
        click(getMyAccountButton());
        click(getLoginButton());
    }
}
