package apartado_a;

import java.util.Scanner;

public class Main {
	static final int NUM_ALUMNS = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ruta;
		ruta = pedirRuta(sc, "Dame la ruta del fichero.");
		addAlumnos(sc, ruta);
		sc.close();
	}

	public static String pedirRuta(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		return sc.nextLine();
	}

	public static void addAlumnos(Scanner sc, String ruta) {
		for (int i = 0; i < NUM_ALUMNS; i++) {
			Alumno alumno = new Alumno(
					leerEntero(sc, "Dime el NIA"),
					"",
					"",
					'1',
					null,
					"",
					"",
					""
					);
			escribirAlumno(alumno, ruta);
		}
	}

	public static int leerEntero(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
	
	public static void escribirAlumno(Alumno alumno, String ruta) {
		
	}
}
