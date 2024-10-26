package ejercicio;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class MainObj {
	final static int NUM_ALUMNOS = 5;

	public void start(String[] args) {
		ArrayList<Alumno> alumnos = new ArrayList<>();
		MaikIO io = new MaikIO();
		leerAlumno(io, alumnos);
		io.close();
		Misedtru xml = new Misedtru("colegio");
		crearAlumnos(xml, alumnos);
		xml.writeToXml("doc.xml");
	}
	
	public void leerAlumno(MaikIO io, ArrayList<Alumno> alumnos) {
		for (int i = 0; i < NUM_ALUMNOS; i++) {
			alumnos.add(new Alumno(
					io.readInt("Dame el NIA"),
					io.readString("Dame el Nombre"),
					io.readString("Dame el Apellido"),
					io.readChar("Dame el Genero"),
					io.readDate("Dame la Fecha (d-M-yyyy)"),
					io.readString("Dame el Ciclo"),
					io.readString("Dame el Curso"),
					io.readString("Dame el Grupo")));
			io.hr();
		}
	}
	
	public void crearAlumnos(Misedtru xml, ArrayList<Alumno> alumnos) {
		for (int i = 0; i < alumnos.size(); i++) {
			crearAlumno(xml, alumnos.get(i), i);
		}
	}
	
	public void crearAlumno(Misedtru xml, Alumno alumno, int i) {
		Element alumnoXml = xml.addMainElement("alumno");
		xml.addAtribute("id", i + "", alumnoXml);
		xml.addElementTo("nia", alumno.getNia() + "", alumnoXml);
		xml.addElementTo("nombre", alumno.getNombre(), alumnoXml);
		xml.addElementTo("nombre", alumno.getApellidos(), alumnoXml);
		xml.addElementTo("genero", alumno.getGenero() + "", alumnoXml);
		xml.addElementTo("nacimiento", Alumno.convertirFecha(alumno.getNacimiento()), alumnoXml);
		xml.addElementTo("ciclo", alumno.getCiclo(), alumnoXml);
		xml.addElementTo("cruso", alumno.getCurso(), alumnoXml);
		xml.addElementTo("grupo", alumno.getGrupo(), alumnoXml);
	}
}
