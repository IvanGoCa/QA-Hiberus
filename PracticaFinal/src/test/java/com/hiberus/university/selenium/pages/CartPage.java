package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

@Getter
public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr")
    private List<WebElement> productsFromBlackCart;

    @FindBy(xpath = "//div[@id='cart']//child::ul//descendant::table[@class='table table-bordered']//descendant::strong[contains(text(), 'Sub-Total')]//parent::td//following-sibling::td")
    private WebElement subTotal;

    CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Integer getProductCountFromBlackCart() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='dropdown-menu pull-right']//table")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr")));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr"), 0));

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

    public void deleteOneProductFromBlackCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu pull-right']")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='dropdown-menu pull-right']//table")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr")));
        wait.until(ExpectedConditions.visibilityOfAllElements(getProductsFromBlackCart()));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='cart']//child::ul//descendant::table[@class='table table-striped']//descendant::tr//child::td[@class='text-center']//child::button[contains(@onclick, 'cart.remove')]")));

        Random rand = new Random();
        List<WebElement> products = getProductsFromBlackCart();
        int productCount = getProductCountFromBlackCart();

        if (productCount > 0) {
            WebElement product = products.get(rand.nextInt(productCount));
            WebElement deleteButton = product.findElement(By.xpath(".//td//button"));

            wait.until(ExpectedConditions.visibilityOf(deleteButton));
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            deleteButton.click();

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay productos en el carrito para eliminar.");
        }
    }
}
