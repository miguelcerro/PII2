/*
 * @autor Miguel
 * 
 */

package controlador;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Paciente;
import  modelo.User;

public class UsuarioController implements Initializable {
	 @FXML
	    private TextField txt_Nombre;

	    @FXML
	    private TextField txt_Apellido1;

	    @FXML
	    private TextField txt_Apellido2;

	    @FXML
	    private TextField txt_DNI;

	    @FXML
	    private TextField txt_Movil;

	    @FXML
	    private TextField txt_casa;

	    @FXML
	    private TextField fnacimiento;

	    @FXML
	    private TextField txt_correo;

	    @FXML
	    private Button img_fotousuario;

	    @FXML
	    private TextField txt_calle;

	    @FXML
	    private TextField txt_numeroCalle;

	    @FXML
	    private TextField txt_ciudad;

	    @FXML
	    private TextField txt_provincia;

	    @FXML
	    private TextField cp;

	    @FXML
	    private Button guardarcambios;

	    @FXML
	    private Button formulariocontacto;

	    @FXML
	    private Button alertas;

	    
	    private User user;
	   
	    
@Override
public void initialize(URL url, ResourceBundle rb) {

	    }    	
	

	// Event Listener on Button[#btn_EntrarLogin].onAction
	@FXML
    void btn_saveUser(ActionEvent event) {	
		if (!User.validDni(txt_DNI.getText())) {
			JOptionPane.showMessageDialog(null, "El DNI no es válido");
		}
		else {
			if (!User.validCorreo(txt_correo.getText())){
				JOptionPane.showMessageDialog(null, "El Email no es válido");
			}
			else {
				if (!User.validPhone(txt_Movil.getText())){
					JOptionPane.showMessageDialog(null, "El Teléfono introducido es incorrecto");
				}
				else {
					User userToSave = new User (				
							null,
						    txt_Nombre.getText(),
						    txt_Apellido1.getText(),
						    txt_Apellido2.getText(),
						    txt_DNI.getText(),
						    txt_Movil.getText(),
						    txt_casa.getText(),
						    fnacimiento.getText(),
						    txt_correo.getText(),
						    //img_fotousuario.getText(),
						    txt_calle.getText(),
						    txt_numeroCalle.getText(),
						    txt_ciudad.getText(),
						    txt_provincia.getText(),
						    cp.getText(), 
						    null,
						    null,
						    null);
		
					Paciente paciente = new Paciente ();
					
					//Se llamda al metod de actulizacion de datos del usuarios
					
					try {
						paciente.saveUser(userToSave,this.user.getlogin());
						Alert alert = new Alert(AlertType.CONFIRMATION, "Se han actualizado los datos correctamente", ButtonType.OK);
						alert.showAndWait();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
    }
	
	
	//Metodo que llama al recuperar los datos desde el csv y lo presenta
	
	public void iniData (User user) {		
		this.user = user;
		Paciente paciente = new Paciente();
		// verifica que datos introducidos sean correctos
		User data = paciente.recoveryUserData(user.getlogin());
	    txt_Nombre.setText(data.getNombre());
	    txt_Apellido1.setText(data.getApellido1());
	    txt_Apellido2.setText(data.getApellido2());
	    txt_DNI.setText(data.getDni());
	    txt_Movil.setText(data.getTelefonoMovil());
	    txt_casa.setText(data.getTelefonoCasa());
	    fnacimiento.setText(data.getFechaNacimiento());
	    txt_correo.setText(data.getCorreo());
	    /*img_fotousuario.setText(data.getFotoUsuario());*/
	    txt_calle.setText(data.getCalle());
	    txt_numeroCalle.setText(data.getNumeroCalle());
	    txt_ciudad.setText(data.getCiudad());
	    txt_provincia.setText(data.getProvincia());
	    cp.setText(data.getCP());
    
	}
	
	@FXML
	public void btn_CerrarSesion(ActionEvent event) throws Exception {
			String vistaLogin = "/vista/Login.fxml";

			FXMLLoader loader = new FXMLLoader();
			InputStream in = UsuarioController.class.getResourceAsStream(vistaLogin);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.setLocation(UsuarioController.class.getResource(vistaLogin));

			AnchorPane page;
			try {
				page = (AnchorPane) loader.load(in);
			} finally {
				in.close();
			}
			// Scene scene = new Scene(page, 1500,1500);//nevo escenario según la opcion

			Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	
	@FXML
	public void btn_sensores(ActionEvent event) throws Exception {
		String vistaSensores = "/vista/Sensores.fxml";

		FXMLLoader loader = new FXMLLoader();
		InputStream in = UsuarioController.class.getResourceAsStream(vistaSensores);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(UsuarioController.class.getResource(vistaSensores));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		// Scene scene = new Scene(page, 1500,1500);//nevo escenario según la opcion

		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		SensoresController sensoresController= loader.getController();
		sensoresController.iniData(this.user);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void btn_mensajeria(ActionEvent event) throws Exception {
		String vistaSms = "/vista/Mensajeria.fxml";

		FXMLLoader loader = new FXMLLoader();
		InputStream in = UsuarioController.class.getResourceAsStream(vistaSms);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(UsuarioController.class.getResource(vistaSms));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		// Scene scene = new Scene(page, 1500,1500);//nevo escenario según la opcion

		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(txt_DNI.getText(), user);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
   	public void btn_sms1(ActionEvent event) throws Exception {
   		String vistaSms = "/vista/Mensajeria.fxml";
   		FXMLLoader loader = new FXMLLoader();
   		InputStream in = UsuarioController.class.getResourceAsStream(vistaSms);
    	loader.setBuilderFactory(new JavaFXBuilderFactory());
    	loader.setLocation(UsuarioController.class.getResource(vistaSms));
    	AnchorPane page;
    	try {
    		page = (AnchorPane) loader.load(in);
    	} finally {
    		in.close();
    	}
		Scene scene = new Scene(page);
		//MensajeriaController mensajeriaController = loader.getController();
		//mensajeriaController.iniData(txt_DNI.getText(), "usuario");
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
}

