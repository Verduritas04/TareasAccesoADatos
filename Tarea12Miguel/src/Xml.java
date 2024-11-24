import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Xml {
	Document doc = null;
	
	public Xml() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructor = null;
		try {
			constructor = factory.newDocumentBuilder();
			DOMImplementation implement = constructor.getDOMImplementation();
			doc = implement.createDocument(null, "grupos", null);
			doc.setXmlVersion("1.0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void guardarEnXml(List<String> grupos, List<String> alumnos) {
		for (String lineaGrupo : grupos) {
			Element grupo = doc.createElement("grupo");
			String[] datosGrupo = lineaGrupo.split(" ");
			grupo.setAttribute("cod_grupo", datosGrupo[0]);
			grupo.setAttribute("nombre", datosGrupo[1]);
			for (String lineaAlumno : alumnos) {
				String[] datosAlumno = lineaAlumno.split(" ");
				if (datosGrupo[0].equals(datosAlumno[datosAlumno.length - 1])) {
					Element alumno = doc.createElement("alumno");
					alumno.setAttribute("nia", datosAlumno[0]);
					alumno.setAttribute("nombre", datosAlumno[1]);
					alumno.setAttribute("apellidos", datosAlumno[2]);
					alumno.setAttribute("genero", datosAlumno[3]);
					alumno.setAttribute("fecha_nacimiento", datosAlumno[4]);
					alumno.setAttribute("ciclo", datosAlumno[5]);
					alumno.setAttribute("curso", datosAlumno[6]);
					alumno.setAttribute("fk_grupo", datosAlumno[7]);
					grupo.appendChild(alumno);
				}
			}
			doc.getDocumentElement().appendChild(grupo);
		}
		System.out.println("Guardado!");
	}
	
	public void chuparDeArchivo() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructor = null;
		try {
			constructor = factory.newDocumentBuilder();
			doc = constructor.parse(new File("datos.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void escribirArchivoXml() {
		Source sorce = new DOMSource(doc);
		Result result = new StreamResult(new File("datos.xml"));
		try {
			Transformer prime = TransformerFactory.newInstance().newTransformer();
			prime.transform(sorce, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
}
