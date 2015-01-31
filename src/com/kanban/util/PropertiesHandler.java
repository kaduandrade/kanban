package com.kanban.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesHandler {
	
	private static HashMap<String, Properties> propertiesList = new HashMap<String, Properties>();
	
	private static String APP_PROPERTIES = "app.properties";
	
	public static Properties getProperties(String name) throws Exception {
		
		Properties prop = (Properties) propertiesList.get(name);
		
		if (prop == null) {
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = loader.getResourceAsStream(name);
			prop = new Properties();
			
			try {
				
				prop.load(inputStream);
				propertiesList.put(name, prop);
				
			} catch (IOException e) {
				
				throw new Exception(e);
				
			}
			
		}
		
		return prop;
		
	}
	
	/**
	 * @return o properties que contém as configurações do sistema
	 * @throws Exception 
	 */
	public static Properties getAppProperties() throws Exception {
		
		return getProperties(PropertiesHandler.APP_PROPERTIES);
		
	}
	
}