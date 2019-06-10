//**************ok funcionando*********************

package controlador;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Authentication;
import modelo.User;

public class LoginController implements Initializable {
	@FXML
	private AnchorPane Panel_Login;
	@FXML
	private Button btn_EntrarLogin;
	@FXML
	private TextField txtF_Usuario;
	@FXML
	private ImageView img_LoginPrincipal;
	@FXML
	private CheckBox chk_recordadContrasenia;
	@FXML
	private PasswordField txtF_Contrasenia;
	@FXML
	private Label labelLoginMensaje;

	Preferences preferencias;
	private Stage stage;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// Para checkbox Recuerdame
		preferencias = Preferences.userNodeForPackage(LoginController.class);
		if (preferencias.get("txtF_Usuario", null) != null && (!preferencias.get("txtF_Contrasenia", null).isEmpty())) {
			txtF_Usuario.setText(preferencias.get("txtF_Usuario", null));
			txtF_Contrasenia.setText(preferencias.get("txtF_Contrasenia", null));
			chk_recordadContrasenia.setSelected(true);
		} else {
			chk_recordadContrasenia.setSelected(false);
		}

		// oculta mensaje de error
		labelLoginMensaje.setVisible(false);

		// cuando cualquier de los textfield gane foco
		// si existe mensaje de error visible -> se oculta
		txtF_Usuario.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						labelLoginMensaje.setVisible(false);
					}
				});
		txtF_Contrasenia.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						labelLoginMensaje.setVisible(false);
					}
				});
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		stage.setTitle("Login Usuarios");
		stage.getIcons().add(new Image("/_imagenes/003.png"));
		stage.setResizable(false);
	}

	// Event Listener on Button[#btn_EntrarLogin].onAction
	@FXML
	public void btn_EntrarLogin_Action(ActionEvent event) throws Exception {

		Authentication authentication = new Authentication();
		// verifica que datos introducidos sean correctos
		User usuario1 = authentication.ComprobarDatosLogin(txtF_Usuario.getText(), txtF_Contrasenia.getText());
//System.out.println(usuario1);
		if (usuario1 != null) { // si reconoce el usuario

			// guardar usuario y contraseña si esta activa el checkbox
			if (chk_recordadContrasenia.isSelected()) {
				preferencias.put("txtF_Usuario", txtF_Usuario.getText());
				preferencias.put("txtF_Contrasenia", txtF_Contrasenia.getText());
			} else {
				preferencias.put("txtF_Usuario", "");
				preferencias.put("txtF_Contrasenia", "");
			}

			// reemplaza el stage actual por el de la vista "de usuario, Supervisor o
			// Administrador"
			// eleccion del tipo de vista en funcion del ROL del usuario
			String nombreVista1 = "";

			if (usuario1.getTipoUsuario().equals("usuario")) {
				nombreVista1 = "/vista/Usuario.fxml";
			} else if ((usuario1.getTipoUsuario().equals("supervisor"))) {
				nombreVista1 = "/vista/Supervisor.fxml";
			} else {
				nombreVista1 = "/vista/Administrador.fxml";
			}

			FXMLLoader loader = new FXMLLoader();
			InputStream in = LoginController.class.getResourceAsStream(nombreVista1);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.setLocation(LoginController.class.getResource(nombreVista1));

			AnchorPane page;
			try {
				page = (AnchorPane) loader.load(in);
			} finally {
				in.close();
			}
			// Scene scene = new Scene(page, 1500,1500);//nevo escenario según la opcion

			Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
			
			
			
			//inicializa el controlador con el usuari
			//comprueba el usuario y recupera el controlador del usuario para pasarle la variable del usuario
			//equal comprobamos el contenido del string
			
			if (usuario1.getTipoUsuario().equals("usuario")) { 
				UsuarioController usuarioControler = loader.getController();
				usuarioControler.iniData(usuario1);
			}
			if (usuario1.getTipoUsuario().equals("supervisor")) {
				SupervisorController supervisorController = loader.getController();
				supervisorController.iniData(usuario1);
			}
			if (stage == null) {
				stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			}
			stage.setScene(scene);
			stage.show();
		} else {
			labelLoginMensaje.setText("Error en usuario o contraseña");
			labelLoginMensaje.setVisible(true);
		}

	}

}