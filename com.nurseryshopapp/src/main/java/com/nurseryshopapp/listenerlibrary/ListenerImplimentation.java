package com.nurseryshopapp.listenerlibrary;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import org.dwsapp.genericlibrary.BaseConfig;

public class ListenerImplimentation implements ITestListener {


	  @Override
	public void onTestFailure(ITestResult result) {
		
	
		String Result = result.getName();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String customtime = sdf.format(new Date());
		
		
		
		//1.typcasting
		TakesScreenshot stobj = (TakesScreenshot)BaseConfig.stdriver;
		
		//2. take sc in temp path
		File temp = stobj.getScreenshotAs(OutputType.FILE);
		
		
		//3. create permanent path
		File permanent = new File("src/test/resources/testscreenshots"+Result+ customtime+".png");
		
		//4. copy temp to permanent
		try {
			FileHandler.copy(temp, permanent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Reporter.log("screenshot taken succesfully",true);
		
		
	}

}
