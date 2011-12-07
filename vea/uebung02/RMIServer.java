package vea.uebung02;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;

import vea.uebung02.NormalDistribution;

public class RMIServer {

	public static void main(String[] args) throws RemoteException {
			
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			RemoteServer.setLog(System.out);
			
			Registry registry = LocateRegistry.getRegistry();
			NormalDistribution nd = new NormalDistribution();
			System.out.println(nd.getArea(0.5, 1, 1, 0));
			registry.rebind("NormalDistribution", nd);
			
			System.out.println("Waiting for Clients to connect and run NormalDistribution remotely..");
	}

}
