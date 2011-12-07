package vea.uebung03.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class NearestNeighborSolver {

	private double[][] route; 
	private boolean[] dead; // Hilfarray zum performancen Checken ob ein Punkt noch über unbekannte Nachbarpunkte verfügt
	private boolean[] visited; // Hilfarray zum perfomanten Checken ob Punkt i schon angefahren wurde
	private double distance = 0; // zurückgelegte Strecke
	private LinkedList<Integer> result = new LinkedList<Integer>(); // Reihenfolge der bereits angefahrenen Punkte
	private int todo; // wie viele Punkte sind noch anzufahren
	
	
	public NearestNeighborSolver(double[][] route) {
			
		this.dead = new boolean[route[0].length];
		this.visited = new boolean[route[0].length];
		this.route = route;
		this.todo = route[0].length; 
		
		
	}
	
	//private 
	
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
			
			////System.out.println("Sackgasse, bisher besucht: " + java.util.Arrays.toString(this.visited));
			
			////System.out.println("Tote Punkte: " + java.util.Arrays.toString(this.dead));
			
			do {
				rndNode = rnd.nextInt(this.visited.length);
				visited = this.visited[rndNode];
				dead = this.dead[rndNode];
				////System.out.println("Random: " + rndNode);
			} while ( visited == false || rndNode == start || dead == true ); // andere unbekannte, nicht tote Punkte suchen
			
			////System.out.println("Nächster Random Punkt, da Sackgasse: " + rndNode);
			
			Iterator<Integer> i = this.result.descendingIterator();
			
			int s = start;
			int next; i.next();
			LinkedList<Integer> queue = new LinkedList<Integer>();
			while(i.hasNext()) { // wir laufen den bekannten Weg zurück um am ermittelten, zufälligen Punkt weiterzumachen
				next = i.next();
				////System.out.println("Next: " + next);
				this.distance += this.route[s][next];
				
				if(next == rndNode)
					break;
				
				queue.add(next);
				s = next;
				////System.out.println("S: " + s);
			}
			this.result.addAll(queue);
			
			return this.solve(rndNode);
		}
		
		// Mische Minima
		Collections.shuffle(min);
		
		if (unknownPaths == 1) // Falls es nur noch einen unbekannten Weg gibt, gehen wir nun diesen entlang.
			this.dead[start] = true;
		
		////System.out.println("Punkt [" + start + "] Minima: " + min.toString());
		////System.out.println(this.route[start][min.get(0)]);
		this.distance += this.route[start][min.get(0)]; // zähle Distanz die wir zurücklegen
		this.solve(min.get(0)); // mache am nächsten Punkt weiter
		
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
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
		}; */
		
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
		
		//NearestNeighborSolver n = new NearestNeighborSolver(route);
		//////System.out.println("Route von (" + 0 + "): " + n.solve(0).toString());
		
		for(int i = 0; i < cntPoints; i++) { // Dauert mit großem Bsp Array ca 16 Sek 
			//for(int j = 0; j < 1000; j++) {
				NearestNeighborSolver n = new NearestNeighborSolver(route);
				System.out.println("Route von (" + i + "): " + n.solve(i).toString());
				System.out.println("Distanz: " + n.getDistance() );
			//}
		}
		
		runtime = System.currentTimeMillis() - runtime;
		
		System.out.println("Laufzeit: " + runtime);
		
	}
	
	public double getDistance() {
		return this.distance;
	}

}
