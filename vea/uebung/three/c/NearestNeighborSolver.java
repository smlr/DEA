package vea.uebung.three.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class NearestNeighborSolver extends Thread implements Runnable {

	private static double[][] route; 
	public static LinkedList<Integer> bestSolution; 
	public static double distance = -1;
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock writeLock = lock.writeLock();
	private final Lock rwLock = new ReentrantLock();
	
	private LinkedList<Integer> result = new LinkedList<Integer>(); // Reihenfolge der bereits angefahrenen Punkte
	private boolean[] dead; // Hilfarray zum performancen Checken ob ein Punkt noch über unbekannte Nachbarpunkte verfügt
	private boolean[] visited; // Hilfarray zum perfomanten Checken ob Punkt i schon angefahren wurde
	private double localDistance = 0; // zurückgelegte Strecke
	private int todo; // wie viele Punkte sind noch anzufahren
	private int startpoint;
	
	public NearestNeighborSolver(int startpoint, double[][] route) {
		writeLock.lock();
		try {
			NearestNeighborSolver.route = route;
		}
		finally {
			writeLock.unlock();
		}
		
		this.init(startpoint);
	}
	
	public NearestNeighborSolver(int startpoint) throws Exception {
		if (NearestNeighborSolver.route == null) {
			throw new Exception("Beim ersten Aufruf muss die Route mit übergeben werden.");
		}
		
		this.init(startpoint);
	}
	
	private void init(int startpoint) {
		this.startpoint = startpoint;
		this.dead = new boolean[route[0].length];
		this.visited = new boolean[route[0].length];
		this.todo = route[0].length; 
	}
	
	public void run() {
		this.solve(this.startpoint);
		
		rwLock.lock();
		try {
			if (this.localDistance < NearestNeighborSolver.distance || NearestNeighborSolver.distance == -1) {
				NearestNeighborSolver.bestSolution = this.result;
				NearestNeighborSolver.distance = this.localDistance;
			}
		}
		finally {
			rwLock.unlock();

		}
	
	}
	
	
	public String getResult() {
		
		return this.startpoint + ": " + this.getLocalDinstance() + ". Strecke: " + this.__toString();
	}
	
	public LinkedList<Integer> solve(int start) {
		
		////System.out.println("result.contains(start): " + result.contains(start));
		
		if (!result.contains(start)) {
			////System.out.println("todo eins abziehen: " + this.todo);
			if (--this.todo == 0) {
				result.add(start);
				return result;
			}
			
			////System.out.println("todo eins abgezogen: " + this.todo);
		}
		
		this.visited[start] = true;
		
		result.add(start); // füge aktuellen punkt ein
		
		////System.out.println("Weg bisher: " + result.toString());
		
		ArrayList<Integer> min = new ArrayList<Integer>();
		min.add(-1);
		
		int unknownPaths = 0;
		
		for(int p = 0; p < route[0].length; p++) { // ermittle alle Punktbeziehungen
			int a_min = min.get(0);
			////System.out.println("Punkt [" + start + "] kostet zu ["+p+"]: " + route[start][p] + " ### " + result.contains(p) );
			if (route[start][p] >= 0) { // Punkt erreichtbar?
				if(!result.contains(p)) { // Punkt bekannt?
					++unknownPaths;
					////System.out.println("Erreichbare unbekannte Punkte: " + unknownPaths); 
					
					if ( (a_min < 0) || (route[start][p] <= route[start][a_min]) ) { // neues Minimum?
						////System.out.println("Minium!");
						if (a_min == -1 || route[start][p] < route[start][a_min]) { // leere Liste, wenn wir ein vorläufig absolutes Minimum gefunden haben
							////System.out.println("clear");
							min.clear();
						}
						
						min.add(p);
					}
				}
			}
		}
	
		if (unknownPaths == 0)
			this.dead[start] = true;
		
		if (min.get(0) < 0) { // Sackgasse oder alle Strecken des Punktes gelaufen
			int rndNode;
			boolean visited;
			boolean dead;
			Random rnd = new Random();
			
			//System.out.println("Sackgasse, bisher besucht: " + java.util.Arrays.toString(this.visited));
			
			//System.out.println("Tote Punkte: " + java.util.Arrays.toString(this.dead));
			
			do {
				rndNode = rnd.nextInt(this.visited.length);
				visited = this.visited[rndNode];
				dead = this.dead[rndNode];
				//System.out.println("Random: " + rndNode);
			} while ( visited == false || rndNode == start || dead == true ); // andere unbekannte, nicht tote Punkte suchen
			
			//System.out.println("Nächster Random Punkt, da Sackgasse: " + rndNode);
			
			Iterator<Integer> i = this.result.descendingIterator();
			
			int s = start;
			int next; i.next();
			LinkedList<Integer> queue = new LinkedList<Integer>();
			while(i.hasNext()) { // wir laufen den bekannten Weg zurück um am ermittelten, zufälligen Punkt weiterzumachen
				next = i.next();
				//System.out.println("Next: " + next);
				this.localDistance += NearestNeighborSolver.route[s][next];
				
				if(next == rndNode)
					break;
				
				queue.add(next);
				s = next;
				//System.out.println("S: " + s);
			}
			this.result.addAll(queue);
			
			return this.solve(rndNode);
		}
		
		// Mische Minima
		Collections.shuffle(min);
		
		if (unknownPaths == 1) // Falls es nur noch einen unbekannten Weg gibt, gehen wir nun diesen entlang.
			this.dead[start] = true;
		
		//System.out.println("Punkt [" + start + "] Minima: " + min.toString());
		//System.out.println(NearestNeighborSolver.route[start][min.get(0)]);
		this.localDistance += NearestNeighborSolver.route[start][min.get(0)]; // zähle Distanz die wir zurücklegen
		this.solve(min.get(0)); // mache am nächsten Punkt weiter
		
		return result;
	}

	public double getLocalDinstance() {
		return this.localDistance;
	}
	
	public String __toString() {
		return this.result.toString();
	}

}
