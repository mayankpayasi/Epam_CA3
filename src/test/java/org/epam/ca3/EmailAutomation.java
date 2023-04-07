package org.epam.ca3;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class EmailAutomation {
    WebDriver driver;
    public Properties prop;

    @BeforeTest
    public void setUp() throws IOException {
        // Loading config.properties file
        prop = new Properties();
        FileInputStream fis = new FileInputStream("E:/Maven/CA3_Email_Automation/src/test/java/org/epam/ca3/config.properties");
        prop.load(fis);
    }

    @BeforeClass
    @Parameters({"browser","url"})
    void setupDriver (String browser, String link) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
        driver.get("https://mail.google.com");
        driver.manage().window().maximize();
    }
    @Test
    void getTitleFromPage() throws InterruptedException {

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        Thread.sleep(4000);

        //Username
        WebElement  emailInput = driver.findElement(By.xpath("//input[@type='email' or @aria-label='Email or phone']\n"));
        emailInput.sendKeys(username);
        Thread.sleep(4000);

        //Next
        driver.findElement(By.xpath("//div[@id='identifierNext']//button\n")).click();
        Thread.sleep(4000);

        //Password
        WebElement  passwordInput =driver.findElement(By.xpath("//input[@type='password']\n"));
        passwordInput.sendKeys(password);
        Thread.sleep(4000);

        //Next
        driver.findElement(By.xpath("//div[@id='passwordNext']//button\n")).click();
        Thread.sleep(9000);

    }

    @AfterClass
    void tearDown () {
        driver.quit();
    }

}
