package com.hiberus.ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Ejercicio22 {

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
    public void segundoEjercicio() {
        /* 2. Abra el sitio web “https://www.hiberus.com/”. */
        final String url = "https://www.hiberus.com/";
        driver.get(url);

        /* 3. Haga click en el enlace de Consultoría y Estrategia usando “driver.findElement(By.xpath(“//a[@href=’/consultoría-y-estrategia-denegocio’]”)).click();” */
        // Acepto Cookies
        driver.findElement(By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();

        // Hago click en el enlace de "ENTERPRISE EFFICIENCY"
        driver.findElement(By.xpath("//a[@href='/tecnologias-diferenciales']")).click();

        /* 4. Vuelva a la página de inicio (utilice el comando 'Back') */
        driver.navigate().back();

        /* 5. Vuelva nuevamente a la página de Consultoría y Estrategia (esta vez use el comando 'Forward') */
        driver.navigate().forward();

        /* 6. Vuelva nuevamente a la página de inicio (esta vez use el comando 'To') */
        driver.navigate().to(url);

        /* 7. Actualizar el navegador (Use el comando 'Refresh') */
        driver.navigate().refresh();

        /* 8. Cierra el navegador */
        driver.close();
    }

    @After
    public void tearDown() {
        //driver.close();
    }
}
