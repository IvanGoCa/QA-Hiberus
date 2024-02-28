package com.hiberus.university.selenium.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
@Setter
public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement inputFirstName;

    @FindBy(id = "last-name")
    private WebElement inputLastName;

    @FindBy(id = "postal-code")
    private WebElement inputZipCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(xpath = "//div[contains(text(), 'Item total: $')]")
    private WebElement itemTotal;

    @FindBy(xpath = "//button[contains(text(), 'Finish')]")
    private WebElement finishButton;

    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement finalMessage;

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

    public Float getExpectedPrize() {
        return InventoryPage.totalPrize;
    }

    public Float getActualPrize() {
        String prize = getItemTotal().getText().replaceAll("Item total: \\$", "");
        return Float.parseFloat(prize);
    }

    public void clickFinish() {
        click(getFinishButton());
    }

    public String getActualMessage() {
        return getFinalMessage().getText();
    }
}
