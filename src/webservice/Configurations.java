/*
 * Reads configuration information from the config.properties file
 */
package webservice;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

	 
public class Configurations {

		//Set default values
		String serverName = "webserver", serverRoot = "wwwroot" ;
		int serverPort = 80, serverMaxQueueLength = 10;

		InputStream inputStream;
		
		private void Error(){
			System.out.println("INFO: Error when reading config.properties. Default values set.");
		}
	 
		public Configurations() {
			
			//Create properties to read the file and set values
			Properties properties = new Properties();
			try {
						 
					inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
					try {
					if (inputStream != null) {
							properties.load(inputStream);
						}
					}
					catch(IOException e){
						Error();
					}
					
				if(properties.getProperty("SERVER_NAME") != null){
					serverName = properties.getProperty("SERVER_NAME");
				}
				
				if(properties.getProperty("SERVER_ROOT")!= null) {
					serverRoot = properties.getProperty("SERVER_ROOT");
				}
					
				serverPort = Integer.parseInt(properties.getProperty("SERVER_PORT"));
				serverMaxQueueLength = Integer.parseInt(properties.getProperty("SERVER_MAX_QUEUE_LENGTH"));
				
				}
			
			catch(NumberFormatException e) {
				Error();
			}
		}
			//Getters
			public int getServerPort() {
				return serverPort;
			}
			
			public int getServerMaxQueueLength() {
				return serverMaxQueueLength;
			}
}
