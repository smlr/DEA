package vea.uebung.two;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Collection;

public class DynamicCollection {
	private java.util.Collection<String> collection;

	public DynamicCollection(java.util.Collection<String> collection)
			throws Exception {
		String concreteType = readFile();

		if (concreteType.equals("java.util.ArrayList")
				|| concreteType.equals("java.util.LinkedList")
				|| concreteType.equals("java.util.HashSet")
				|| concreteType.equals("java.util.Stack")
				|| concreteType.equals("java.util.TreeSet")) {
			try {
				Class cls = Class.forName(concreteType);
				//System.out.println("es wurde eine " + cls + " erstellt.");
				Constructor ct = cls.getConstructor();
				Object tmpObject = ct.newInstance();
				this.collection = (Collection<String>) tmpObject;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("Wrong Class");
		}
		Class klasse = collection.getClass();
	}

	public void addElement(String element) {
		this.collection.add(element);
	}

	public String readFile() {
		String klasse = "";
		try {
			FileReader fr = new FileReader("klasse.txt");
			BufferedReader br = new BufferedReader(fr);
			klasse = br.readLine();
			br.close(); fr.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return klasse;
	}

	@Override
	public String toString() {
for (Object element : this.collection.toArray()) {
	System.out.println(element.toString()); 
}
return ""; 

	}

}