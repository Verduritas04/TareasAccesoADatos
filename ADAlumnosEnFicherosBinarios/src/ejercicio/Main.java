package ejercicio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		Scanner sc = new Scanner(System.in);
		String ruta;
		ruta = pedirRuta(sc, "Dame la ruta del fichero.");
		sc.close();
		addAlumnos(alumnos);
		escribirAlumnos(alumnos, ruta);
	}
	
	public static String pedirRuta(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		return sc.nextLine();
	}
	
	public static void addAlumnos(List<Alumno> alumnos) {
		alumnos.add(new Alumno(1, "Pepe", "Luis", 'X', new Date(), "dam", "2º", "el que hay"));
		alumnos.add(new Alumno(2, "Laura", "Luisa", 'M', new Date(), "dam", "2º", "el que hay"));
		alumnos.add(new Alumno(3, "Luis", "Pepe", 'X', new Date(), "dam", "2º", "el que hay"));
		alumnos.add(new Alumno(4, "Travis", "Groot", 'H', new Date(), "aa", "1º", "el que hay"));
		alumnos.add(new Alumno(5, "Clara", "Oscura", 'M', new Date(), "dam", "2º", "el que hay"));
	}
	
	public static void escribirAlumnos(List<Alumno> alumnos, String ruta) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			FileOutputStream fos = new FileOutputStream(new File(ruta));
			DataOutputStream fod = new DataOutputStream(fos);
			for (Alumno alumno : alumnos) {
				fod.writeInt(alumno.getNia());
				fod.writeUTF(alumno.getNombre());
				fod.writeUTF(alumno.getApellidos());
				fod.writeChar(alumno.getGenero());
                Date nacimiento = alumno.getNacimiento();
                LocalDate localDate = nacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String formattedDate = localDate.format(formateador);
                fod.writeUTF(formattedDate);
				fod.writeUTF(alumno.getCiclo());
				fod.writeUTF(alumno.getCurso());
				fod.writeUTF(alumno.getGrupo());
				fod.flush();
			}
			fod.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
