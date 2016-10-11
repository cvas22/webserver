/*
 * Implements a sample HTTP response to requests 
 */
package webservice;

import java.io.*;

public class Response {
	
	private FileInputStream reader;
	private String string;

	public Response (String string) {
		this.string=string;
		
	}
	
	public Response reponseFile (File f) {
		if (f.isFile()) {
			try {
				reader = new FileInputStream(f);
			}
			 catch (IOException e) {
				System.err.println("Error while reading " + f);
			}
			return this;
		} else {
			return new Response("404");
		}
	}
}
