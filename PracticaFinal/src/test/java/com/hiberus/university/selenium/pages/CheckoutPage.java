package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Getter
public class CheckoutPage extends BasePage {

    @FindBy(id = "input-email")
    private WebElement emailInputLogin;

    @FindBy(id = "input-password")
    private WebElement passwordInputLogin;

    @FindBy(id = "button-login")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@type='radio' and @value='new']")
    private WebElement radioButtonNewAddress;

    @FindBy(id = "button-payment-address")
    private WebElement buttonContinueBillingDetailLogged;

    @FindBy(id = "button-guest")
    private WebElement buttonContinueBillingDetailGuest;

    //Address inputs
    @FindBy(id = "input-payment-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-payment-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-payment-email")
    private WebElement emailInput;

    @FindBy(id = "input-payment-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-payment-company")
    private WebElement companyInput;

    @FindBy(id = "input-payment-address-1")
    private WebElement address1Input;

    @FindBy(id = "input-payment-address-2")
    private WebElement address2Input;

    @FindBy(id = "input-payment-city")
    private WebElement cityInput;

    @FindBy(id = "input-payment-postcode")
    private WebElement postCodeInput;

    @FindBy(id = "input-payment-country")
    private WebElement coutrySelect;

    @FindBy(xpath = "//select[@id='input-payment-country']//child::option")
    private List<WebElement> countryOptions;

    @FindBy(id = "input-payment-zone")
    private WebElement regionSelect;

    @FindBy(xpath = "//select[@id='input-payment-zone']//child::option")
    private List<WebElement> regionOptions;

    //------- End Inputs

    @FindBy(xpath = "//input[@type='checkbox' and @name='agree']")
    private WebElement termsCheckbox;

    @FindBy(id = "button-payment-method")
    private WebElement continuePaymentMethodButton;

    @FindBy(id = "button-shipping-address")
    private WebElement continueDeliveryDetailsButton;

    @FindBy(id = "button-shipping-method")
    private WebElement continueDeliveryMethodButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-hover']")
    private WebElement table;

    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//descendant::strong[text()='Sub-Total:']//parent::td//following-sibling::td")
    private WebElement subTotalPrice;

    @FindBy(xpath = "//input[@type='radio' and @value='guest']")
    private WebElement radioButtonAsGuest;

    @FindBy(id = "button-account")
    private WebElement continuneButtonAccount;

    @FindBy(id = "button-confirm")
    private WebElement confirmOrderButton;

    @FindBy(xpath = "//div[@id='common-success']//child::div[@class='row']//child::div[@id='content']//h1")
    private WebElement confirmOrderMessage;


    //Constructor
    CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String email, String password) {
        getEmailInputLogin().sendKeys(email);
        getPasswordInputLogin().sendKeys(password);
        click(getButtonLogin());
    }

    public void clickContinuePaymentAddressLogged() {
        click(getButtonContinueBillingDetailLogged());
    }

    public void clickContinuePaymentAddressGuest() {
        click(getButtonContinueBillingDetailGuest());
    }

    public void writeBillingData(String firstName, String lastName, String email, String telephone, String company,
                                 String address1, String address2, String city, String postCode, String country, String region, Boolean radioButton) {

        if (radioButton)
            click(getRadioButtonNewAddress());

        getFirstNameInput().sendKeys(firstName);
        getLastNameInput().sendKeys(lastName);

        if (!email.equals("null"))
            getEmailInput().sendKeys(email);

        if (!telephone.equals("null"))
            getTelephoneInput().sendKeys(telephone);

        getCompanyInput().sendKeys(company);
        getAddress1Input().sendKeys(address1);

        if(!address2.equals("null"))
            getAddress2Input().sendKeys(address2);

        getCityInput().sendKeys(city);
        getPostCodeInput().sendKeys(postCode);
        click(getCoutrySelect());
        for (WebElement countryOption : getCountryOptions()) {
            if (countryOption.getText().equals(country)) {
                click(countryOption);
                break;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(getRegionSelect()));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='input-payment-zone']//child::option"), 10));
        click(getRegionSelect());

        for (WebElement regionOption : getRegionOptions()) {
            if (regionOption.getText().equals(region)) {
                click(regionOption);
                break;
            }
        }

        if (radioButton)
            clickContinuePaymentAddressLogged();
        else
            clickContinuePaymentAddressGuest();
    }

    public void acceptPayment() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.elementToBeClickable(getTermsCheckbox()),
                ExpectedConditions.visibilityOf(getTermsCheckbox())
        ));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.elementToBeClickable(getContinuePaymentMethodButton()),
                ExpectedConditions.visibilityOf(getContinuePaymentMethodButton())
        ));

        click(getTermsCheckbox());
        click(getContinuePaymentMethodButton());
        System.out.println("");
    }

    public void clickContinueDeliveryDetails() {
        click(getContinueDeliveryDetailsButton());
    }

    public void clickContinueDeliveryMethod() {
        click(getContinueDeliveryMethodButton());
    }

    public Float getSubtotalPriceFloat() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']")));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//table[@class='table table-bordered table-hover']//child::tfoot//child::tr"), 1));
        wait.until(ExpectedConditions.visibilityOf(getSubTotalPrice()));

        return Float.parseFloat(getSubTotalPrice().getText().replaceAll("\\$", ""));
    }

    public void clickDoCkeckoutAsGuest() {
        wait.until(ExpectedConditions.visibilityOf(getRadioButtonAsGuest()));
        click(getRadioButtonAsGuest());
        click(getContinuneButtonAccount());
    }

    public void clickConfirmOrder() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(getConfirmOrderButton()),
                ExpectedConditions.elementToBeClickable(getConfirmOrderButton())
        ));

        click(getConfirmOrderButton());
    }

    public String getConfirmOrderMessageText() {
        wait.until(ExpectedConditions.visibilityOf(getConfirmOrderMessage()));
        return getConfirmOrderMessage().getText();
    }
}
