package vea.uebung.one;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Aufgabe3_Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Server gestartet");
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			Socket client = null;
			
			try {
				client = server.accept();
				System.out.println("neue Connection");
				handleConnection(client);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (client != null) {
					try {
						client.close();
					} catch (IOException e) {
						System.out.println("Fehler beim Schließen: ");
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	private static void handleConnection(Socket client) throws IOException {
				
		Scanner in = new Scanner (client.getInputStream());
		
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		
		String message = "";
		String name = "";
		
		while (!message.equals("/quit")) {
			name = in.nextLine();
			message = in.nextLine();
			
			System.out.println(name + ": " + message);
		}
		
		System.out.println(name + " hat den Chat verlassen.");
		
		return;
	}
	

}
