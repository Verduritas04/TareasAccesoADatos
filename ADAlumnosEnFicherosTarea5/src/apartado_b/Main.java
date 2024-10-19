package apartado_b;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import apartado_a.Alumno;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ruta;
		ruta = pedirRuta(sc, "Dame la ruta del fichero.");
		System.out.println();
		leerAlumnos(ruta);
		sc.close();
	}

	public static String pedirRuta(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		return sc.nextLine();
	}
	
	public static void leerAlumnos(String ruta) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(ruta);
			ois = new ObjectInputStream(fis);
			while(fis.available() > 0) {
				Alumno alumno = (Alumno) ois.readObject();
				System.out.println(alumno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ois.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
