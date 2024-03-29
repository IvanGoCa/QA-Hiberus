package com.hiberus.university.selenium.pages;

import org.openqa.selenium.WebDriver;

public class PagesFactory {
  private static PagesFactory pagesFactories;
  private final WebDriver driver;
  private final LoginPage loginPage;
  private final InventoryPage inventoryPage;
  private final CartPage cartPage;
  private final CheckoutPage checkoutPage;

  private PagesFactory(WebDriver driver) {
    this.driver = driver;
    loginPage = new LoginPage(driver);
    inventoryPage = new InventoryPage(driver);
    cartPage = new CartPage(driver);
    checkoutPage = new CheckoutPage(driver);
  }

  public static void start(WebDriver driver) {
    pagesFactories = new PagesFactory(driver);
  }

  public static PagesFactory getInstance() {
    return pagesFactories;
  }

  public WebDriver getDriver() {
    return driver;
  }

  public LoginPage getLoginPage() {
    return this.loginPage;
  }

  public InventoryPage getInventoryPage() {
    return this.inventoryPage;
  }

  public CartPage getCartPage() { return cartPage; }

  public CheckoutPage getCheckoutPage() { return checkoutPage; }
}
