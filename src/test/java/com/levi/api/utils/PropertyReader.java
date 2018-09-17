package com.levi.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	 Properties properties;

	    public PropertyReader(String filePath) {

	        try {
	            FileInputStream inputStream = new FileInputStream(filePath);
	            properties = new Properties();
	            properties.load(inputStream);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }

	    public String getData(String ElementName) throws Exception {
	        // Read value using the logical name as Key
	        String data = properties.getProperty(ElementName);
	        return data;
	    }
	
	
	
	
}


