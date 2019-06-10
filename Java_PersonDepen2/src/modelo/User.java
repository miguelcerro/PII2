package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;//Expresiones Regulares en Java
import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

public class User {

	/* DATOS PERSONALES */
	private String dniSupervisor;// (0)--> padre Spervisor
	private String nombre;// (1)
	private String apellido1;// (2)
	private String apellido2;// (3)
	private String dni;// (4)
	private String telefonoMovil;// (5)
	private String telefonoCasa;// (6)
	private String fechaNacimiento;// (7)
	private String correo;// (8)
	private String fotoUsuario;// (9)
	/* DIRECCION */
	private String calle;// (10)
	private String numeroCalle;// (11)
	private String ciudad;// (12)
	private String provincia;// (13)
	private String CP;// (14)
	/* DATOS LOGIN */
	private String login;// (15)
	private String password;// (16)
	private String tipoUsuario;// (17)

	/** Constructor de clase */
	public User() {
	}

	public User(String dniSupervisor, String nombre, String apellido1, String apellido2, String dni,
			String telefonoMovil, String telefonoCasa, String fechaNacimiento, String correo, String fotoUsuario,
			String calle, String numeroCalle, String ciudad, String provincia, String CP, String login,
			String password, String tipoUsuario) {
		super();
		this.dniSupervisor = dniSupervisor;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.telefonoMovil = telefonoMovil;
		this.telefonoCasa = telefonoCasa;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.fotoUsuario = fotoUsuario;

		this.calle = calle;
		this.numeroCalle = numeroCalle;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.CP = CP;

		this.calle = calle;
		this.login = login;
		this.password = password;
		this.tipoUsuario = tipoUsuario;

	}

	public User(String dniSupervisor, String nombre, String apellido1, String apellido2, String dni,
			String telefonoMovil, String telefonoCasa, String fechaNacimiento, String correo,
			String calle, String numeroCalle, String ciudad, String provincia, String CP, String nombreUsuario,
			String password, String tipoUsuario) {
		super();
		this.dniSupervisor = dniSupervisor;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.telefonoMovil = telefonoMovil;
		this.telefonoCasa = telefonoCasa;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.calle = calle;
		this.numeroCalle = numeroCalle;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.CP = CP;
		this.calle = calle;
		this.login = nombreUsuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

	public User(String nombreUsuario, String password) {
		this.login = nombreUsuario;
		this.password = password;

	}

	public User(String nombreUsuario, String password, String tipoUsuario) {
		this.login = nombreUsuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

	public String getDniSupervisor() {
		return dniSupervisor;
	}

	public void setDniSupervisor(String dniSupervisor) {
		this.dniSupervisor = dniSupervisor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroCalle() {
		return numeroCalle;
	}

	public void setNumeroCalle(String numeroCalle) {
		this.numeroCalle = numeroCalle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setCP(String CP) {
		this.CP = CP;
	}

	public String getCP() {
		return CP;
	}

	public String getlogin() {
		return login;
	}

	public void setlogin(String login) {
		this.login = login;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String toString() {



		return dniSupervisor + "," + nombre + "," + apellido1 + "," + apellido2 + "," + dni + "," + telefonoMovil + ","
				+ telefonoCasa + "," + ","+fechaNacimiento + "," + correo + "," + fotoUsuario + "," + calle + ","
				+ numeroCalle + "," + ciudad + "," + provincia + "," + CP + "," + login + "," + password
				+ "," + tipoUsuario;
	}

	public static boolean validPhone(String phone) {
		if (phone.length() == 9) {
			if (phone.matches("[0-9]*")) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public static boolean validDni(String dni) {
		// Comprueba la longitud
		if (dni.length() == 9) {
			String numero = dni.substring(0, 8);
			// Comprueba si los primeros 8 caracteres son un número
			if (numero.matches("[0-9]*")) {
				char[] letraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
						'V', 'H', 'L', 'C', 'K', 'E' };
				char letra = dni.charAt(8);
				int numeroDni = Integer.parseInt(numero);
				// Comprueba si la letra del DNI coincide con lo que debe ser
				if (letraDni[numeroDni % 23] == letra) {
					return true;
				} else
					return false;
			} else
				return false;
		} else
			return false;
	}

	public static boolean validCorreo(String email) {
		// patron para validar correo
		Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			// valido
			return true;
		} else {
			return false;
		}
	}

	public static boolean validTexto(String text) {
		// patron para validar texto solo letras y espacio
		Pattern pattern = Pattern.compile("^[a-zA-Z\\D]*$");
		Matcher mather = pattern.matcher(text);

		if (mather.find() == true) {
			if (text == "" || text.isEmpty()) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}
	}

	public static boolean validTipoUsuario(String tipoUsu) {
		//if (tipoUsu =="usuario" ||tipoUsu =="administrador"|| tipoUsu!="supervisor")
		if (tipoUsu.equals("usuario") ||tipoUsu.equals ("administrador")|| tipoUsu.equals("supervisor"))
		{
			return true;
		}
		else {
			return false;
		}

	}

}