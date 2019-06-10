package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Mensaje;
import modelo.Supervisor;
import modelo.User;

import java.sql.ResultSet;

import java.sql.PreparedStatement;

public class HandlerBBDD {

	private static String loginSQL = "SELECT * FROM usuarios WHERE login =?";
	private static String cargarSQL = "SELECT * FROM usuarios";
	private static String insertSQL = "INSERT INTO usuarios VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String updateSQL = "UPDATE usuarios SET (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE login = ?";
	private static String deleteSQL = "DELETE FROM usuarios WHERE login = ?";
	private static String buscarIdMensajeSQL = "SELECT * FROM relacionusuariomesaje WHERE idusuario = ?";
	private static String buscarMensajeSQL = "SELECT * FROM mensajes WHERE idmensaje = ?";
	private static String insertarMensaje = "INSERT INTO mensajes VALUES (?,?,?,?)";
	private static String insertarRelacionUsuarioMensaje = "INSERT INTO relacionusuariomesaje VALUES (?,?)";
	private static String leerListadoPacientes = "SELECT * FROM pacientes WHERE idSupervisor = ?";

	private static HandlerBBDD bbdd = null;
	private Connection conexion;

	private HandlerBBDD() {
		super();
		conexion = ConexionMysql.getConexion();

	}

	public static HandlerBBDD getBBDD() {
		if (bbdd == null)
			bbdd = new HandlerBBDD();

		return bbdd;
	}

	public User logon(String login, String pass) {
		
		User aux = null;
		try {
			PreparedStatement sentencia = conexion.prepareStatement(loginSQL);
			sentencia.setString(1, login);

			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				if (resultado.getString("password").compareTo(pass) == 0) {
					aux = new User(login, pass, resultado.getString("rol"));
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al loguear al usuario " + login, "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {

			return aux;
		}

	}

	public void insertar(User nuevoUsuario) {
		try {
			PreparedStatement sentencia = conexion.prepareStatement(insertSQL);
			sentencia.setString(1, nuevoUsuario.getNombre());

			sentencia.setString(2, nuevoUsuario.getApellido1());
			sentencia.setString(3, nuevoUsuario.getApellido2());
			sentencia.setString(4, nuevoUsuario.getDni());
			sentencia.setString(5, nuevoUsuario.getTelefonoMovil());
			sentencia.setString(6, nuevoUsuario.getTelefonoCasa());
			sentencia.setString(7, nuevoUsuario.getFechaNacimiento().toString());
			sentencia.setString(8, nuevoUsuario.getCorreo());
			sentencia.setString(9, nuevoUsuario.getFotoUsuario());
			sentencia.setString(10, nuevoUsuario.getCalle());
			sentencia.setString(11, nuevoUsuario.getNumeroCalle());
			sentencia.setString(12, nuevoUsuario.getCiudad());
			sentencia.setString(13, nuevoUsuario.getProvincia());
			sentencia.setString(14, nuevoUsuario.getCP());

			sentencia.execute();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al registrar al usuario " + nuevoUsuario.getNombre(), "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public void eliminar(User user) {
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(deleteSQL);

			sentencia.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actualiza(User user) {
		try {
			PreparedStatement sentencia = conexion.prepareStatement(loginSQL);
			sentencia.setString(1, user.getlogin());

			ResultSet resultado = sentencia.executeQuery();

			if (resultado.next()) {
				sentencia = conexion.prepareStatement(updateSQL);
				sentencia.setString(1, user.getNombre());
				sentencia.setString(2, user.getApellido1());
				sentencia.setString(3, user.getApellido2());
				sentencia.setString(4, user.getDni());
				sentencia.setString(5, user.getTelefonoMovil());
				sentencia.setString(6, user.getTelefonoCasa());
				/**/sentencia.setString(7, user.getFechaNacimiento().toString());/**/
				sentencia.setString(8, user.getCorreo());
				sentencia.setString(9, user.getFotoUsuario());
				sentencia.setString(10, user.getCalle());
				sentencia.setString(11, user.getNumeroCalle());
				sentencia.setString(12, user.getCiudad());
				sentencia.setString(13, user.getProvincia());
				sentencia.setString(14, user.getCP());
				sentencia.execute();
			} else {
				insertar(user);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al loguear al usuario " + user, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public List<User> recuperarUsuarios(){
		ArrayList<User> lista= new ArrayList();
		try {
	
		PreparedStatement sentencia = conexion.prepareStatement(cargarSQL);
	

		ResultSet resultado = sentencia.executeQuery();

		while(resultado.next()) {
			lista.add(new User(resultado.getString(4),
							resultado.getString("nombre"),
							resultado.getString("apellido1"),
							resultado.getString("apellido2"),
							resultado.getString("dni"),
							resultado.getString("telefonoMovil"),
							resultado.getString("telefonoCasa"),
							resultado.getString("fNacimiento"),//date
							resultado.getString("correo"),
							resultado.getString("img_fotousuario"),
							resultado.getString("calle"),
							resultado.getString("numeroCalle"),
							resultado.getString("ciudad"),	
							resultado.getString("provincia"),
							resultado.getString("cp"),
							resultado.getString("login"),
							resultado.getString("password")
							, resultado.getString("rol")
							));
		}
		}catch(SQLException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "Error al loguear al usuario "+ username,"Error", JOptionPane.ERROR_MESSAGE);

		}
		return lista;
	}

	public User recuperar(String username) {
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(loginSQL);
			sentencia.setString(1, username);

			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()) {

				switch(resultado.getString("rol")) {
				case "administrador":
					
				case "supervisor":

					Supervisor s= new Supervisor(resultado.getString("dni"),
							resultado.getString("nombre"),
							resultado.getString("apellido1"),
							resultado.getString("apellido2"),
							resultado.getString("dni"),
							resultado.getString("telefonoMovil"),
							resultado.getString("telefonoCasa"),
							resultado.getString("fNacimiento"),//date
							resultado.getString("correo"),
							resultado.getString("img_fotousuario"),
							resultado.getString("calle"),
							resultado.getString("numeroCalle"),
							resultado.getString("ciudad"),	
							resultado.getString("provincia"),
							resultado.getString("cp"),
							resultado.getString("login"),
							resultado.getString("password")
							, resultado.getString("rol")
							);//revisar números no coinciden con los campos

					cargarListaPacientes(s);
					return s;
				case "usuario":
					return new User(resultado.getString(4),
							resultado.getString("nombre"),
							resultado.getString("apellido1"),
							resultado.getString("apellido2"),
							resultado.getString("dni"),
							resultado.getString("telefonoMovil"),
							resultado.getString("telefonoCasa"),
							resultado.getString("fNacimiento"),//date
							resultado.getString("correo"),
							resultado.getString("img_fotousuario"),
							resultado.getString("calle"),
							resultado.getString("numeroCalle"),
							resultado.getString("ciudad"),	
							resultado.getString("provincia"),
							resultado.getString("cp"),
							resultado.getString("login"),
							resultado.getString("password")
							, resultado.getString("rol")
							);//revisar números no coinciden con los campos
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al loguear al usuario "+ username,"Error", JOptionPane.ERROR_MESSAGE);

		} 
		return null;

	}

	private void cargarListaPacientes(Supervisor s) {
		User[] lista = new User[8];
		try {
			PreparedStatement st = conexion.prepareStatement(leerListadoPacientes);
			System.out.println(s.getDni());/**/
			st.setString(1, s.getDni());
			ResultSet resultado = st.executeQuery();

			int i =0;
			while(resultado.next()) {
				lista[i]= recuperar(resultado.getString("nombrepaciente"));
				System.out.println(resultado.getString("nombrepaciente"));
				System.out.println(lista[i]);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			s.setListaPacientes(lista);
		}


	}

	public Mensaje[] leerMensajes(String dni) {
		LinkedList<Mensaje> lista = new LinkedList();

		try {
			PreparedStatement sentencia = conexion.prepareStatement(buscarIdMensajeSQL);
			sentencia.setString(1, dni);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				PreparedStatement idMensaje = conexion.prepareStatement(buscarMensajeSQL);
				idMensaje.setString(1, resultado.getString("idmensaje"));
				ResultSet mensajes = idMensaje.executeQuery();
				while (mensajes.next()) {
					lista.add(new Mensaje(mensajes.getString("fecha"), mensajes.getString("rol"),
							mensajes.getString("texto")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mensaje[] m = new Mensaje[20];
		for(int i =0; i < lista.size(); i++)
			m[i]=lista.get(i);
		for(int i = lista.size(); i<20; i++)
			m[i]= new Mensaje("","","");
		return m;
	}

	public void insertarMensaje(String fecha, User usuario, String text) {
		try {
			/**/ System.out.println("insertando" + text);
			PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM mensajes");
			int max = 0;
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				max = Math.max(max, Integer.parseInt(resultado.getString("idmensaje")));
			}
					sentencia = conexion.prepareStatement(insertarMensaje);
			sentencia.setInt(1,  (max + 1));
			sentencia.setString(2, fecha);
			sentencia.setString(3, usuario.getTipoUsuario());
			sentencia.setString(4, text);
			sentencia.execute();
			
			sentencia = conexion.prepareStatement(insertarRelacionUsuarioMensaje);
			sentencia.setInt(1, (max + 1));
			sentencia.setString(2, usuario.getDni());
			sentencia.execute();


		} catch (SQLException e) {
e.printStackTrace();
		}

	}

}
