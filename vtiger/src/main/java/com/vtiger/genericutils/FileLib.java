package com.vtiger.genericutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Karthik M
 * 
 * This class contains methods to load, read and write data from Properties file
 *
 */

public class FileLib {
	
	
	private Properties prop;
	private FileInputStream fis;
	
	/**
	 * Method to get the property
	 * @param key
	 * @return value
	 * @throws Throwable 
	 * @throws Exception
	 */
	
	//Method to get the property
	public String getProperty(String key) throws Throwable
	{
		prop = new Properties();
		fis = new FileInputStream("./testData/commonData.properties");
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}

}
