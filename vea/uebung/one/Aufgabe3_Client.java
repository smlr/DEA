package vea.uebung.one;

import java.io.IOException;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Aufgabe3_Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket server = null;
		
		Scanner chatInput = new Scanner(System.in);
		System.out.print("Bitte geben Sie Ihren Namen an: ");
		String name = chatInput.nextLine();
		System.out.println("Um den Chat zu beenden, geben Sie '/quit' ein");
		
		try {
			server = new Socket("localhost", 8888);
			Scanner in = new Scanner(server.getInputStream());
			PrintWriter out = new PrintWriter(server.getOutputStream());
			
			String message = "";
					
			while(!message.equals("/quit")) {
				System.out.print("neue Nachricht: ");
				message = chatInput.nextLine();
			
				out.println(name);
				out.println(message);
				out.flush();
				System.out.println("(gesendet)");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Der Chat wurde beendet.");
			if (server != null)
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}


}
