/*
 * Process a HTTP request
 * Reference: Igor
 * 
 *  Sample request
 *  GET /hello.htm HTTP/1.1
 *  POST /cgi-bin/process.cgi HTTP/1.1
 *
 * References/Credits due:
 * www.java2blog.com/2013/03/web-service-tutorial.html
 * http://www.eclipse.org/webtools/community/education/web/t320/Implementing_a_Simple_Web_Service.pdf 
 * https://github.com/ibogomolov/WebServer
 * */

package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {
	
	//Store request Info
	private String protocol;
	private String type;
	private String page;
	
	public String getProtocol() {
		return protocol;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPage() {
		return page;
	}
	
	
	public static Request process(InputStream in) {
	
	try {
		
		Request request = new Request();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = reader.readLine();
		
		if (line != null) {
		String[] requestLine = line.split(" ", 3);
		//GET or POST	
		request.type = requestLine[0];
		// Ex: hello.htm
		request.page = requestLine[1];
		// Ex: HTTP1.1
		request.protocol = requestLine[1];
		
		//Return the request
		return request;
		}
	}
		catch (IOException e) {
		System.err.println(e.getMessage());
		}
	return null;
}
	
}
	