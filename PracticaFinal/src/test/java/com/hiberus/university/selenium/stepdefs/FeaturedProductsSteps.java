package com.hiberus.university.selenium.stepdefs;

import com.hiberus.university.selenium.pages.FeaturedProducts;
import com.hiberus.university.selenium.pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FeaturedProductsSteps {

    private FeaturedProducts featuredProducts;

    @When("anado el producto {string} al carrito")
    public void anado_el_producto_al_carrito(String productNameToClick) {
        init();
        featuredProducts.addProductToCartByName(productNameToClick);
    }

    @When("hago click en el boton de carrito negro")
    public void hago_click_en_el_boton_de_carrito_negro() {
        init();
        featuredProducts.clickBlackCartButton();
    }

    @When("anado {string} productos al carrito")
    public void anado_productos_al_carrito(String products) {
        init();
        featuredProducts.addRandomProductsToCart(Integer.parseInt(products));
    }

    @Then("en el apartado featured hay {string} productos")
    public void en_el_apartado_featured_hay_productos(String numString) {
        init();
        Integer expectedNum = Integer.parseInt(numString);
        Integer actualNum = featuredProducts.getProductCount();

        Assert.assertEquals("El numero de productos en el apartado 'Featured' no corresponde con el esperado", expectedNum, actualNum);
    }

    @Then("compruebo que el producto {string} existe")
    public void compruebo_que_el_producto_existe(String expectedProductName) {
        init();
        Boolean productExists = featuredProducts.findProductByName(expectedProductName);

        Assert.assertTrue("El producto '"+ expectedProductName +"' no existe", productExists);
    }

    @Then("compruebo que en el carrito hay {string} elementos")
    public void compruebo_que_en_el_carrito_hay_elemento(String productCount) {
        init();
        Integer expectedProductCount = Integer.parseInt(productCount);
        Integer actualProductCount = featuredProducts.getProductCountFromBlackCart();

        Assert.assertEquals("Los productos esperados en el carrito no coinciden con los actuales", expectedProductCount, actualProductCount);
    }

    private void init(){
        PagesFactory pf = PagesFactory.getInstance();
        featuredProducts = pf.getFeaturedProducts();
    }
}
