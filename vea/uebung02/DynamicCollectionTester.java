package vea.uebung02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import vea.uebung02.DynamicCollection;

public class DynamicCollectionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> ArrayListTester = new ArrayList<String>();
		File file = new File("klasse.txt");
		try {
			Writer writer = new FileWriter(file, false);
			writer.write(ArrayListTester.getClass().getName());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			DynamicCollection c = new DynamicCollection(ArrayListTester);
			c.addElement("Hallo Welt");
			c.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}