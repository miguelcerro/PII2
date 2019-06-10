package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import Database.HandlerBBDD;
import modelo.User;

/* Compara datos 16 y 17 de fichero con datos login*/
public class Authentication {

	public User ComprobarDatosLogin(String nombreUsuario, String pass) {
		
		return HandlerBBDD.getBBDD().logon(nombreUsuario, pass);
		
		
	}
/**
	User u = null;

	@SuppressWarnings("finally")
	public User ComprobarDatosLogin(String nombreUsuario, String pass) throws IOException, NullPointerException {
		String archCSV = "datos\\usuarios.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(archCSV));) {
			String linea;
			do {
				linea = br.readLine();
				String[] fila = linea.split(",");
				if (fila[15].equals(nombreUsuario) && fila[16].equals(pass)) {
					u = new User(fila[15], fila[16], fila[17]);
					return u;
				}

			} while (linea != null);
			br.close();
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "¡Error al cargar el fichero");
			e.printStackTrace();
		} finally {// fuerza el retorno null si no encuentra el usuario
			return u;
		}

	}
	**/
}
