import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BaseDatos {
	private final String CONECTAR = "jdbc:mysql://localhost:3306/alumno1585520";
	private final String USUARIO = "root";
	private final String PASSWORD = "Admin.1234";
	private Connection connection = null;
	
	public BaseDatos() {
		try {
			connection = DriverManager.getConnection(CONECTAR, USUARIO, PASSWORD);
			System.out.println("Conectado con exito!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearGrupo(Scanner sc) {
		try(PreparedStatement insert = connection.prepareStatement("INSERT INTO GRUPO (cod_grupo, nombre) VALUES (?, ?);")) {
			insert.setInt(1, readInt(sc, "Dame la clave primaria del grupo (int):"));
			insert.setString(2, readString(sc, "Dame el nombre del grupo"));
			insert.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearGrupo(int cod, String nombre) {
		System.out.println(cod);
		System.out.println(nombre);
		try(PreparedStatement insert = connection.prepareStatement("INSERT INTO GRUPO (cod_grupo, nombre) VALUES (?, ?);")) {
			insert.setInt(1, cod);
			insert.setString(2, nombre);
			insert.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createAlumno(Scanner sc, int nia, String nombre, String apellido, String genero, Date fecha, String ciclo, String curso, int grupo) {
		try(PreparedStatement insert = connection.prepareStatement("INSERT INTO ALUMNO (nia, nombre, apellidos, genero, fecha_nacimiento, ciclo, curso, fk_grupo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);")) {
			insert.setInt(1, nia);
			insert.setString(2, nombre);
			insert.setString(3, apellido);
			insert.setString(4, genero);
			insert.setDate(5, fecha);
			insert.setString(6, ciclo);
			insert.setString(7, curso);
			insert.setInt(8, grupo);
			insert.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getDatosAlumnos() {
		List<String> datos = new ArrayList<String>();
		try (ResultSet result = connection.createStatement().executeQuery("SELECT nia, nombre, apellidos, genero, fecha_nacimiento, ciclo, curso, fk_grupo FROM ALUMNO;")) {
			while(result.next()) {
				String linea = "";
				linea += result.getInt("nia") + " ";
				linea += result.getString("nombre") + " ";
				linea += result.getString("apellidos") + " ";
				linea += result.getString("genero") + " ";
				linea += result.getDate("fecha_nacimiento").toString() + " ";
				linea += result.getString("ciclo") + " ";
				linea += result.getString("curso") + " ";
				linea += result.getString("fk_grupo");
				datos.add(linea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	public List<String> getDatosGrupo() {
		List<String> datos = new ArrayList<String>();
		try (ResultSet result = connection.createStatement().executeQuery("SELECT cod_grupo, nombre FROM GRUPO;")) {
			while(result.next()) {
				String linea = "";
				linea += result.getInt("cod_grupo") + " ";
				linea += result.getString("nombre");
				datos.add(linea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	public void mostrarAlumnos() {
		List<String> datos = getDatosAlumnos();
		for (String linea : datos) {
			System.out.print(linea);
			String[] array = linea.split(" ");
			try(ResultSet result = connection.createStatement().executeQuery("SELECT nombre FROM GRUPO WHERE cod_grupo = " + array[array.length - 1] + ";")) {
				while(result.next()) {
					System.out.println(" " + result.getString("nombre"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarEnFichero() {
		List<String> datos = getDatosAlumnos();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt"))) {
			for (String linea : datos) {
				bw.write(linea);
				bw.newLine();
			}
			System.out.println("Datos guardados!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void leerDeFichero(Scanner sc) {
		try(BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
			String linea;
			while((linea = br.readLine()) != null) {
				String[] datos = linea.split(" ");
				createAlumno(
						sc,
						Integer.parseInt(datos[0]),
						datos[1],
						datos[2],
						datos[3],
						java.sql.Date.valueOf(datos[4]),
						datos[5],
						datos[6],
						Integer.parseInt(datos[7]));
			}
			System.out.println("Datos leidos!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificarAlumno(Scanner sc) {
		try(PreparedStatement update = connection.prepareStatement("UPDATE ALUMNO SET nombre = ? WHERE nia = ?;")){
			update.setString(1, readString(sc, "Dame el nuevo nombre"));
			update.setInt(2, readInt(sc, "Dame la clave pirmaria"));
			update.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarGrupos() {
		try(ResultSet result = connection.createStatement().executeQuery("SELECT cod_grupo, nombre FROM GRUPO;")) {
			while(result.next()) {
				System.out.println(result.getInt("cod_grupo") + " " + result.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarAlumno(Scanner sc) {
		try(PreparedStatement delete = connection.prepareStatement("DELETE FROM ALUMNO WHERE nia = ?;")){
			delete.setInt(1, readInt(sc, "Dame la clave pirmaria"));
			delete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarAlumnoAPartirDeGrupo(Scanner sc) {
		try(PreparedStatement delete = connection.prepareStatement("DELETE FROM ALUMNO WHERE fk_grupo = ?;")){
			delete.setInt(1, readInt(sc, "Dame la clave del grupo"));
			delete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void leerDeXml(Xml xml, Scanner sc) {
		NodeList elements = xml.doc.getDocumentElement().getElementsByTagName("grupo");
		for (int i = 0; i < elements.getLength(); i++) {
			Element grupo = (Element) elements.item(i);
			crearGrupo(Integer.parseInt(grupo.getAttribute("cod_grupo")), grupo.getAttribute("nombre"));
			NodeList alumnos = grupo.getElementsByTagName("alumno");
			for (int j = 0; j < alumnos.getLength(); j++) {
				Element alumno = (Element) alumnos.item(j);
				createAlumno(sc,
						Integer.parseInt(alumno.getAttribute("nia")),
						alumno.getAttribute("nombre"),
						alumno.getAttribute("apellidos"),
						alumno.getAttribute("genero"),
						java.sql.Date.valueOf(alumno.getAttribute("fecha_nacimiento")),
						alumno.getAttribute("ciclo"),
						alumno.getAttribute("curso"),
						Integer.parseInt(alumno.getAttribute("fk_grupo")));
			}
		}
	}
	
	public String readString(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		return sc.nextLine();
	}
	
	public int readInt(Scanner sc) {
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
	
	public int readInt(Scanner sc, String mensaje) {
		System.out.println(mensaje);
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
	
	public void close() {
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
