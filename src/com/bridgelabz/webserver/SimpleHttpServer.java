package com.bridgelabz.webserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	public static int DEFAULT_PORT = 9023;
	public static int port;

	public static void main(String[] args) {
		SimpleHttpServer httpServer = new SimpleHttpServer();
		httpServer.start(DEFAULT_PORT);
	}

	private void start(int port) {
		SimpleHttpServer.port = port;
		try {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("Server running at " + port);

			httpServer.createContext("/", new Handler.RootHandler());
			httpServer.createContext("/echoHeader", new Handler.EchoHeaderHandler());
			httpServer.createContext("/echoGet", new Handler.EchoGetHandler());
			httpServer.createContext("/echoPost", new Handler.EchoPostHandler());
			httpServer.setExecutor(null);
			httpServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}