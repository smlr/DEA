package vea.uebung03.b;

public class ThreadStarter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
				
/*int cntPoints = 6;
		
		double[] values = {
				-1, 1.5, 3, 1.5, -1, -1, 
				1.5, -1, -1, 1.5, 2, -1, 
				3, -1, -1, 2, -1, 2.5,
				1.5, 1.5, 2, -1, 2, -1,
				-1, 2, -1, 2, -1, 5,
				-1, -1, 2.5, -1, 5, -1
		};*/
		
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
		/*
		int cntPoints = 8;
		
		double[] values = {
			-1, 5, 6, 11, 11, 7, -1, -1,
			5, -1, -1, -1, -1, 8, -1, 6,
			6, -1, -1, -1, -1, -1, -1, -1, 
			11, -1, -1, -1, -1, -1, -1, -1, 
			11, -1, -1, -1, -1, -1, -1, -1, 
			7, 8, -1, -1, -1, -1, 30, -1, 
			-1, -1, -1, -1, -1, 30, -1, -1, 
			-1, 6, -1, -1, -1, -1, -1, -1
		};*/
		
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
	
		long runtime = System.currentTimeMillis();
		
		NearestNeighborSolver[] threads = new NearestNeighborSolver[cntPoints];
		
		try {
			for(int i = 0; i < cntPoints; i++) {
				if (i == 0) {
					threads[i] = new NearestNeighborSolver(i, route);
				} else {
					threads[i] = new NearestNeighborSolver(i);
				}
				
				threads[i].start();
			}
			
			for (NearestNeighborSolver t : threads) {
				t.join();
			}
		}
		catch ( InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < cntPoints; i++) {
			System.out.println(i + ": " + threads[i].getLocalDistance() + ". Strecke: " + threads[i].__toString());
		}
		
		
		System.out.print("Bestes Ergebniss an Startpunkt " + NearestNeighborSolver.bestSolution.getFirst() + " mit Kosten von " + NearestNeighborSolver.distance + ". Strecke: ");
		System.out.println(NearestNeighborSolver.bestSolution.toString());
				
		runtime = System.currentTimeMillis() - runtime;
		
		System.out.println("Laufzeit: " + runtime);


	}

}
