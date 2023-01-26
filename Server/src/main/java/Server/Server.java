package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.HashMap;

import java.util.Map;

/*
 * Starts a new local server
 * Calls the required handlers at runtime.
 */
public class Server {

	public static void main(String[] args) throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		System.out.println("server started at ");
		server.createContext("/WorldBank", new WBServerHandler());
		server.createContext("/OpenCovid", new OpenCovidHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
	}
}
