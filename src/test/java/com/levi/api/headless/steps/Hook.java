package com.levi.api.headless.steps;

import java.io.File;

import com.cucumber.listener.Reporter;
import cucumber.api.java.After;


public class Hook {
	
	
	@After
	public static void writeExtentReport() {
		 Reporter.loadXMLConfig(new File(System.getProperty("extentConfig")));
		 }
}