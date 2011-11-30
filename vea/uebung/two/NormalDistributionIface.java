package vea.uebung.two;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface NormalDistributionIface extends Remote {

	public double getAreaRemote (double x1, double x2, double var, double ew)  throws RemoteException;
	
	public double getDNormRemote (double x, double var, double ew)  throws RemoteException;
	
}