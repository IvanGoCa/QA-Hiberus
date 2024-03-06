package com.hiberus.university.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class PagesFactory {
  private static PagesFactory pagesFactories;
  private final WebDriver driver;
  private final HomePage homePage;
  private final FeaturedProducts featuredProducts;
  private final RegisterPage registerPage;
  private final LoginPage loginPage;
  private final MyAccountPage myAccountPage;
  private final CheckoutPage checkoutPage;

  private PagesFactory(WebDriver driver) {
    this.driver = driver;
    this.homePage = new HomePage(driver);
    this.featuredProducts = new FeaturedProducts(driver);
    this.registerPage = new RegisterPage(driver);
    this.loginPage = new LoginPage(driver);
    this.myAccountPage = new MyAccountPage(driver);
    this.checkoutPage = new CheckoutPage(driver);
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
}
