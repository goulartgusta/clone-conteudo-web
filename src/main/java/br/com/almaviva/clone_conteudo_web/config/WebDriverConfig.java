package br.com.almaviva.clone_conteudo_web.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {

    private WebDriver driver;

    public WebDriverConfig() {
        System.setProperty("webdriver.chrome.driver", "/home/almaviva-linux/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }
}