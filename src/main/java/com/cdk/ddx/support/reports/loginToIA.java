package com.cdk.ddx.support.reports;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cdk.ddx.support.utils.UIObjectMap;

public class loginToIA {

    private String iaUrl;
    private String iaUsername;
    private String iaPassword;
    private UIObjectMap uiObjectMap;
    private  WebDriver driver;
    
    @Before
    public void setUp() throws Exception {
        
        uiObjectMap = new UIObjectMap("../resources/objectrepository/infoIQLoginPageObjectRepo.properties");
        Properties envConfig = new Properties();
        
        try{            
            FileInputStream fileInputStream = new FileInputStream("../resources/config/environmentConfig.properties");
            envConfig.load(fileInputStream);
            fileInputStream.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
        iaUrl = envConfig.getProperty("url");
        iaUsername = envConfig.getProperty("username");
        iaPassword = envConfig.getProperty("password");
        
        WebDriver driver = new ChromeDriver();
        driver.get(iaUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void testValidLoginToIA() {
        
        WebElement element;
       
        try {
            element = driver.findElement(uiObjectMap.getLocator("username"));
            element.sendKeys(iaUsername);
            
            element = driver.findElement(uiObjectMap.getLocator("password"));
            element.sendKeys(iaPassword);
            
            element = driver.findElement(uiObjectMap.getLocator("login"));
            element.click();
            
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    

    @After
    public void tearDown() throws Exception {
        
        driver.quit();
        
    }

}
