package com.ninjaproj.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninjaproj.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base() {
		 prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\ninjaproj\\qa\\config\\Config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\ninjaproj\\qa\\testdata\\testdata.properties"); 
		
		try {
		FileInputStream Datafis = new FileInputStream(dataPropFile);
		dataProp.load(Datafis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e){
			e.printStackTrace();
			
		}
	}
	
	public WebDriver initializeBrowserAndAppURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver =new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		} 
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}

}
