package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public FeaturedProducts(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Integer getProductCount() {
        return getProducts().size();
    }

    public Boolean findProductByName(String expectedProductName) {
        boolean exists = Boolean.FALSE;
        for(WebElement product : products) {
           String actualProductName = product.findElement(By.xpath(".//descendant::h4//child::a")).getText();

           if (expectedProductName.equals(actualProductName))
               exists = Boolean.TRUE;
        }

        return exists;
    }

    public void addProductToCartByName(String productToAdd) {
        for (WebElement product : products) {
            String productName = product.findElement(By.xpath(".//descendant::h4//child::a")).getText();

            if (productToAdd.equals(productName)) {
                WebElement addToCartButton = product.findElement(By.xpath(".//descendant::div[@class='button-group']" +
                        "//child::button[contains(@onclick, 'cart.add')]"));
                click(addToCartButton);
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
}
