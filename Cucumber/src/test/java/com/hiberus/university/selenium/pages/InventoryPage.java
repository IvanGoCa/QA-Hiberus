package com.hiberus.university.selenium.pages;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public class InventoryPage extends BasePage {

    public static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";

    public static Float totalPrize = 0F;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @FindBy(className = "product_sort_container")
    private WebElement selectFilter;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cart;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburguerButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    //CONSTRUCTOR
    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //ACTIONS
    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    public Integer sizeOfInventory(){
        return getInventoryItems().size();
    }

    public String getProductFromInventory(String productName) {
        String foundItem = null;
        for(WebElement item : getInventoryItems()) {
            String title = item.findElement(By.xpath(".//descendant::div[@class='inventory_item_name ']")).getText();
            if (title.equals(productName)) {
                foundItem = item.getText();
            }
        }

        return foundItem;
    }

    public void clickButtonAddItemToCart(String productName) {
        for(WebElement item : getInventoryItems()) {
            String title = item.findElement(By.xpath(".//descendant::div[@class='inventory_item_name ']")).getText();
            if (title.equals(productName)) {
                WebElement addToCartButton = item.findElement(By.xpath(".//child::button"));
                click(addToCartButton);
            }
        }
    }

    public void clickButtonRemoveItemFromCart(String productName) {
        for(WebElement item : getInventoryItems()) {
            String title = item.findElement(By.xpath(".//descendant::div[@class='inventory_item_name ']")).getText();
            if (title.equals(productName)) {
                WebElement removeFromCart = item.findElement(By.xpath(".//child::button"));
                click(removeFromCart);
            }
        }
    }

    public Integer getElementsInCart(){
        Integer items = null;
        try {
            items = Integer.parseInt(getShoppingCartBadge().getText());
        } catch (NoSuchElementException e) {
            System.out.println("NO SE HA ENCONTRADO EL ELEMENTO DEL CARRITO:\n" + e.getMessage());
        }

        return items;
    }

    public void addRandomItemsToCart(Integer numOfItems) {
        Random random = new Random();
        List<WebElement> inventoryItemsCopy = new ArrayList<>(getInventoryItems());
        for (int i = 0; i < numOfItems && !inventoryItemsCopy.isEmpty(); i++) {
            int randomIndex = random.nextInt(inventoryItemsCopy.size());
            WebElement randomItem = inventoryItemsCopy.get(randomIndex);
            WebElement button = randomItem.findElement(By.xpath(".//button[contains(@id, 'add-to-cart')]"));
            button.click();
            String prize = randomItem.findElement(By.xpath(".//div[@class='inventory_item_price' and contains(text(), '$')]")).getText().replaceAll("\\$", "");
            totalPrize += Float.parseFloat(prize);
            inventoryItemsCopy.remove(randomIndex);
        }
    }

    public Boolean isOrderedByZA() {
        List<String> expectedTitles = new ArrayList<String>();
        List<String> actualTitles = new ArrayList<String>();
        for(WebElement item : getInventoryItems()) {
            String title = item.findElement(By.xpath(".//descendant::div[@class='inventory_item_name ']")).getText();
            actualTitles.add(title);
            expectedTitles.add(title);
        }

        expectedTitles.sort(Collections.reverseOrder());

        if (expectedTitles.equals(actualTitles))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean isOrderedByLoHi() {
        List<Float> expectedPrizes = new ArrayList<Float>();
        List<Float> actualPrizes = new ArrayList<Float>();

        for(WebElement item : getInventoryItems()) {
            Float prize = Float.parseFloat(item.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText().replaceAll("\\$", ""));
            expectedPrizes.add(prize);
            actualPrizes.add(prize);
        }

        Collections.sort(expectedPrizes);

        if (expectedPrizes.equals(actualPrizes))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean isOrderedByHiLo() {
        List<Float> expectedPrizes = new ArrayList<Float>();
        List<Float> actualPrizes = new ArrayList<Float>();

        for(WebElement item : getInventoryItems()) {
            Float prize = Float.parseFloat(item.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText().replaceAll("\\$", ""));
            expectedPrizes.add(prize);
            actualPrizes.add(prize);
        }

        expectedPrizes.sort(Collections.reverseOrder());

        if (expectedPrizes.equals(actualPrizes))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void clickSelect() {
        click(getSelectFilter());
    }

    public void clickOptionZA() {
        click(getSelectFilter().findElement(By.xpath(".//child::option[@value='za']")));
    }

    public void clickOptionLoHi() {
        click(getSelectFilter().findElement(By.xpath(".//child::option[@value='lohi']")));
    }

    public void clickOptionHiLo() {
        click(getSelectFilter().findElement(By.xpath(".//child::option[@value='hilo']")));
    }

    public void clickCart() {
        click(getCart());
    }

    public void clickHamburguer() {
        click(getHamburguerButton());
    }
    public void clickLogout() {
        click(getLogoutButton());
    }

    public void doLogout() {
        clickHamburguer();
        clickLogout();
    }
}
