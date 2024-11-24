import java.util.Scanner;

public class Main {
	private static final String[] OPCIONES = {
		"Insertar alumno",
		"Insertar grupo",
		"Mostrar alumnos",
		"Guardar en fichero",
		"Leer de fichero",
		"Modificar alumno por PK",
		"Eliminar alumno por PK",
		"Eliminar alumno de grupo, se mostraran los grupos",
		"Guardar grupos en XML con sus alumnos",
		"Leer XML",
		"Salir"
	};
	
	public static void main(String[] args) {
		System.out.println("ALUMNOS DB:");
		hr();
		BaseDatos bd = new BaseDatos();
		Xml xml = new Xml();
		hr();
		menu(bd, xml);
		bd.close();
	}
	private static void menu(BaseDatos bd, Xml xml) {
		Scanner sc = new Scanner(System.in);
		int seleccion = 0;
		do {
			System.out.println("Selecciona una opcion:");
			mostrarOpciones();
			seleccion = bd.readInt(sc);
			hr();
			switch(seleccion) {
			case 1:
				bd.createAlumno(sc,
						bd.readInt(sc, "Dame la clave primaria (int):"),
						bd.readString(sc, "Dame el nombre"),
						bd.readString(sc, "Dame el apellido"),
						bd.readString(sc, "Dame el genero"),
						java.sql.Date.valueOf(bd.readString(sc, "Dame la fecha")),
						bd.readString(sc, "Dame el ciclo"),
						bd.readString(sc, "Dame el curso"),
						bd.readInt(sc, "Dame la clave del grupo (int):"));
				hr();
				break;
			case 2:
				bd.crearGrupo(sc);
				hr();
				break;
			case 3:
				bd.mostrarAlumnos();
				hr();
				break;
			case 4:
				bd.guardarEnFichero();
				hr();
				break;
			case 5:
				bd.leerDeFichero(sc);
				hr();
				break;
			case 6:
				bd.modificarAlumno(sc);
				hr();
				break;
			case 7:
				bd.eliminarAlumno(sc);
				hr();
				break;
			case 8:
				bd.mostrarGrupos();
				bd.eliminarAlumnoAPartirDeGrupo(sc);
				hr();
				break;
			case 9:
				xml.guardarEnXml(bd.getDatosGrupo(), bd.getDatosAlumnos());
				xml.escribirArchivoXml();
				hr();
				break;
			case 10:
				xml.chuparDeArchivo();
				bd.leerDeXml(xml, sc);
				hr();
				break;
			case 11:
				System.out.println("Bye!");
				break;
			default:
				System.out.println("Seleccion invalida");
				hr();
				break;
			}
		} while (seleccion != 11);
		sc.close();
	}
	
	private static void mostrarOpciones() {
		for (int i = 0; i < OPCIONES.length; i++) {
			String opcion = OPCIONES[i];
			System.out.println("  " + (i + 1) + ": " + opcion);
		}
	}
	
	private static void hr() {
		System.out.println("---------------------------------");
	}
}
