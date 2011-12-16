package vea.uebung04.d;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlElement;

@javax.xml.bind.annotation.XmlRootElement
public class CustomerCollection {

	private int customerid; 
	private String name;
	private String street;
	private String city;
	private int postalcode; 
	
	@XmlElements({
		@XmlElement(name="customerid", type=Integer.class),
		@XmlElement(name="name", type=String.class),
		@XmlElement(name="street", type=String.class),
		@XmlElement(name="city", type=String.class),
		@XmlElement(name="postalcode", type=Integer.class),
	})

	
	LinkedList<CustomerBean> collection = new LinkedList<CustomerBean>();

	
	public LinkedList<CustomerBean> getCollection() {
		return collection;
	}

	public void setCollection(LinkedList<CustomerBean> collection) {
		this.collection = collection;
	}
	
	
	
}
