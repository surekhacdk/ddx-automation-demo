package com.cdk.ddx.support.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class UIObjectMap {
    
    Properties uiProperties;
    
    public UIObjectMap(String filePath) {
        
        uiProperties = new Properties();
        
        try{            
            FileInputStream fileInputStream = new FileInputStream(filePath);
            uiProperties.load(fileInputStream);
            fileInputStream.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public By getLocator(String uiObjectKey) throws Exception {
        
        String locator = uiProperties.getProperty(uiObjectKey);
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
        
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.tagName(locatorValue);
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Unknown locator type '" + locatorType + "'");
    }
}
