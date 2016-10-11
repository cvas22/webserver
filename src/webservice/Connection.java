/*
 * Implements a connection object
 * Reference: This has been inspired from Igor 
 */
	
package webservice;

import java.io.*;
import java.net.Socket;
import webservice.Response;

public class Connection implements Runnable{

	private Socket socket;
	private Server server;
	
	//Set the socket and server
	public Connection(Server server, Socket socket) {
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			Request request = Request.process(in);
			Response response;
			String method = request.getType();
			
			//If method is GET or POST, 
			//send response OK and attach the file else just send a "404"
			if ((method == "GET")  || (method == "POST")) {
					response = new Response("OK");
					File file = new File(request.getPage());
					response.reponseFile(file);
				} else {
					response = new Response("404");
			}
			in.close();
			out.close();
			
		} catch (IOException e) {
			System.out.println("Cannot close I/O Stream!");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Cannot close the socket connection!");
			}
		}
	}

}
