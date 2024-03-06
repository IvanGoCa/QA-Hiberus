package com.hiberus.university.selenium.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.crypto.ExemptionMechanism;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class HomePage extends BasePage{

    public static final String PAGE_URL = "https://opencart.abstracta.us/index.php?route=common/home";

    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement MyAccountButton;

    @FindBy(xpath = "//a[@title='My Account']//following-sibling::ul//child::li//child::a[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@title='My Account']//following-sibling::ul//child::li//child::a[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@title='Checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@class='row']//child::div[contains(@class, 'product-layout')]")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@id='cart']//child::button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
    private WebElement blackCartButton;

    @FindBy(id = "cart-total")
    private WebElement cartText;

    public static Float prizeWithTax = 0F;

    public static Float prizeWithoutTax = 0F;

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

    public void goToCheckoutPage() {
        click(getCheckoutButton());
    }

    public Integer getProductCount() {
        return getProducts().size();
    }

    public Boolean findProductByName(String expectedProductName) {
        boolean exists = Boolean.FALSE;
        for(WebElement product : getProducts()) {
            String actualProductName = product.findElement(By.xpath(".//descendant::h4//child::a")).getText();

            if (expectedProductName.equals(actualProductName))
                exists = Boolean.TRUE;
        }

        return exists;
    }

    public void addProductToCartByName(String productToAdd, Integer timesToAdd) {
        for (WebElement product : getProducts()) {
            String productName = getProductName(product);
            if (productToAdd.equals(productName)) {
                WebElement addToCartButton = getAddToCartButton(product);
                addProductToCart(addToCartButton, timesToAdd);
            }
        }
    }

    private String getProductName(WebElement product) {
        return product.findElement(By.xpath(".//descendant::h4//child::a")).getText();
    }

    private WebElement getAddToCartButton(WebElement product) {
        return product.findElement(By.xpath(".//descendant::div[@class='button-group']" +
                "//child::button[contains(@onclick, 'cart.add')]"));
    }

    private void addProductToCart(WebElement addToCartButton, int timesToAdd) {
        for (int i = 0; i < timesToAdd; i++) {
            String previousCartText = getCartText().getText();

            addToCartButton.click();

            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfElementLocated(By.id("cart-total")),
                    ExpectedConditions.not(ExpectedConditions.textToBe(By.id("cart-total"), previousCartText))
            ));
        }
    }

    public void addProductToCartByWebElement(WebElement productToAdd) {
        if (null != productToAdd) {
            click(productToAdd.findElement(By.xpath(".//descendant::div[@class='button-group']" +
                    "//child::button[contains(@onclick, 'cart.add')]")));

        }
    }

    public void clickBlackCartButton(){
        click(getBlackCartButton());
    }

    public void addRandomProductsToCart(Integer numProducts) {
        Random rand = new Random();
        List<WebElement> copy = new ArrayList<WebElement>(getProducts());

        for(int i = 0; i < numProducts && !copy.isEmpty(); i++) {
            int randId = rand.nextInt(copy.size());
            WebElement product = copy.get(randId);
            addProductToCartByWebElement(product);
            copy.remove(product);
        }
    }
}
