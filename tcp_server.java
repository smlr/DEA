import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Multimedia
 *
 */
public class tcp_server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Server gestartet");
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(50002);
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
						System.out.println("Fehler beim Schlieﬂen: ");
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	private static void handleConnection(Socket client) throws IOException {
		
		System.out.println("handeConnection() gerufen");
		
		Scanner in = new Scanner (client.getInputStream());
		
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		
		String op = in.nextLine();
		String f1 = in.nextLine();
		String f2 = in.nextLine();
		
		String output = "";
		
		try {
			Class <?> klasse = Class.forName("tcp_server");

			Method methode = klasse.getMethod(op, null);
			output = (String)methode.invoke(klasse.newInstance(), null);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		out.println("Result: " + output);
		
	}
	
	public String Bla() {
		return "Bla";
	}

	public String Blub() {
		return "Blub";
	}
}
