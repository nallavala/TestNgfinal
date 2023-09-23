package com.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
	public static WebDriver driver;
	
	static Properties prop;
	String path = System.getProperty("user.dir")+"\\data.properties";
	FileInputStream file = null;
	public void launchurl() {
		
		
		try {
			
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//driver = new ChromeDriver();
		driver = new EdgeDriver();
		String url = prop.getProperty("url");
		driver.get(url);	
	}
	public String screenShot(String testcasename) {
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss").format(new Date());
		String des = System.getProperty("user.dir")+"\\screenshots\\"+dateName+"output.jpg";
		try {
			FileUtils.copyFile(scr, new File(des));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return des;
	}
	public static ExtentReports extentreport() {
		String dateName = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss").format(new Date());
		String file = System.getProperty("user.dir")+"\\ExtentReports\\"+dateName+".html";
		ExtentSparkReporter ext = new ExtentSparkReporter(file);
		ext.config().setReportName("Reporting");
		ExtentReports repo = new ExtentReports();
		repo.attachReporter(ext);
		repo.setSystemInfo("chrome", "version3.0");
		return repo;
	}

}
