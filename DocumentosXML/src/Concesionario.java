

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class Concesionario {

//	public static void main(String[] args)
//			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
//		// TODO Auto-generated method stub
//		EscribirXML();
////		LeerXML();
////		LeerXML2(); Si no conocemos la estructura del XML
//	}

	protected static void EscribirXML() throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
		 {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementacion = builder.getDOMImplementation();
		
		
		// Creamos un documento vac�o de nombre doc con el nodo ra�z de nombre Empleados
		Document doc = implementacion.createDocument(null, "Concesionario", null);
		// y asignamos la versi�n de XML
		doc.setXmlVersion("1.0");

		// creamos el nodo empleado
		Element nCoche = doc.createElement("coche");
		// a�adimos el nodo al documento
		doc.getDocumentElement().appendChild(nCoche);

		// creamos el atributo id
		Attr attr = doc.createAttribute("id");
		attr.setValue("1");
		// a�adimos el atributo al nodo coche
		nCoche.setAttributeNode(attr);

		// creamos el nodo marca
		crearElemento(doc, nCoche,"marca","Renault");

		// creamos el nodo modelo
		
		crearElemento(doc,nCoche,"modelo","Megane");
		
		// creamos el nodo cilindrada
	
		crearElemento(doc,nCoche,"cilindrada","1.5");

		Source source = new DOMSource(doc);// Creamos la fuente del documento
		// Creamos el resultado en el fichero Empleados.xml
		Result resultado = new StreamResult(new File("."));
		// Se obtiene el TransformerFactory
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		// se realiza la transformaci�n del documento a fichero
		transformer.transform(source, resultado);
	}

	private static void crearElemento(Document doc, Element nCoche,String datoCoche,String valor) {
		Element nMarca = doc.createElement(datoCoche);
		nMarca.appendChild(doc.createTextNode(valor));
		nCoche.appendChild(nMarca);
	}

	protected static void LeerXML()  {
		try
		{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//Creamos el objeto documento que vamos a leer
		Document doc=builder.parse(new File("C:\\Users\\Lenovo Legion\\eclipse-workspace\\Acceso-a-Datos\\T1-Ficheros\\src\\xmlDOM\\concesionario.xml"));
		//Obtenemos la lista de nodos con el nombre empleado de todo el documento
		NodeList coches=doc.getElementsByTagName("coche");
		//Creamos un bucle para recorrer toda la lista de nodos
		for(int i=0;i<coches.getLength();i++)
		{
		Node nodoCoche=coches.item(i);//obtenemos el nodo i
		if(nodoCoche.getNodeType()==Node.ELEMENT_NODE) //si es un elemento de tipo  nodo
		{
		  Element elem=(Element)nodoCoche;
		  System.out.println("Coche con ID: "+ elem.getAttribute("id"));
		  System.out.println("Marca: "+ elem.getElementsByTagName("marca").item(0).getTextContent());
		  System.out.println("Modelo: "+ elem.getElementsByTagName("modelo").item(0).getTextContent());
		  System.out.println("Cilindrada: "+ elem.getElementsByTagName("cilindrada").item(0).getTextContent());
		}
		}

		}
		catch(Exception e)
		{
			
		}
	}
	protected static void LeerXML2()
	{
		try
		{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//Creamos el objeto documento que vamos a leer
		Document doc=builder.parse(new File("C:\\Users\\Lenovo Legion\\eclipse-workspace\\Acceso-a-Datos\\T1-Ficheros\\src\\xmlDOM\\concesionario.xml"));
		
		NodeList nList = doc.getElementsByTagName("coche");

		for (int i = 0; i < nList.getLength(); i++) {
		  Node node = nList.item(i);

		  if (node.getNodeType() == Node.ELEMENT_NODE) {
		    Element eElement = (Element) node;

		    if(eElement.hasChildNodes()) {
		      NodeList nl = node.getChildNodes();
		      for(int j=0; j<nl.getLength(); j++) {
		        Node nd = nl.item(j);
		        System.out.println(nd.getTextContent());
		      }
		    }
		  }
		}
	}
		catch(Exception e)
		{
			}
		}
}