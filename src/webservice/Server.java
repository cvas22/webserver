/*
 * Implements a basic web server.
 * 
 * References/Credits due:
 * www.java2blog.com/2013/03/web-service-tutorial.html
 * http://www.eclipse.org/webtools/community/education/web/t320/Implementing_a_Simple_Web_Service.pdf 
 * https://github.com/ibogomolov/WebServer
 */

package webservice;

import webservice.Configurations;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Server implements Runnable {
	
	private ExecutorService executorService;
	private int serverPort;
	private int serverQueueLength;
	private static ServerSocket serverSocket;
	
	
	public Server() {
		Configurations myConfig = new Configurations();
		this.serverPort = myConfig.getServerPort();
		this.serverQueueLength = myConfig.getServerMaxQueueLength();
	}
	
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Error while closing server socket.");
		}
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(serverQueueLength, TimeUnit.SECONDS)) 
				executorService.shutdownNow();
		} catch (InterruptedException e) {}
	}


	public static void main(String[] args)  {
		 new Thread(new Server()).start();	
	 }

	
	 
	@Override
	public void run() {
	 ExecutorService threadPool = Executors.newFixedThreadPool(serverQueueLength);
		 
		 try {
			 		serverSocket= new ServerSocket(serverPort, serverQueueLength);
			  }
		 
		 catch (IOException e) {
				System.out.println("Cannot start server!");
				System.exit(1);
			}
			
		 System.out.println("Server started...");
		 while (true) {
				try {
					threadPool.execute(new Thread(new Connection(this, serverSocket)));
				} catch (IOException e) {
					System.out.println("Cannot accept client.");
				}
			}
			stop();
	}

}
