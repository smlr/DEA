package vea.uebung02;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

public class RMIClient {

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
			System.out.println("RMIClient gestartet. Warte auf Eingaben.");

			Scanner input = new Scanner(System.in);
			System.out.print("x1: ");
			Double x1 = Double.parseDouble(input.nextLine());
			
			System.out.print("x2: ");
			Double x2 = Double.parseDouble(input.nextLine());
			
			System.out.print("mu: ");
			Double m = Double.parseDouble(input.nextLine());
			
			System.out.print("sigma: ");
			Double s = Double.parseDouble(input.nextLine());
			
			//if (System.getSecurityManager() == null)
			//	System.setSecurityManager(new RMISecurityManager());
			
			//Registry registry = LocateRegistry.getRegistry();
			NormalDistribution nd = (NormalDistribution) Naming.lookup("rmi://localhost:1099/NormalDistribution");
			
			System.out.println(nd.getAreaRemote(x1, x2, m, s));
			System.out.println(nd.getDNormRemote(0.5, 1, 1));
			
			System.out.println("RMIClient Ende");
	}
}
