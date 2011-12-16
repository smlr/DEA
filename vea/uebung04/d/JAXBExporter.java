package vea.uebung04.d;

import java.util.LinkedList;

public class JAXBExporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CustomerBean a = new CustomerBean();
		a.setCity("Passau");
		a.setCustomerid(1);
		a.setName("Hansdampf");
		a.setPostalcode(1234);
		a.setStreet("Löwenking");
		
		CustomerBean b = new CustomerBean();
		b.setCity("Passau");
		b.setCustomerid(1);
		b.setName("Hansdampf2");
		b.setPostalcode(1234);
		b.setStreet("Löwenking");
		
		CustomerBean c = new CustomerBean();
		c.setCity("Passau");
		c.setCustomerid(1);
		c.setName("Hansdampf3");
		c.setPostalcode(1234);
		c.setStreet("Löwenking");
		
		LinkedList<CustomerBean> l = new LinkedList<CustomerBean>();
		
		l.add(a);
		l.add(b);
		l.add(c);
		
		CustomerCollection cc = new CustomerCollection();
		cc.setCollection(l);
		
		

	}

}
