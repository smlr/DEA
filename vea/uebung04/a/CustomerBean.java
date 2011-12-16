package vea.uebung04.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class CustomerBean {
	
	private int customerid; 
	private String name;
	private String street;
	private String city;
	private int postalcode; 


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		CustomerBean cb = new CustomerBean();
		cb.setCity("Passau");
		cb.setCustomerid(1337);
		cb.setName("Sven");
		cb.setStreet("Löwengrube");
		cb.setPostalcode(94032);

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		Document document = builder.newDocument();
		Element cbElement = cb.toXML(document);
		document.appendChild(cbElement);
		
		System.out.println(cb.toString(document));
		
		CustomerBean cb2 = new CustomerBean();
		
		try {
			cb2.parseXML(cbElement);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cb2.setName("hansdampf");
		
		Document document2 = builder.newDocument();
		Element cbElement2 = cb2.toXML(document2);
		document2.appendChild(cbElement2);
		System.out.println(cb2.toString(document2));
	}
	
	public org.w3c.dom.Element toXML(org.w3c.dom.Document document) {
		
		Class<CustomerBean> dies = (Class<CustomerBean>) this.getClass();
		//System.out.println(java.util.Arrays.toString(dies.getFields()));
		
		Element cbElement = document.createElement(dies.getSimpleName());
		
		
		Element ciElement = document.createElement("customerid");
		ciElement.setTextContent(Integer.toString(this.customerid));
		cbElement.appendChild(ciElement);
		
		Element nameElement = document.createElement("name");
		nameElement.setTextContent(this.name);
		cbElement.appendChild(nameElement);
		
		Element streetElement = document.createElement("street");
		streetElement.setTextContent(this.street);
		cbElement.appendChild(streetElement);
		
		Element cityElement = document.createElement("city");
		cityElement.setTextContent(this.city);
		cbElement.appendChild(cityElement);
		
		Element zipElement = document.createElement("postalcode");
		zipElement.setTextContent(Integer.toString(this.postalcode));
		cbElement.appendChild(zipElement);
		
		return cbElement;
		
	}
	
	
	public void parseXML(org.w3c.dom.Element element) throws ParserConfigurationException {
		
		this.customerid = Integer.parseInt(this.valueByName("customerid", element));
		this.name = this.valueByName("name", element);
		this.street = this.valueByName("street", element);
		this.city = this.valueByName("city", element);
		this.postalcode = Integer.valueOf(this.valueByName("postalcode", element));
		
		return;
	}

	public String toString(org.w3c.dom.Document document) {
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		OutputFormat format = new OutputFormat(document);
		XMLSerializer serializer = new XMLSerializer(bout, format);
		try {
			serializer.serialize(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bout.toString();
	}	
	
	/**
	 * 
	 * @param name - Name des Elements, dessen Wert ausgelesen werden soll
	 * @param e - Wurzelelement in welchem nach dem Wert gesucht werden soll
	 * @return String mit dem Wert des gesuchten Element oder NULL wenn Element nicht vorhanden ist
	 */
	public String valueByName(String name, Element e) {				
		return (e.getElementsByTagName(name).getLength() > 0) ? e.getElementsByTagName(name).item(0).getTextContent() : null;
	}


	public int getCustomerid() {
		return customerid;
	}


	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getPostalcode() {
		return postalcode;
	}


	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	
	
}
