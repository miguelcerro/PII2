package controlador;

import modelo.User;

import javax.swing.JOptionPane;
import com.opencsv.CSVReader;

import Database.HandlerBBDD;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdministradorController<T> implements Initializable {

	// Definicion de campos de graficos que vamos controlar
	@FXML
	private AnchorPane menuAdm_1_AnchorPane;
	@FXML
	private SplitPane menuAdm_1_1_SplitPane;
	@FXML
	private AnchorPane menuAdm_1_1_1_AnchorPane;
	@FXML
	private TextField txt_Filtro;
	@FXML
	private TabPane menuAdm_1_1_1_1_TabPane;
	@FXML
	private Tab menuAdm_1_1_1_1_1_Tab_ListUsu;
	@FXML

	/* Campos de la tableview */
	private TableView<User> Tbl_Tab_ListUsu_Admin;// Añadimos la clase de user
	@FXML
	private TableColumn<User, String> TblCol_Tab_ListUsu_Admin_Rol;// Añadimos la clase de user + tipo dato
	@FXML
	private TableColumn<User, String> TblCol_Tab_ListUsu_Admin_Nombre;
	@FXML
	private TableColumn<User, String> TblCol_Tab_ListUsu_Admin_Apellido1;
	@FXML
	private TableColumn<User, String> TblCol_Tab_ListUsu_Admin_Apellido2;
	@FXML
	private TableColumn<User, String> TblCol_Tab_ListUsu_Admin_Dni;

	/* Campos de la pestaña de Detalle */
	@FXML
	private Tab menuAdm_1_1_1_1_2_Tab_DetUsu;

	@FXML
	private TextField txt_aux;
	@FXML
	private TextField txt_Tab_Det_Nombre;
	@FXML
	private TextField txt_Tab_Det_Apellido1;
	@FXML
	private TextField txt_Tab_Det_Apellido2;
	@FXML
	private TextField txt_Tab_Det_DNI;
	@FXML
	private TextField txt_Tab_Det_telefonoMovil;
	@FXML
	private TextField txt_Tab_Det_telefonoCasa;
	@FXML
	private TextField txt_Tab_Det_fechaNacimiento;
	@FXML
	private TextField Date_Tab_Det_fechaNacimiento;
	@FXML
	private TextField txt_Tab_Det_correo;
	@FXML
	private Button btn_Adm_FotoUsuario;

	@FXML
	private TextField txt_Tab_Det_calle;
	@FXML
	private TextField txt_Tab_Det_numeroCalle;
	@FXML
	private TextField txt_Tab_Det_ciudad;
	@FXML
	private TextField txt_Tab_Det_provincia;
	@FXML
	private TextField txt_Tab_Det_CP;

	@FXML
	private TextField txt_Tab_Det_Id;// DNI del Supervisor aociado al Paciente
	@FXML
	private TextField txt_Tab_Det_NombreSuper;
	@FXML
	private TextField txt_Tab_Det_ApellidoSuper;
	@FXML
	private TextField txt_Tab_Det_Apellido2Super;
	@FXML
	private TextField txt_Tab_Det_login;
	@FXML
	private TextField txt_Tab_Det_password;
	@FXML
	private TextField txt_Tab_Det_tipoUsuario;
	@FXML
	private ComboBox<String> cbo_Tab_Det_tipoUsuario;

	/* Campos de la Panel derecho con los Button */
	@FXML
	private AnchorPane menuAdm_1_1_2_AnchorPane;
	@FXML
	private Button btn_Adm_RetrocederUsuario;
	@FXML
	private Button btn_Adm_AvanzarUsuario;
	@FXML
	private Button btn_Adm_NuevoUsuario;
	@FXML
	private Button btn_Adm_ModificarUsuario;
	@FXML
	private Button btn_Adm_BorrarUsuario;
	@FXML
	private Button btn_Adm_Salir;

	@FXML
	private Button btn_Adm_AniadirUsuario;
	@FXML
	private Button btn_Adm_Volver;

	@FXML
	private Button btn_ListUsu_Salir;
	@FXML
	private TextField Txt_Adm_Nombre;
	@FXML
	private Label lbl_filtro;
	@FXML
	private Label lbl_CamposObligatorios;
	/* Imagenes de iconos dentro de los Button */
	@FXML
	private ImageView img_Adm_Usuario;
	@FXML
	private ImageView img_Adm_Retroceder;
	@FXML
	private ImageView img_Adm_avanzar;
	@FXML
	private ImageView img_Adm_NuevoUsuario;
	@FXML
	private TextField txt_NombreFoto;
	@FXML
	private ImageView img_Adm_ModificarUsuario;
	@FXML
	private ImageView img_Adm_BorrarUsuario;

	@FXML
	private ImageView img_Adm_Volver;
	@FXML
	private ImageView img_Adm_Salir;

	private ObservableList<User> listadoUsuarios = FXCollections.observableArrayList(); // para añadir datos a la tabla
	String archCSV = "datos\\usuarios.csv";// archivo de usuarios
	private int posicionUsuarioEnTabla=0;
	private int tamañoTableView;
	String pestañaActiva;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		txt_aux.setText("0");
		filtro(); // Inicializacion relleno datos en tabla y ponemos filtro a funcionar
		
	
		/* Rellenamos el combox de Tipo de Usuario */
		cbo_Tab_Det_tipoUsuario.getItems().add("usuario");
		cbo_Tab_Det_tipoUsuario.getItems().add("administrador");
		cbo_Tab_Det_tipoUsuario.getItems().add("supervisor");
		// cbo_Tab_Det_tipoUsuario.getItems().add("familiar");

		// Inicializa la vision de los botones
		txt_Filtro.setVisible(true);
		lbl_filtro.setVisible(true);
		lbl_CamposObligatorios.setVisible(false);

		txt_aux.setVisible(false);
		txt_NombreFoto.setVisible(false);
		img_Adm_Usuario.setVisible(true);
		btn_Adm_RetrocederUsuario.setVisible(true);
		btn_Adm_AvanzarUsuario.setVisible(true);
		btn_Adm_NuevoUsuario.setVisible(true);
		btn_Adm_ModificarUsuario.setVisible(true);
		btn_Adm_BorrarUsuario.setVisible(true);
		img_Adm_BorrarUsuario.setVisible(true);
		btn_Adm_Salir.setVisible(true);
		img_Adm_Salir.setVisible(true);

		btn_Adm_AniadirUsuario.setVisible(false);
		btn_Adm_Volver.setVisible(false);

		txt_Tab_Det_NombreSuper.setDisable(true);
		txt_Tab_Det_ApellidoSuper.setDisable(true);
		txt_Tab_Det_Apellido2Super.setDisable(true);

//		cargarCsv(archCSV);// --> Lectura de fichero de datos
cargar();
	}

	/*------------------------------------------------------------------------*/
	/* ----------------METODOS DE BOTONES, TAB Y TEXTFIELD------------------- */
	/*------------------------------------------------------------------------*/

	// Event Listener on Button[RETROCEDER].onAction
	@FXML
	public void btn_Adm_RetrocederUsuario_Action(ActionEvent event) {
		tamañoTableView = Tbl_Tab_ListUsu_Admin.getItems().size();// tamaño de tabla

		// saca la posicion activa en la tabla listado usuarios
		posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();

		posicionUsuarioEnTabla = posicionUsuarioEnTabla - 1;

		// Cuando llega al final de la tabla pasa al principio
		if (posicionUsuarioEnTabla < 0) {
			posicionUsuarioEnTabla = tamañoTableView - 1;
		} // si llega al principio se pone al final
		Tbl_Tab_ListUsu_Admin.getSelectionModel().select(posicionUsuarioEnTabla);// seleccion la fila que queremos en la
		txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));	
		rellenarDatosDetalleUsuario(posicionEnArray()); // rellena pestaña Detalle con posicion dentro tabla
														// listArray:ListadoUsuarios
	}																		

	// Event Listener on Button[AVANZAR].onAction
	@FXML
	public void btn_Adm_AvanzarUsuario_Action(ActionEvent event) {
		tamañoTableView = Tbl_Tab_ListUsu_Admin.getItems().size();// tamaño de tabla

		posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();
		posicionUsuarioEnTabla = posicionUsuarioEnTabla + 1;

		// Cuando llega al final de la tabla pasa al principio
		if (posicionUsuarioEnTabla >= tamañoTableView) {
			posicionUsuarioEnTabla = 0;
		}
		Tbl_Tab_ListUsu_Admin.getSelectionModel().select(posicionUsuarioEnTabla);// seleccion la fila que queremos en la
		rellenarDatosDetalleUsuario(posicionEnArray());// rellena la pestaña Detalle con la posicion dentro de la
														// listArray:ListadoUsuarios


		txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));	
	}

	// Event Listener on Button[NUEVO USUARIO].onAction
	@FXML
	public void btn_Adm_NuevoUsuario_Action(ActionEvent event) throws Exception {
		menuAdm_1_1_2_AnchorPane.setVisible(true);
		txt_Filtro.setVisible(false);
		lbl_filtro.setVisible(false);

		btn_Adm_RetrocederUsuario.setVisible(false);
		btn_Adm_AvanzarUsuario.setVisible(false);
		btn_Adm_NuevoUsuario.setVisible(false);
		btn_Adm_ModificarUsuario.setVisible(false);
		btn_Adm_BorrarUsuario.setVisible(false);
		btn_Adm_Salir.setVisible(false);

		btn_Adm_AniadirUsuario.setText("Añadir");
		btn_Adm_AniadirUsuario.setVisible(true);
		btn_Adm_Volver.setVisible(true);

		menuAdm_1_1_1_1_1_Tab_ListUsu.setDisable(true);
		menuAdm_1_1_1_1_TabPane.getSelectionModel().select(menuAdm_1_1_1_1_2_Tab_DetUsu);// Toma foco tab Detalle

		menuAdm_1_1_1_1_2_Tab_DetUsu.setDisable(false);
		menuAdm_1_1_1_1_2_Tab_DetUsu.setText("Nuevo Usuario");
		limpiarDatosDetalleUsuario();
		
		//cargamos imagen de icono
		Image image = new Image("/_imagenes/001.png");
		img_Adm_Usuario.setImage(image);
		txt_NombreFoto.setText("/_imagenes/001.png");	
	}

	// Event Listener on Button[AÑADIR/MODIFICAR USUARIO].onAction
	@FXML
	public void btn_Adm_AniadirUsuario_Action(ActionEvent event) throws Exception {
		User nuevoUsuario = new User();

		/* comprueba si los nombres y apellidos son Validos */
		if (nuevoUsuario.validTexto(txt_Tab_Det_Nombre.getText()) &&
				nuevoUsuario.validTexto(txt_Tab_Det_Apellido1.getText())&&
				nuevoUsuario.validTexto(txt_Tab_Det_Apellido2.getText())) {
		
		/* comprueba si el dni es Valido */
		if (nuevoUsuario.validDni(txt_Tab_Det_DNI.getText())) {

			/* comprueba si los telefonosson Validos */
			if (nuevoUsuario.validPhone(txt_Tab_Det_telefonoCasa.getText())
					|| nuevoUsuario.validPhone(txt_Tab_Det_telefonoMovil.getText())) {
				
				/* comprueba si los correos son Validos */
				if (nuevoUsuario.validCorreo(txt_Tab_Det_correo.getText())) {
			
					/* comprueba si los el tipo usuario son Validos */
					if (nuevoUsuario.validTipoUsuario(cbo_Tab_Det_tipoUsuario.getValue())) {

					/* comprueba si los el el supervisor es valido */
						if (!cbo_Tab_Det_tipoUsuario.getValue().equals("usuario") ||
								asignaSuper()==true) {
							/* comprueba que el num pacientes asociad a super es <8s */
							if (numeroPacientes()<8) {
													
							/* Toma los valores delos Campos y los mete en el nuevo Usuario */
						nuevoUsuario.setDniSupervisor(txt_Tab_Det_Id.getText());
						nuevoUsuario.setNombre(txt_Tab_Det_Nombre.getText());
						nuevoUsuario.setApellido1(txt_Tab_Det_Apellido1.getText());
						nuevoUsuario.setApellido2(txt_Tab_Det_Apellido2.getText());
						nuevoUsuario.setDni(txt_Tab_Det_DNI.getText());
						nuevoUsuario.setTelefonoMovil(txt_Tab_Det_telefonoMovil.getText());
						nuevoUsuario.setTelefonoCasa(txt_Tab_Det_telefonoCasa.getText());
						nuevoUsuario.setFechaNacimiento(Date_Tab_Det_fechaNacimiento.getText());
						nuevoUsuario.setCorreo(txt_Tab_Det_correo.getText());
						nuevoUsuario.setFotoUsuario(txt_NombreFoto.getText());
		
						nuevoUsuario.setCalle(txt_Tab_Det_calle.getText());
						nuevoUsuario.setNumeroCalle(txt_Tab_Det_numeroCalle.getText());
						nuevoUsuario.setCiudad(txt_Tab_Det_ciudad.getText());
						nuevoUsuario.setProvincia(txt_Tab_Det_provincia.getText());
						nuevoUsuario.setCP(txt_Tab_Det_CP.getText());
		
						nuevoUsuario.setlogin(txt_Tab_Det_login.getText());
						nuevoUsuario.setpassword(txt_Tab_Det_password.getText());
						nuevoUsuario.setTipoUsuario(cbo_Tab_Det_tipoUsuario.getValue());

				/* Añade el nuevo usuario al listado al Array */

						if (menuAdm_1_1_1_1_2_Tab_DetUsu.getText().equals("Nuevo Usuario")) { // Añadir Usuario
							listadoUsuarios.add(nuevoUsuario);
					/**/		HandlerBBDD.getBBDD().insertar(nuevoUsuario);
							tamañoTableView = Tbl_Tab_ListUsu_Admin.getItems().size();// tamaño de tabla

						/* Modificar los datos del usuario en el Array */
					} else {
						if (posicionUsuarioEnTabla == -1) {
							posicionUsuarioEnTabla = 0;
						}
//**/
						HandlerBBDD.getBBDD().actualiza(nuevoUsuario);
						listadoUsuarios.set(posicionEnArray(), nuevoUsuario);
					}
				/* Vuelve a Pestaña de Listado de Usuarios */
				btn_Adm_Volver_Action(event);
				
							} else {
								JOptionPane.showMessageDialog(null, "El Supervisor tiene " + numeroPacientes()+
										"pacientes asignados\n Elija otro Supervisor");
							}
							
				
						} else {
							//JOptionPane.showMessageDialog(null, "Debe especificar DNI Super");
						}
						
						} else {
						JOptionPane.showMessageDialog(null, "Debe especificar un Tipo de Usuario");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe especificar un Email Correcto\n Vuelva a Introducirlo de nuevo");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe especificar un Numero de telefono Correcto\n Vuelva a Introducirlo de nuevo");
			}
		} else {

			JOptionPane.showMessageDialog(null, "DNI Incorrecto \n Vuelva a Introducirlo de nuevo");
		}
		} else {

			JOptionPane.showMessageDialog(null, "Nombre o Apellidos Incorrecto \n Vuelva a Introducirlo de nuevo");
			
		}
		posicionUsuarioEnTabla=	Integer.parseInt(txt_aux.getText());
		
	}

	// Event Listener on Button[BORRAR USUARIO].onAction
	@FXML
	public void btn_Adm_BorrarUsuario_Action(ActionEvent event) throws Exception {
		posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();
		String pestañaActiva = menuAdm_1_1_1_1_TabPane.getSelectionModel().getSelectedItem().getText();

		/* Fuerza la seleccion de una fila */
		if (posicionUsuarioEnTabla != -1) {
			/* En caso de estar en Pestaña detalle */
			if (pestañaActiva.equals("Detalle Usuario")) {
				rellenarDatosDetalleUsuario(posicionEnArray());
				/**/ HandlerBBDD.getBBDD().eliminar(listadoUsuarios.get(posicionEnArray()));
				listadoUsuarios.remove(posicionEnArray());
				if (posicionUsuarioEnTabla == 0) {
					posicionUsuarioEnTabla = listadoUsuarios.size();
				}

				rellenarDatosDetalleUsuario(posicionEnArray());

			} else {
				/* Borra el usuario del array */
				listadoUsuarios.remove(posicionEnArray());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe especificar un Usuario");
		}
		;
		txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));	
	}

	// Event Listener on Button[MODIFICAR USUARIO].onAction
	@FXML
	public void btn_Adm_ModificarUsuario_Action(ActionEvent event) throws Exception {

		/* Formateo de interface y envio a Añadir/Modificar usuario */

		posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();
		String pestañaActiva = menuAdm_1_1_1_1_TabPane.getSelectionModel().getSelectedItem().getText();

		/* Si esta en pestaña listado cambia a pestaña Detalle */
		if (pestañaActiva.equals("Listado Usuarios")) {
			/* si hay una fila sellecionada hace formatea Interface */
			if (posicionUsuarioEnTabla != -1) {
				menuAdm_1_1_1_1_TabPane.getSelectionModel().select(menuAdm_1_1_1_1_2_Tab_DetUsu);// Toma foco tab Detll
				
				txt_Filtro.setVisible(false);
				lbl_filtro.setVisible(false);
				lbl_CamposObligatorios.setVisible(true);
				
				btn_Adm_RetrocederUsuario.setVisible(false);
				btn_Adm_AvanzarUsuario.setVisible(false);
				btn_Adm_NuevoUsuario.setVisible(false);
				btn_Adm_ModificarUsuario.setVisible(false);
				btn_Adm_BorrarUsuario.setVisible(false);
				btn_Adm_Salir.setVisible(false);

				btn_Adm_AniadirUsuario.setText("Actualizar");

				btn_Adm_AniadirUsuario.setVisible(true);
				btn_Adm_Volver.setVisible(true);

				menuAdm_1_1_1_1_1_Tab_ListUsu.setDisable(true);
				menuAdm_1_1_1_1_TabPane.getSelectionModel().select(menuAdm_1_1_1_1_2_Tab_DetUsu);// Toma foco tab Detll

				menuAdm_1_1_1_1_2_Tab_DetUsu.setDisable(false);
				menuAdm_1_1_1_1_2_Tab_DetUsu.setText("Modificar Usuario");
			} else {
				JOptionPane.showMessageDialog(null, "Debe especificar un Usuario");
			}
			/* en el caso de estar en Pestaña Detalle va a Añadir usuario */
		} else {
			/* Fuerza a que este una Fila selleccionada */
			if (posicionUsuarioEnTabla == -1) {
				posicionUsuarioEnTabla = 0;
			}

			btn_Adm_AniadirUsuario_Action(event);
			Tab_ListUsu_SelectionChanged(event);
		}
		txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));
	}

	// Event Listener on Button[VOLVER].onAction
	@FXML
	public void btn_Adm_Volver_Action(ActionEvent event) throws Exception {
		/* Vuelve a lPestaña de Listado usuario despues de Borrar/Modificar */
		posicionUsuarioEnTabla=	Integer.parseInt(txt_aux.getText());
		menuAdm_1_1_1_1_TabPane.getSelectionModel().select(menuAdm_1_1_1_1_1_Tab_ListUsu);// Toma foco tab Dtalle

		// Cambio botones visibles <-> invisibles
		txt_Filtro.setVisible(true);
		lbl_filtro.setVisible(true);
		lbl_CamposObligatorios.setVisible(false);
		
		btn_Adm_RetrocederUsuario.setVisible(true);
		btn_Adm_AvanzarUsuario.setVisible(true);
		btn_Adm_NuevoUsuario.setVisible(true);
		btn_Adm_ModificarUsuario.setVisible(true);
		btn_Adm_BorrarUsuario.setVisible(true);
		btn_Adm_Salir.setVisible(true);

		btn_Adm_AniadirUsuario.setVisible(false);
		btn_Adm_Volver.setVisible(false);

		menuAdm_1_1_1_1_1_Tab_ListUsu.setDisable(false);// activar tab lista usuarios
		menuAdm_1_1_1_1_2_Tab_DetUsu.setText("Detalle Usuario");// cambiar nombre de tab
		
	}

	// Event Listener on Button[SALIR].onAction
	@FXML
	public void btn_ListUsu_Salir_Action(ActionEvent event) throws Exception {
		/* Guarda los datos en el archivo y salida del programa */
		//guardarCSV(archCSV, listadoUsuarios);
		System.exit(0);
	}

	// Event Listener on Tab [DETALLE USUARIO].onSelectionChanged
	@FXML
	public void Tab_DetUsu_SelectionChanged(Event event) {

		posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();

		if (menuAdm_1_1_1_1_TabPane.getSelectionModel().getSelectedItem().isSelected()) {// si el TAB esta seleccionada
			/* Fuerza a que este seleccionada una fila */
			if (posicionUsuarioEnTabla == -1) {
				posicionUsuarioEnTabla = 0;
			}
			/* Desactiva el filtro */
			txt_Filtro.setVisible(false);
			lbl_filtro.setVisible(false);

			/* Rellena los campos */
			Tbl_Tab_ListUsu_Admin.getSelectionModel().select(posicionUsuarioEnTabla);// seleccion de fila
			rellenarDatosDetalleUsuario(posicionEnArray());
		}
		txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));
	}
	// Event Listener on Tab [LISTADO USUARIOS].onSelectionChanged
	@FXML
	public void Tab_ListUsu_SelectionChanged(Event event) {
		try {
	posicionUsuarioEnTabla=Integer.parseInt(txt_aux.getText());
		} catch(NullPointerException e) {
			e.printStackTrace();
		}/**/
		
		if (menuAdm_1_1_1_1_TabPane.getSelectionModel().getSelectedItem().isSelected()) {// si el TAB esta seleccionada
			/* Fuerza a que este seleccionada una fila */
			if (posicionUsuarioEnTabla == -1) {
				posicionUsuarioEnTabla = 0;
			}
		
			/* Activa el filtro */
			menuAdm_1_1_1_AnchorPane.setVisible(true);
			txt_Filtro.setVisible(true);
			lbl_filtro.setVisible(true);

			txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));			
		}
	}
	
	// Event Listener on TableView[1/2 CLIK SOBRE TABLA].onMouseClicked
	@FXML
	public void Tbl_Tab_ListUsu_Admin_Click(MouseEvent event) {// un click
		/* Con 1 Click sobre fila rellena la tabla detalle */
		posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();
		txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));	
		rellenarDatosDetalleUsuario(posicionEnArray());
		/* Con 2 Click sobre fila cambia a pestaña detalle rellena */
		if (event.getClickCount() == 2) { // doble click
			posicionUsuarioEnTabla = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedIndex();
			rellenarDatosDetalleUsuario(posicionEnArray());			
			menuAdm_1_1_1_1_TabPane.getSelectionModel().select(menuAdm_1_1_1_1_2_Tab_DetUsu);// Toma foco tab Detalle
			/* Desactiva el filtro */
			txt_Filtro.setVisible(false);
			lbl_filtro.setVisible(false);
			txt_aux.setText(Integer.toString(posicionUsuarioEnTabla));
		}
	}

	// Event Listener on TextField[DNI USUARIO].onAction
	// Event Listener on TextField[DNI USUARIO].onAction
	@FXML
	public void txt_Tab_Det_DNI_Action(ActionEvent event) {
		User nuevoUsuario = new User();

		/* comprueba si el dni es Valido */
		if (!nuevoUsuario.validDni(txt_Tab_Det_DNI.getText())) {
			JOptionPane.showMessageDialog(null, "DNI Incorrecto \n Vuelva a Introducirlo de nuevo");
		}
	}
	
	// Event Listener on Button[BOTON PARA AÑADIR FOTO USUARIO].onAction	

	// Event Listener on Button[BOTON PARA AÑADIR FOTO USUARIO].onAction
	@FXML
	public void btn_Adm_FotoUsuario_Action(ActionEvent event) throws IOException {
		/* se crea una ventana para poder Insertar el objeto Filechooser */
		Stage stage = null;
		FXMLLoader loader = new FXMLLoader();

		InputStream in = LoginController.class.getResourceAsStream("/vista/Ventana_JFileChooser.fxml");
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(LoginController.class.getResource("/vista/Ventana_JFileChooser.fxml"));

		AnchorPane page1 = null;
		try {
			page1 = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}

		/* Cargamos el objeto de Buscar archivo explorador */

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");

		/* Agregar filtros para facilitar la busqueda */
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

		/* Obtener la imagen seleccionada */
		File imgFile = fileChooser.showOpenDialog(stage);

		/* Mostar la imagen seleccionada */
		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getPath());
			img_Adm_Usuario.setImage(image);
		}
		/* Copiamos la foto elegida a carpeta de imagenes */
		/* Path Origen y destino */
		Path FROM = Paths.get(imgFile.getPath());
		Path TO = Paths.get(System.getProperty("user.dir") + // Directorio de trabajo eclipse
				"\\src\\_imagenes\\" + imgFile.getName());// Resto donde se quiere guardar

		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING, // Reemplaza exist si existe
				StandardCopyOption.COPY_ATTRIBUTES// copia atributos
		};
		Files.copy(FROM, TO, options);

		txt_NombreFoto.setText("/_imagenes/" + "imgFile.getName()");
	}
	
	// Event Listener on TextField [DNI SUPERVISOR].onAction
	// Event Listener on TextField [DNI SUPERVISOR].onAction
	@FXML
	public void txt_Tab_Det_Id_Action(ActionEvent event) {
		/* ASIGNACION DE SUPERVISOR A USUARIO */
		/* Al meter el DNI del Supervisor hace una busqueda de sus datos */
		User Supervisor = buscarUser(txt_Tab_Det_Id.getText(), listadoUsuarios);

		if (Supervisor != null) {
			txt_Tab_Det_NombreSuper.setText(Supervisor.getNombre());
			txt_Tab_Det_ApellidoSuper.setText(Supervisor.getApellido1());
			txt_Tab_Det_Apellido2Super.setText(Supervisor.getApellido2());
		} else {
			JOptionPane.showMessageDialog(null, "No existe un DNI asociado Supervisor \n Introduzca Otro DNI");
		}

	}
	
	// Event Listener on ComBox [TIPO USUARIO].onAction-->activa/desactiva DNI	

	// Event Listener on ComBox [TIPO USUARIO].onAction-->activa/desactiva DNI
	@FXML
	public void cbo_Tab_Det_tipoUsuario_Action(ActionEvent event) {
		/* Activa el campo DNI Supervisor si se escoge a tipo usuario como Usuario */

		if (cbo_Tab_Det_tipoUsuario.getValue().equals("usuario")) {
			txt_Tab_Det_Id.setDisable(false);
		} else {
			txt_Tab_Det_Id.setDisable(true);
		}
		/* Nombre usuario y contraseña automaticos*/
		txt_Tab_Det_login.setText(txt_Tab_Det_Nombre.getText());
		txt_Tab_Det_password.setText(txt_Tab_Det_DNI.getText());
	}

	/*------------------------------------------------------------------------*/
	/* --------------------------------METODOS ------------------------------ */
	/*------------------------------------------------------------------------*/
	
	/* METODO --> RELLENA CAMPOS DETALLE USUARIO */
	/*------------------------------------------------------------------------*/
	/* --------------------------------METODOS ------------------------------ */
	/*------------------------------------------------------------------------*/

	/* METODO --> Rellena los datos de Detalle con la posición de la tabla/Array */
	public void rellenarDatosDetalleUsuario(int posicionUsuarioArray) {
		User u = listadoUsuarios.get(posicionUsuarioArray);

		/* Relleno de campos */
		txt_Tab_Det_Nombre.setText(u.getNombre());
		txt_Tab_Det_Apellido1.setText(u.getApellido1());
		txt_Tab_Det_Apellido2.setText(u.getApellido2());
		txt_Tab_Det_DNI.setText(u.getDni());
		txt_Tab_Det_telefonoMovil.setText(u.getTelefonoMovil());
		txt_Tab_Det_telefonoCasa.setText(u.getTelefonoCasa());
		Date_Tab_Det_fechaNacimiento.setText(u.getFechaNacimiento());
		txt_Tab_Det_correo.setText(u.getCorreo());
		txt_NombreFoto.setText(u.getFotoUsuario());
		
		String pathFoto = u.getFotoUsuario();

		/* Pone icosno de User si no hay foto */
		if (pathFoto==null || pathFoto.isEmpty() || pathFoto.equals("") ) {
			pathFoto = "/_imagenes/001.png";
			txt_NombreFoto.setText("/_imagenes/001.png");
		}

		Image imagen1 = new Image(pathFoto); // "file:" +
		img_Adm_Usuario.setImage(imagen1);

		txt_Tab_Det_calle.setText(u.getCalle());
		txt_Tab_Det_numeroCalle.setText(u.getNumeroCalle());
		txt_Tab_Det_ciudad.setText(u.getCiudad());
		txt_Tab_Det_provincia.setText(u.getProvincia());
		txt_Tab_Det_CP.setText(u.getCP());

		txt_Tab_Det_Id.setText(u.getDniSupervisor());

		/* Relleno de datos de Supervisor según la entrada de DNI */
		User Supervisor = buscarUser(txt_Tab_Det_Id.getText(), listadoUsuarios);
		if (Supervisor != null) {
			txt_Tab_Det_NombreSuper.setText(Supervisor.getNombre());
			txt_Tab_Det_ApellidoSuper.setText(Supervisor.getApellido1());
			txt_Tab_Det_Apellido2Super.setText(Supervisor.getApellido2());
		}

		txt_Tab_Det_login.setText(u.getlogin());
		txt_Tab_Det_password.setText(u.getpassword());
		cbo_Tab_Det_tipoUsuario.setValue(u.getTipoUsuario());

		/* activa / desactiva DNI Supervisor en funcion del tipo de usuario */

		if (cbo_Tab_Det_tipoUsuario.getValue().equals("usuario")) {
			txt_Tab_Det_Id.setDisable(false);
		} else {
			txt_Tab_Det_Id.setDisable(true);
		}
	}
	

	/* METODO --> PONE DATOS PESTAÑA DETALLE USUARIO EN BLANCO */
	public void limpiarDatosDetalleUsuario() {
		txt_Tab_Det_Id.setText("");
		txt_Tab_Det_Nombre.setText("");
		txt_Tab_Det_Apellido1.setText("");
		txt_Tab_Det_Apellido2.setText("");
		txt_Tab_Det_DNI.setText("");
		txt_Tab_Det_telefonoMovil.setText("");
		txt_Tab_Det_telefonoCasa.setText("");
		Date_Tab_Det_fechaNacimiento.setText("");
		txt_Tab_Det_correo.setText("");

		img_Adm_Usuario.setImage(new Image("/_imagenes/001.png"));
		txt_NombreFoto.setText("/_imagenes/001.png");
		txt_Tab_Det_calle.setText("");
		txt_Tab_Det_numeroCalle.setText("");
		txt_Tab_Det_ciudad.setText("");
		txt_Tab_Det_provincia.setText("");
		txt_Tab_Det_CP.setText("");

		txt_Tab_Det_NombreSuper.setText("");
		txt_Tab_Det_ApellidoSuper.setText("");
		txt_Tab_Det_Apellido2Super.setText("");

		txt_Tab_Det_login.setText("");
		txt_Tab_Det_password.setText("");
		cbo_Tab_Det_tipoUsuario.setValue("");
	}
	
	private void cargar() {
		 
		listadoUsuarios =  FXCollections.observableList(HandlerBBDD.getBBDD().recuperarUsuarios());
		Tbl_Tab_ListUsu_Admin.setItems(listadoUsuarios);
	}
	
	/* METODO --> CARGA FICHERO CSV EN OBSERVABLELIST */
	/* METODO --> cargar Archivo CSV de usuarios */
	private void cargarCsv(String archCSV) {
		try {
			User u = null;
			/* formatea la fecha a formato de archivo texto */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

			ObservableList<User> listaUsuarios = null;// inicializar listaUsuarios

			CSVReader csvReader = new CSVReader(new FileReader(archCSV));
			String[] fila = null;

			while ((fila = csvReader.readNext()) != null) {
				/* Evita el error En caso que la fecha nacimiento este en blanco */
				if (fila[7].isEmpty()) {// si no hay fecha nacimiento
					u = new User(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], null, fila[8], fila[9],
							fila[10], fila[11], fila[12], fila[13], fila[14], fila[15], fila[16], fila[17]);
				} else {
					u = new User(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6],
							fila[7],  fila[8], fila[9], fila[10], fila[11], fila[12],
							fila[13], fila[14], fila[15], fila[16], fila[17]);
				}

				listadoUsuarios.add(u);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* CARGA DE LOS USUARIOS EN TABLEVIEW */
		Tbl_Tab_ListUsu_Admin.setItems(listadoUsuarios);// cargar usuarios en tableview
	}
	
	/* METODO --> GUARDA LISTA USUARIOS EN FICHERO CSV */

	/* METODO --> Guarda los usuarios actualizados en el Archivo CSV */
	private void guardarCSV(String archCSV, ObservableList<User> listadoUsuarios) throws IOException {
		FileWriter writer = null;
		writer = new FileWriter(archCSV);
		String fila = null;
		/* bucle para introucir los datos en el archivo */
		for (User s : listadoUsuarios) {
			/* carga en fila el string contodos los datos del usuario */
			fila = s.toString();
			/* Reemplado de caracteres en paths */
			fila = fila.replace('\\', '/');
			/* con tipo CSV hay que meter retorno de carro en cada fila */
			writer.write(fila + "\n");
		}
		writer.close();
	}
	
	/* METODO --> /* ASIGNACION DE SUPERVISOR A PACIENTE */
	public boolean asignaSuper() {
	/* Al meter el DNI del Supervisor hace una busqueda de sus datos */
	User Supervisor = buscarUser(txt_Tab_Det_Id.getText(), listadoUsuarios);

	if (Supervisor != null) {
		txt_Tab_Det_NombreSuper.setText(Supervisor.getNombre());
		txt_Tab_Det_ApellidoSuper.setText(Supervisor.getApellido1());
		txt_Tab_Det_Apellido2Super.setText(Supervisor.getApellido2());
		return true;
	} else {
		JOptionPane.showMessageDialog(null, "No existe un DNI asociado Supervisor \n Introduzca Otro DNI");
		return false;
	}
}
	
	/* METODO --> /* BUSCA SUPERVISOR POR SU DNI */
	public User buscarUser(String dniSupervisor, ObservableList<User> listadoUsuarios) {
		for (User s : listadoUsuarios) {
			if (s.getDni().equals(dniSupervisor)) {
				return s;
			}
		}
		return null;
	}


	 /* METODO --> BUSCA LA POSICION EN EL ARRAY SEGUN EL DNI*/
	public int posicionEnArray() {
		String dniTableview = Tbl_Tab_ListUsu_Admin.getSelectionModel().getSelectedItem().getDni();
		int i = -1;
		for (User s : listadoUsuarios) {
			i++;
			if (s.getDni().equals(dniTableview)) {
				return i;
			}
		}
		return 0;
	}
	 /* METODO --> CALCULA NUMERO DE PACIENTE POR SUPERVISOR SEGUN DNI*/
	public int numeroPacientes() {
		String dniSuper = txt_Tab_Det_Id.getText();
		int i = 0;
		for (User s : listadoUsuarios) {
			if (s.getDniSupervisor().equals(dniSuper)) {
				++i;
			}
		}
		return i;
	}

	 /* METODO --> CARGA DATOS INICIALES EN AL TABLA  HACE FILTROTABLA */
	private void filtro() {

		TblCol_Tab_ListUsu_Admin_Rol.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
		TblCol_Tab_ListUsu_Admin_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		TblCol_Tab_ListUsu_Admin_Apellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
		TblCol_Tab_ListUsu_Admin_Apellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
		TblCol_Tab_ListUsu_Admin_Dni.setCellValueFactory(new PropertyValueFactory<>("Dni"));

		// Creacion de la listra filtrada.- inicial muestra todo
		FilteredList<User> filteredData = new FilteredList<>(listadoUsuarios, p -> true);

		// Predicado del filtro (cada vez que cambia)
		txt_Filtro.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(u -> {

				// Si el filtro esta vacio muestra todo
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				/* Filtros en los campos de la tabla Tipo Usuario, Nombre,Apellidos y DNI */
				if (u.getTipoUsuario().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.

				} else if (u.getNombre().toLowerCase().contains(lowerCaseFilter)) {

					return true; // Filter matches last name.

				} else if (u.getApellido1().toLowerCase().contains(lowerCaseFilter)) {

					return true; // Filter matches last name.

				} else if (u.getApellido2().toLowerCase().contains(lowerCaseFilter)) {

					return true; // Filter matches last name.

				} else if (u.getDni().toLowerCase().contains(lowerCaseFilter)) {

					return true; // Filter matches last name.
				}
				return false;
			});

			/* Creación de datos filtros con la clase SortedList */
			SortedList<User> sortedData = new SortedList<>(filteredData);

			/* Vincula SortedList a la Tabla */
			sortedData.comparatorProperty().bind(Tbl_Tab_ListUsu_Admin.comparatorProperty());

			/* Rellena la tabla con los datos filtrados */
			Tbl_Tab_ListUsu_Admin.setItems(sortedData);
		});

	}

}
