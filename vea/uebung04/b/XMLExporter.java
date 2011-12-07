package vea.uebung04.b;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import vea.uebung04.a.CustomerBean;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class XMLExporter {

	private File file;
	private org.w3c.dom.Document document;
	private org.w3c.dom.Element root;
	
	public XMLExporter(File f) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		this.document = builder.newDocument();
		this.root = this.document.createElement("root");
		this.document.appendChild(this.root);
		this.file = f;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		CustomerBean cb = new CustomerBean();
		cb.setCity("Passau");
		cb.setCustomerid(1337);
		cb.setName("Sven");
		cb.setStreet("Löwengrube");
		cb.setPostalcode(94032);
	
		File f = new File("/home/sven/test.xml");
		XMLExporter x = new XMLExporter(f);
		
		Element e = x.document.createElement("test");
		e.setTextContent("inhalt");
		
		x.appendElement(e);
		
		System.out.println(x);
		x.export();

	}
	
	public void appendElement(org.w3c.dom.Element element) {
		this.root.appendChild(element);
		return;
	}
	
	public void export() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(this.file);
			fos.write(this.toString().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		
		return;
	}
	
	public Document getDocument() {
		return this.document;
	}

	@Override
	public String toString() {
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		OutputFormat format = new OutputFormat(document);
		XMLSerializer serializer = new XMLSerializer(bout, format);
		try {
			serializer.serialize(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bout.toString();
	}
	
	

}
