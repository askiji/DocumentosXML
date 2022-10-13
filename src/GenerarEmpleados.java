

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class GenerarEmpleados {

//	public static void main(String[] args)
//			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
//
////		EscribirXML();
////		LeerXML();
//
//	}

	protected static void EscribirXML()
			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// Creamos una instancia de DocumentBuilderFactory para construir el parser y
		// capturamos la excepci�n
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementacion = builder.getDOMImplementation();
			// Creamos un documento vac�o de nombre doc con el nodo ra�z de nombre Empleados
			// y asignamos la versi�n de XML

			Document doc = implementacion.createDocument(null, "Empleados", null);
			doc.setXmlVersion("1.0");

			// Creamos 4 nodos empleado con sus respectivos nodos hijos (id, apellido,depto,
			// salario)
			Element nodoP1 = doc.createElement("empleado"); // creamos el nodo empleado
			doc.getDocumentElement().appendChild(nodoP1); // a�adimos el nodo al documento

			CrearElementoHijo("id", "1", nodoP1, doc);
			CrearElementoHijo("apellido", "Garc�a", nodoP1, doc);
			CrearElementoHijo("depto", "10", nodoP1, doc);
			CrearElementoHijo("salario", "1000.45", nodoP1, doc);

			Element nodoP2 = doc.createElement("empleado"); // creamos el nodo empleado
			doc.getDocumentElement().appendChild(nodoP2); // a�adimos el nodo al documento

			CrearElementoHijo("id", "2", nodoP2, doc);
			CrearElementoHijo("apellido", "Gil", nodoP2, doc);
			CrearElementoHijo("depto", "20", nodoP2, doc);
			CrearElementoHijo("salario", "2400.6", nodoP2, doc);

			Element nodoP3 = doc.createElement("empleado"); // creamos el nodo empleado
			doc.getDocumentElement().appendChild(nodoP3); // a�adimos el nodo al documento

			CrearElementoHijo("id", "3", nodoP3, doc);
			CrearElementoHijo("apellido", "L�pez", nodoP3, doc);
			CrearElementoHijo("depto", "10", nodoP3, doc);
			CrearElementoHijo("salario", "3000.0", nodoP3, doc);

			Source source = new DOMSource(doc);// Creamos la fuente del documento
			// Creamos el resultado en el fichero Empleados.xml
			Result resultado = new StreamResult(new File(
					"."));
			// Se obtiene el TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// se realiza la transformaci�n del documento a fichero
			transformer.transform(source, resultado);

			// si queremos mostrar el documento por pantalla en lugar de guardarlo en un
			// fichero, haremos:
//			Result resultadoConsola=new StreamResult(System.out);
//			transformer.transform(source, resultadoConsola);

		} catch (ParserConfigurationException e) {

		}
	}

	protected static void CrearElementoHijo(String datoEmpleado, String valor, Element padre, Document documento) {
		Element elem = documento.createElement(datoEmpleado);
		Text texto = documento.createTextNode(valor);
		elem.appendChild(texto);
		padre.appendChild(elem);

	}

	protected static void LeerXML() {
		// creamos una instancia de
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(
					"/home/profesor/eclipse-workspace/EjerciciosAD1/src/dom_XML/empleados.xml"));

			// Obtenemos la lista de nodos con el nombre empleado de todo el documento
			NodeList empleados = doc.getElementsByTagName("empleado");
			// Creamos un bucle para recorrer toda la lista de nodos
			for (int i = 0; i < empleados.getLength(); i++) {
				Node nodoEmple = empleados.item(i);// obtenemos el nodo i
				if (nodoEmple.getNodeType() == Node.ELEMENT_NODE) // si es un elemento de tipo nodo
				{
					Element elem = (Element) nodoEmple;
					System.out.println("ID: " + elem.getElementsByTagName("id").item(0).getTextContent());
					System.out.println("Apellido: " + elem.getElementsByTagName("apellido").item(0).getTextContent());
					System.out.println("Departamento: " + elem.getElementsByTagName("depto").item(0).getTextContent());
					System.out.println("Salario: " + elem.getElementsByTagName("salario").item(0).getTextContent());

				}
			}

		} catch (Exception e) {
		}

	}

	protected static String DameNodo(String etiqueta, Element elem) {
		NodeList nodos = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valorNodo = (Node) nodos.item(0);
		return valorNodo.getNodeValue();
	}

	protected static String DameValorNodo(String etiqueta, Element elem) {
		String valor = elem.getElementsByTagName(etiqueta).item(0).getTextContent();

		return valor;
	}
}