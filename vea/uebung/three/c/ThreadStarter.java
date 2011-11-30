package vea.uebung.three.c;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadStarter implements Runnable {
	
	private ExecutorService pool;
	private double[][] route; 

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
			
		ThreadStarter ts = new ThreadStarter();
		
		ts.pool = Executors.newFixedThreadPool(2);
		
		int cntPoints = 8;
		
		double[] values = {
				-1, 4, 2, -1, -1, -1, -1, -1,  
				4, -1, -1, 6, -1, 5, -1, -1, 
				2, -1, -1, 3, -1, -1, -1, -1, 
				-1, 6, 3, -1, 4, -1, 1, 1.5,
				-1, -1, -1, 4, -1, -1, -1, 3,
				-1, 5, -1, -1, -1, -1, -1, 4,
				-1, -1, -1, 1, -1, -1, -1, 1.5,
				-1, -1, -1, 1.5, 3, 4, 1.5, -1
		}; 
		
		if (values.length != cntPoints*cntPoints) {
			System.out.println("Anzahl der Werte stimmt nicht mit Punktezahl überein.");
			return;
		}
			
		double[][] route = new double[cntPoints][cntPoints];
		
		System.out.print(" \t");
		
		// Schreibe Werte in 2d Array && gebe es aus
		for (int col=0; col < cntPoints; col++)
			System.out.print(" [" + col + "] \t");
		
		System.out.println("");
			
		for (int row=0; row < cntPoints; row++) {
			System.out.print("[" + row + "] \t");
			for (int col=0; col < cntPoints; col++) {		
				route[row][col] = values[row*cntPoints + col];
				
				if(route[row][col] >= 0)
					System.out.print(" ");
				
				System.out.print(route[row][col] + "\t");
			}
		
			System.out.println("");
		}

		ts.route = route;
		
		ts.run();
		
	}

	public void run() {
		
		int cntPoints = this.route.length;
		
		long runtime = System.currentTimeMillis();
				
	    List<Future<NearestNeighborSolver>> tasks = new ArrayList<Future<NearestNeighborSolver>>();
	    NearestNeighborSolver n;
	    
		try {
		
			for (int i = 0; i < cntPoints; i++) {
				if (i == 0) {
					n = new NearestNeighborSolver(i, route);
				} else {
					n = new NearestNeighborSolver(i);
				}
				
				Future<NearestNeighborSolver> future = this.pool.submit(n, n);
				
				tasks.add(future);
			}
			
			for (Future<NearestNeighborSolver> t : tasks) {
				NearestNeighborSolver p = t.get();
				System.out.println(p.getResult());
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.pool.shutdown();
		}
	
		
		//for(int i = 0; i < cntPoints; i++) {
			//System.out.println(i + ": " + threads[i].getLocalDinstance() + ". Strecke: " + threads[i].__toString());
		//}
		
		
		System.out.print("Bestes Ergebniss an Startpunkt " + NearestNeighborSolver.bestSolution.getFirst() + " mit Kosten von " + NearestNeighborSolver.distance + ". Strecke: ");
		System.out.println(NearestNeighborSolver.bestSolution.toString());
				
		runtime = System.currentTimeMillis() - runtime;
		
		System.out.println("Laufzeit: " + runtime);
		
	}
}