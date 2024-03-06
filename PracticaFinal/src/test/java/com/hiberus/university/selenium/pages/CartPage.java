package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr")
    private List<WebElement> productsFromBlackCart;

    CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Integer getProductCountFromBlackCart() {
        Integer productCount = 0;
        if (getProductsFromBlackCart().isEmpty())
            return productCount;
        else {
            return getProductsFromBlackCart().size();
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
