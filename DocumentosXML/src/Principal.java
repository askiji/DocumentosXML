import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class Principal {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
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
