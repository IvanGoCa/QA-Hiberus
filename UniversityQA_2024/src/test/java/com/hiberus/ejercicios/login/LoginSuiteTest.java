package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginSuiteTest {

    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver= new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }

    @Test
    public void loginTest() {
        /* Abrir una página web */
        driver.get("https://www.marca.com");

        /* Recoger el título de la página web */
        String title = driver.getTitle();
        System.out.println("-- getTitle --");
        System.out.println(title);

        /* Recoger la URL de la página */
        String url = driver.getCurrentUrl();
        System.out.println("-- getCurrentUrl");
        System.out.println(url);

        /* Recoge el HTML de la página web */
        String pageSource = driver.getPageSource();
        System.out.println("-- getPageSource");
        System.out.println(pageSource);

    }

    @Test
    public void loginIncorrectTest() {


    }

    @After
    public void tearDown() {
        driver.close();
    }

}
