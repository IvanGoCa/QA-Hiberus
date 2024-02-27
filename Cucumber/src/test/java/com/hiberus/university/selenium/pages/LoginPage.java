package com.hiberus.university.selenium.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
@Setter
public class LoginPage extends BasePage {

    public static final String PAGE_URL = "https://www.saucedemo.com";

    /* WebElements */
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    /* Constructor */
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /* Page actions */
    private void writeUsername(String username) {
        sendKeys(getUsernameInput(), username);
    }

    private void writePassword(String pass) {
        sendKeys(getPasswordInput(), pass);
    }

    private void clickLoginButton() {
        click(getLoginButton());
    }

    public void doLogin(String username, String password) {
        writeUsername(username);
        writePassword(password);
        clickLoginButton();
    }

    public Boolean isErrorMessageDisplayed() {
        return getErrorMessage().isDisplayed();
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
}