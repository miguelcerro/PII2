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

public class Usuario2Controller implements Initializable {
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

	    
	    private User user;
	    private User supervisor;
	   
	    
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
	    }    	
	

	//Metodo que llama al recuperar los datos desde el csv y lo presenta
	public void iniData (User supervisor, User user) {		
		this.user = user;
		this.supervisor = supervisor;
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
		String vistaSupervisor = "/vista/Supervisor.fxml";

		FXMLLoader loader = new FXMLLoader();
		InputStream in = Sensores2Controller.class.getResourceAsStream(vistaSupervisor);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Sensores2Controller.class.getResource(vistaSupervisor));

		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
	
		
		Scene scene = new Scene(page);
		SupervisorController supervisorController = loader.getController();
		supervisorController.iniData(supervisor);
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}

