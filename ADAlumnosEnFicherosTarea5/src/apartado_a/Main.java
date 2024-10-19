package apartado_a;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
	static final int NUM_ALUMNS = 5;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ruta;
		ruta = pedirRuta(sc, "Dame la ruta del fichero.");
		System.out.println();
		addAlumnos(sc, ruta);
		sc.close();
	}

	public static String pedirRuta(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		return sc.nextLine();
	}

	public static void addAlumnos(Scanner sc, String ruta) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(ruta);
			oos = new ObjectOutputStream(fos);
			escribirAlumno(sc, oos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				oos.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static int leerEntero(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
	
	public static String leerTexto(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		return sc.nextLine();
	}
	
    public static LocalDate convertirFecha(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-yyyy");
        return LocalDate.parse(fecha, formato);
    }
	
	public static void escribirAlumno(Scanner sc, ObjectOutputStream oos) throws Exception {
		for (int i = 0; i < NUM_ALUMNS; i++) {
			Alumno alumno = new Alumno(
					leerEntero(sc, "Dime el NIA"),
					leerTexto(sc, "Dime el Nombre"),
					leerTexto(sc, "Dime el Apellido"),
					leerTexto(sc, "Dime el Genero").charAt(0),
					convertirFecha(leerTexto(sc, "Dame una fecha (d-M-yyyy)")),
					leerTexto(sc, "Dime el Ciclo"),
					leerTexto(sc, "Dime el Curso"),
					leerTexto(sc, "Dime el Grupo")
					);
			oos.writeObject(alumno);
			System.out.println();
		}
	}
}
