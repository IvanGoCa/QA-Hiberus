package com.hiberus.ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Ejercicio21 {
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
    public void primerEjercicio() {
        /* 2. Abrir la página https://www.saucedemo.com/ */
        driver.get("https://www.saucedemo.com");

        /* 3. Obtenga el nombre del titulo de la pagina y la longitud del título. */
        /* 4. Imprima el título de la página y la longitud del título en la consola de IntellIJ. */
        String title = driver.getTitle();
        System.out.println("-- TITULO --");
        System.out.println(title);

        Integer titleLength = title.length();
        System.out.println("-- LONGITUD DEL TITULO --");
        System.out.println(titleLength);

        /* 5. Obtenga la URL de la página y verifique si es una página correcta. */
        String url = driver.getCurrentUrl();
        System.out.println("-- URL --");
        System.out.println(url);

        /* 6. Obtenga la fuente de la página (código fuente HTML) y la longitud de la fuente de la página */
        String source = driver.getPageSource();
        System.out.println("-- CODIGO DE LA PAGINA --");
        System.out.println(source);

        Integer sourceLength = source.length();
        System.out.println("-- LONGITUD DEL CODIGO --");
        System.out.println(sourceLength);


    }

    @After
    public void tearDown() {
        driver.close();
    }
}
