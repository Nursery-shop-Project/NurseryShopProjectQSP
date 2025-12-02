package com.nurseryshopapp.genericlibrary;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLibrary {

	public FileInputStream fis;
	public FileOutputStream fos;
	public Properties propertyobj;
	
	
	
	public String readData(String key) {

		try {
			fis=new FileInputStream("./src/test/resources/Data/DWSappdata.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 propertyobj = new Properties(); 
		try {
			propertyobj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return propertyobj.getProperty(key);
		
	}
}
