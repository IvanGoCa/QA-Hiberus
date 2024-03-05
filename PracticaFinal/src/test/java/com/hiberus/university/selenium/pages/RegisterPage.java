package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RegisterPage extends BasePage {

    public static final String PAGE_URL = "https://opencart.abstracta.us/index.php?route=account/register";

    @FindBy(xpath = "//div[@class='buttons']//child::input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement errorMessage;

    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='input-firstname']//following-sibling::div[@class='text-danger']")
    private WebElement firstNameInputError;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='input-lastname']//following-sibling::div[@class='text-danger']")
    private WebElement lastNameInputError;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-email']//following-sibling::div[@class='text-danger']")
    private WebElement emailInputError;

    @FindBy(id = "input-telephone")
    private WebElement telephoneInput;

    @FindBy(xpath = "//input[@id='input-telephone']//following-sibling::div[@class='text-danger']")
    private WebElement telephoneInputError;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='input-password']//following-sibling::div[@class='text-danger']")
    private WebElement passwordInputError;

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmInput;

    @FindBy(xpath = "//input[@id='input-confirm']//following-sibling::div[@class='text-danger']")
    private WebElement passwordConfirmInputError;

    @FindBy(xpath = "//div[@class='buttons']//child::input[@type='checkbox']")
    private WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//div[@id='content']//p[1]")
    private WebElement successMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickContinueButton() {
        click(getContinueButton());
    }

    public String getErrorMessageText() {
        return getErrorMessage().getText();
    }

    public void clickCheckboxPrivacyPolicy() {
        click(getPrivacyPolicyCheckbox());
    }

    public Boolean areAllErrorMessagesVisible() {
        if (!(getFirstNameInputError().isDisplayed() || getLastNameInputError().isDisplayed() ||
                getEmailInputError().isDisplayed() || getTelephoneInputError().isDisplayed() ||
                getPasswordInputError().isDisplayed()))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public void registerWithAllData(String firstName, String lastName, String email, String telephone, String password) {
        getFirstNameInput().sendKeys(firstName);
        getLastNameInput().sendKeys(lastName);
        getEmailInput().sendKeys(email);
        getTelephoneInput().sendKeys(telephone);
        getPasswordInput().sendKeys(password);
        getPasswordConfirmInput().sendKeys(password);
    }

    public String getSuccessMessageText() {
        return getSuccessMessage().getText();
    }
}
