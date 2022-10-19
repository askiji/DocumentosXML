import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class Principal {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
//		ejercicio1();
		ejercicio2();

	}

	private static void ejercicio2() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("alumnos.xml"));
			// Obtenemos la lista de nodos con el nombre empleado de todo el documento
			NodeList empleados = doc.getElementsByTagName("alumno");
			// Creamos un bucle para recorrer toda la lista de nodos
			
			//TODO ver el max
			for (int i = 0; i < empleados.getLength(); i++) {
				Node nodoEmple = empleados.item(i);
				if (nodoEmple.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nodoEmple;
					System.out.println("Nombre: " + elem.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println("Apellido: " + elem.getElementsByTagName("apellido").item(0).getTextContent());
					System.out.println("Edad: " + elem.getElementsByTagName("edad").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
		}
	}
	private static void ejercicio1() throws ParserConfigurationException, TransformerConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementacion = builder.getDOMImplementation();
		
		Document doc = implementacion.createDocument(null, "alumnos", null);
		doc.setXmlVersion("1.0");

		
		CrearAlumno("Soraya", "Garcia Ramirez", "18", doc );
		CrearAlumno("Pedro", "Aldo Faldo", "28", doc);
		CrearAlumno("Gillermo", "Pérez Pérez", "31", doc);
		CrearAlumno("Jimena", "Olivos Martín", "41", doc);
		Source source = new DOMSource(doc);
		Result resultado = new StreamResult(new File("alumnos.xml"));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, resultado);
		System.out.println("End");
	}
	protected static void CrearAlumno(String nombre , String apellido, String edad,Document documento) {
		Element padre = documento.createElement("alumno");
		documento.getDocumentElement().appendChild(padre);
		CrearElementoHijo("apellido", apellido, padre, documento);
		
		CrearElementoHijo("nombre", nombre, padre, documento);
		
		CrearElementoHijo("edad", edad, padre, documento);
	}
	
	protected static void CrearElementoHijo(String datoAlumno, String valor, Element
			padre, Document documento) {

			Element elem = documento.createElement(datoAlumno);
			Text texto = documento.createTextNode(valor);

			elem.appendChild(texto);
			padre.appendChild(elem);
			}
}
