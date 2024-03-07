package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class HomePage extends BasePage {

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

    public static Float priceWithoutTax = 0F;

    public static Float totalPrice = 0F;

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
        for (WebElement product : getProducts()) {
            String actualProductName = product.findElement(By.xpath(".//descendant::h4//child::a")).getText();

            if (expectedProductName.equals(actualProductName)) {
                exists = Boolean.TRUE;
                break;
            }
        }

        return exists;
    }

    public void addProductToCartByName(String productToAdd, Integer timesToAdd) {
        for (WebElement product : getProducts()) {
            String productName = getProductName(product);
            if (productToAdd.equals(productName)) {
                setPriceWithoutTax(getPriceWithoutTaxFloat(product));
                WebElement addToCartButton = getAddToCartButton(product);
                addProductToCart(addToCartButton, timesToAdd);
            }
        }
    }

    private String getProductName(WebElement product) {
        return product.findElement(By.xpath(".//descendant::h4//child::a")).getText();
    }

    private String[] getPrices(WebElement product) {
        String p = product.findElement(By.xpath(".//descendant::p[@class='price']")).getText();
        return product.findElement(By.xpath(".//descendant::p[@class='price']")).getText().replaceAll("\\$", "").split("\nEx Tax: ");

    }
    private Float getTotalPriceFloat(WebElement product) {
        return Float.parseFloat(getPrices(product)[0]);
    }

    private Float getPriceWithoutTaxFloat(WebElement product) {
        return Float.parseFloat(getPrices(product)[1]);
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

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (i == timesToAdd -1)
                clickBlackCartButton();
        }
    }

    public void setTotalPrice(Float price) {
        totalPrice += price;
    }

    public void setPriceWithoutTax(Float price) {
        priceWithoutTax += price;
    }

    public void addProductToCartByWebElement(WebElement productToAdd) {
        if (null != productToAdd) {
            click(productToAdd.findElement(By.xpath(".//descendant::div[@class='button-group']" +
                    "//child::button[contains(@onclick, 'cart.add')]")));

        }
    }

    public void clickBlackCartButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart")));
        wait.until(ExpectedConditions.visibilityOf(getBlackCartButton()));
        wait.until(ExpectedConditions.elementToBeClickable(getBlackCartButton()));
        click(getBlackCartButton());
    }

    public void addRandomProductsToCart(Integer numProducts) {
        Random rand = new Random();
        List<WebElement> copy = new ArrayList<WebElement>(getProducts());

        for (int i = 0; i < numProducts && !copy.isEmpty(); i++) {
            int randId = rand.nextInt(copy.size());
            WebElement product = copy.get(randId);
            addProductToCartByWebElement(product);
            copy.remove(product);
        }
    }
}
