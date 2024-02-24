package com.streetlink.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.streetlink.base.BaseClass;

public class ReadConfig{	
	Properties properties = new Properties();
	public ReadConfig(){
		
	try {
	    //load a properties file from class path, inside static method
		 InputStream inputStream = ReadConfig.class.getClassLoader().getResourceAsStream("config.properties");
		 properties.load(inputStream);
	} catch (IOException e) {
	e.printStackTrace();	
	 }
  }
	
	public String getPublicURL(){
		String readEnv = properties.getProperty("env");
		if(readEnv.equals("dev")){
			String url = properties.getProperty("public_dev_url");
			return url;
		}
		else if(readEnv.equals("stage")){
			String url = properties.getProperty("public_stage_url");
			return url;
		}
		return null;
	}
	
	public String getAdminURL(){
		String readEnv = properties.getProperty("env");
		if(readEnv.equals("dev")){
			String url = properties.getProperty("admin_dev_url");
			return url;
		}
		else if(readEnv.equals("stage")){
			String url = properties.getProperty("admin_stage_url");
			return url;
		}
		return null;
	}
	
	public String getBrowser() {
		String browserName = properties.getProperty("browser");
		System.out.println("BrowserName: "+ browserName);
		return browserName;
	}
	
	public String getEnv() {
		String env = properties.getProperty("env");
		System.out.println("Environment: "+ env);
		return env;
	}
	
	
	
	
	
	
	
	
}
	

