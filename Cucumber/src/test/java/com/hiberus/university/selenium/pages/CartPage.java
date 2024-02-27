package com.hiberus.university.selenium.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='cart_list']//child::div[@class='cart_item']")
    List<WebElement> items;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    List<WebElement> itemsCopy;

    WebElement removedItem;

    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void deleteOneProduct() {
        this.itemsCopy = new ArrayList<>(getItems());

        Random random = new Random();
        int randomIndex = random.nextInt(itemsCopy.size());

        removedItem = itemsCopy.get(randomIndex);

        WebElement removeButton = itemsCopy.get(randomIndex).findElement(By.xpath(".//child::button[contains(text(), Remove)]"));
        click(removeButton);
    }

    public Boolean checkIfProductIsDeleted() {
        Boolean removed = Boolean.TRUE;
        for (WebElement item : getItems()) {
            if (item.equals(removedItem)) {
                removed = Boolean.FALSE;
                break;
            }
        }

        return removed;
    }

    public void clickCheckout() {
        click(getCheckoutButton());
    }
}
