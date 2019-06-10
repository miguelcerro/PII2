package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Supervisor extends User{
	
	/* ATRIBUTOS */
	private User[] listaPacientes;	
	
	
	/* CONSTRUCTOR */
	public Supervisor() {
		super();
		User[] listaPacientes = new User [8];
		this.listaPacientes = listaPacientes;
	}
	public Supervisor(String dniSupervisor, String nombre, String apellido1, String apellido2, String dni,
			String telefonoMovil, String telefonoCasa, String fechaNacimiento, String correo, String fotoUsuario,
			String calle, String numeroCalle, String ciudad, String provincia, String CP, String login,
			String password, String tipoUsuario) {
		super(dniSupervisor, nombre, apellido1, apellido2, dni, telefonoMovil, telefonoCasa, fechaNacimiento, correo,
				fotoUsuario, calle, numeroCalle, ciudad, provincia, CP, login, password, tipoUsuario);
		User[] listaPacientes = new User [8];
		this.listaPacientes = listaPacientes;
	}
	public Supervisor(String login, String password, String tipoUsuario) {
		super(login, password, tipoUsuario);
		User[] listaPacientes = new User [8];
		this.listaPacientes = listaPacientes;
	}
	public Supervisor(String login, String password) {
		super(login, password);
		User[] listaPacientes = new User [8];
		this.listaPacientes = listaPacientes;
	}
	
	
	/* SETTERS & GETTERS */
	public void setListaPacientes(User[] listaPacientes) {
		this.listaPacientes=listaPacientes;
	}
	public User[] getListaPacientes() {
		return listaPacientes;
	}
	
	
	/* METODOS */
	private LocalDate parseDate(String date) {
		return  LocalDate.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	/*
	public String cargarDni(String nombreUsuario) throws FileNotFoundException, IOException {
		String aux = "";
		String archCSV = "datos\\usuarios.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(archCSV));) {
			String linea;
			do {
				linea = br.readLine();
				String[] fila = linea.split(",");
				if (fila[15].equals(nombreUsuario)){
					aux=fila[4];
					return aux;
				}
			} while (linea != null || aux!="");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}*/
	/*
	public void cargarListaPacientes(String dni) {
		User[] lista = new User [8];
		User u = new User();
		int aux = 0;
		String archCSV = "datos\\usuarios.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(archCSV));) {
			String linea;
			do {
				linea = br.readLine();
				if (linea!=null) {
					String[] fila = linea.split(",");
					if (fila[0]!=null) {
						if (fila[0].equals(dni)){
							u = new User(fila[0],fila[1], fila[2], fila[3], fila[4], fila[5], fila[6],
									parseDate(fila[7]), fila[8], fila[9], fila[10], fila[11], fila[12],
									fila[13], fila[14],fila[15],fila[16], linea);
							lista[aux]=u;
							aux++;
						}
					}
				}
			} while (linea != null);
			br.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "¡Error al cargar el fichero");
			e.printStackTrace();
		}
		setListaPacientes(lista);
	}
	*/
	
	public void printListaPacientes() {
		for (int i=0; i<8; i++) {
			if (listaPacientes[i]!=null) {
				System.out.println(listaPacientes[i].getNombre()+" "+listaPacientes[i].getApellido1()+" "+listaPacientes[i].getApellido2()+" "+listaPacientes[i].getDni());
			}
			else {
				System.out.println("---");
			}
		}
	}
}
