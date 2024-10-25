package ejercicio;

import org.w3c.dom.Element;

public class MainObj2 extends MainObj {
	@Override
	public void crearTrabajador(Misedtru xml, Alumno alumno, int i) {
		Element alumnoXml = xml.addMainElement("alumno");
		xml.addAtribute("id", i + "", alumnoXml);
		xml.addAtribute("nia", alumno.getNia() + "", alumnoXml);
		xml.addAtribute("nombre", alumno.getNombre(), alumnoXml);
		xml.addAtribute("nombre", alumno.getApellidos(), alumnoXml);
		xml.addAtribute("genero", alumno.getGenero() + "", alumnoXml);
		xml.addAtribute("nacimiento", Alumno.convertirFecha(alumno.getNacimiento()), alumnoXml);
		xml.addAtribute("ciclo", alumno.getCiclo(), alumnoXml);
		xml.addAtribute("cruso", alumno.getCurso(), alumnoXml);
		xml.addAtribute("grupo", alumno.getGrupo(), alumnoXml);
	}
}
