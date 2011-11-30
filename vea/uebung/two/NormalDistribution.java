package vea.uebung.two;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NormalDistribution extends UnicastRemoteObject implements NormalDistributionIface {
	final static int PRECISION=(int) Math.pow(10, 5); 
	/**
	 * @param args
	 */
	
	public NormalDistribution() throws RemoteException {
		super();
	}
	
	public static void main(String[] args)  throws RemoteException  {
		//test
	
		System.out.println(getArea(0.5, 1, 1, 0));

	}

	public double getDNormRemote (double x, double var, double ew)  throws RemoteException {
		return NormalDistribution.getDNorm(x, var, ew);
	}
	
	public static double getDNorm(double x, double var, double ew)  throws RemoteException {
		double d=(1/(var*Math.sqrt(2*Math.PI)))*Math.exp(-0.5d*Math.pow(((x-ew)/var),2d));
		return d; 
	}
	
	public double getAreaRemote (double x1, double x2, double var, double ew)  throws RemoteException  {
		return NormalDistribution.getArea(x1, x2, var, ew);
	}
	
	public static double getArea(double x1, double x2, double var, double ew)  throws RemoteException  {
		double area=0; 
		double step=Math.abs((x1-x2)/PRECISION); 
		//System.out.println("step= " + step);
		for(double i=x1; i<x2;){
			area+=step*getDNorm(i, var, ew); 
			i+=step; 
		}
		return area; 
	}

}
