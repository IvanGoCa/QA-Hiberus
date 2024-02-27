package com.hiberus.university.selenium.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
@Setter
public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    WebElement inputFirstName;

    @FindBy(id = "last-name")
    WebElement inputLastName;

    @FindBy(id = "postal-code")
    WebElement inputZipCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    //Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void writeFirstName(String firstName) {
        sendKeys(getInputFirstName(), firstName);
    }

    public void writeLastName(String lastName) {
        sendKeys(getInputLastName(), lastName);
    }

    public void writeZipCode(String zipCode) {
        sendKeys(getInputZipCode(), zipCode);
    }

    public void clickContinue() {
        click(getContinueButton());
    }

    public void doCheckout(String firstName, String lastName, String zipCode) {
        writeFirstName(firstName);
        writeLastName(lastName);
        writeZipCode(zipCode);
        clickContinue();
    }
}
