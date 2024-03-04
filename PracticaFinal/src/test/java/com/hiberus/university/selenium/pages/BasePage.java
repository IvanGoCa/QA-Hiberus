package com.hiberus.university.selenium.pages;

import com.hiberus.university.selenium.utils.MyFluentWait;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

@Slf4j
public class BasePage {

  protected Wait<WebDriver> wait;
  private final WebDriver driver;

  BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new MyFluentWait<>(driver)
      .withTimeout(10, ChronoUnit.SECONDS)
      .pollingEvery(2, ChronoUnit.SECONDS)
      .ignoring(NoSuchElementException.class);
  }

  protected WebDriver getDriver() {
    return driver;
  }

  public void navigateTo(String url) {
    WebDriver driver = getDriver();

    try {
      driver.navigate().to(url);
    } catch (java.lang.Exception e) {
      if (e instanceof TimeoutException) {
        log.info("Timeout loading home page");
      } else if (e instanceof ScriptTimeoutException) {
        log.info("Script Timeout loading home page");
      } else {
        log.error(e.getMessage());
      }
    }
  }
}