package com.wuzzuf.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // 👇 Add AdBlocker extension (update the path if needed)
            String extensionPath = "C:\\Program Files\\Google\\Chrome\\Application\\137.0.7151.104\\Extensions\\<extension-folder-id>";
            File adblockExtension = new File(extensionPath);
            if (adblockExtension.exists()) {
                options.addArguments("--load-extension=" + adblockExtension.getAbsolutePath());
            }

            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
