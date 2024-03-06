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
public class FeaturedProducts extends BasePage {

    @FindBy(xpath = "//div[@class='row']//child::div[contains(@class, 'product-layout')]")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@id='cart']//child::button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
    private WebElement blackCartButton;

    @FindBy(xpath = "//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr")
    private List<WebElement> productsFromBlackCart;

    @FindBy(id = "cart-total")
    private WebElement cartText;

    public static Float prizeWithTax = 0F;

    public static Float prizeWithoutTax = 0F;

    public FeaturedProducts(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public void addProductToCartByName(String productToAdd) {
        String previousCartText = getCartText().getText();

        for (WebElement product : getProducts()) {
            String productName = product.findElement(By.xpath(".//descendant::h4//child::a")).getText();

            if (productToAdd.equals(productName)) {
                WebElement addToCartButton = product.findElement(By.xpath(".//descendant::div[@class='button-group']" +
                        "//child::button[contains(@onclick, 'cart.add')]"));

                String prizes [] = product.findElement(By.xpath(".//descendant::p[@class='price']")).getText().replaceAll("\\$", "").split("\nEx Tax: ");
                Float prizeNoTax = Float.parseFloat(prizes[1]);
                prizeWithoutTax += prizeNoTax;
                System.out.println("prizeWithoutTax - - " + prizeWithoutTax);

                addToCartButton.click();

                wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.id("cart-total"), previousCartText)));

                String newCartText = getCartText().getText();
                if (!newCartText.equals(previousCartText)) {
                    System.out.println("El producto se ha añadido correctamente al carrito.");
                } else {
                    System.out.println("Error: El producto no se añadió correctamente al carrito.");
                }
            }
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

    public Integer getProductCountFromBlackCart() {
        Integer productCount = 0;
        if (getProductsFromBlackCart().isEmpty())
            return productCount;
        else {
            return getProductsFromBlackCart().size();
        }
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

    public Integer getAmmountAtCartOf(String productName) {
        Integer amount = 0;

        if (!getProductsFromBlackCart().isEmpty()) {
            for (WebElement product : getProductsFromBlackCart()) {
                WebElement link = product.findElement(By.xpath(".//td[@class='text-left']/a"));
                String name = link.getText();

                if (name.equals(productName)) {
                    WebElement quantityCell = product.findElement(By.xpath(".//td[@class='text-right' and contains(text(), 'x')]"));
                    amount = Integer.parseInt(quantityCell.getText().replaceAll("x ", ""));
                    break;
                }
            }
        }

        return amount;
    }
}
