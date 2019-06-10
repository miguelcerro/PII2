/*
 * @autor Pablo
 */

package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Database.HandlerBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Paciente;
import modelo.Supervisor;
import modelo.User;

public class SupervisorController implements Initializable {
	
	@FXML
	private TextField nombre_1;
	
	@FXML
	private TextField apellido_11;
	
	@FXML
	private TextField apellido_12;

	@FXML
	private TextField dni_1;

	@FXML
	private TextField nombre_2;

	@FXML
	private TextField apellido_21;

	@FXML
	private TextField apellido_22;
	
	@FXML
	private TextField dni_2;

	@FXML
	private TextField nombre_3;

	@FXML
	private TextField apellido_31;

	@FXML
	private TextField apellido_32;
	
	@FXML
	private TextField dni_3;

	@FXML
	private TextField nombre_4;

	@FXML
	private TextField apellido_41;

	@FXML
	private TextField apellido_42;
	
	@FXML
	private TextField dni_4;

	@FXML
	private TextField nombre_5;

	@FXML
	private TextField apellido_51;

	@FXML
	private TextField apellido_52;
	
	@FXML
	private TextField dni_5;

	@FXML
	private TextField nombre_6;

	@FXML
	private TextField apellido_61;

	@FXML
	private TextField apellido_62;
	
	@FXML
	private TextField dni_6;

	@FXML
	private TextField nombre_7;

	@FXML
	private TextField apellido_71;

	@FXML
	private TextField apellido_72;
	
	@FXML
	private TextField dni_7;

	@FXML
	private TextField nombre_8;

	@FXML
	private TextField apellido_81;

	@FXML
	private TextField apellido_82;
	
	@FXML
	private TextField dni_8;

	
	private Supervisor sup;
	private User usuario;
	private User[] lista;
	
	
	/*** METODOS ***/
	
    @Override
    public void initialize(URL url, ResourceBundle rb) { }
    
    //
    public void iniData(User user)  {
    	//Supervisor sup = new Supervisor();
    	System.out.println("control 1");
    	this.sup = (Supervisor)HandlerBBDD.getBBDD().recuperar(user.getlogin());;
    	//System.out.println("User: "+user.getlogin());	//PRUEBAS
    	//System.out.println(sup.cargarDni(user.getlogin()));	//PRUEBAS
    	System.out.println("control 2");
    	rellenarPacientes(sup.getDniSupervisor(), sup);
    	System.out.println("control 3");
    	System.out.println(dni_1.getText()+"/"+dni_2.getText()+"/"+dni_3.getText());	//PRUEBAS
    }
     
    //
    public void rellenarPacientes(String dni, Supervisor sup) {
    	//sup.cargarListaPacientes(dni);
    	sup.printListaPacientes();
    	this.lista = sup.getListaPacientes();
    	//Paciente 1	
    	if(lista[0]!=null) {
    		nombre_1.setText(lista[0].getNombre());
    		apellido_11.setText(lista[0].getApellido1());
    		apellido_12.setText(lista[0].getApellido2());
    		dni_1.setText(lista[0].getDni());
    		
    	}
    	else {
    		nombre_1.setText("--");
    		apellido_11.setText("--");
    		apellido_12.setText("--");
    		dni_1.setText("--");
    	}
    	//Paciente 2
    	if(lista[1]!=null) {
    		nombre_2.setText(lista[1].getNombre());
    		apellido_21.setText(lista[1].getApellido1());
    		apellido_22.setText(lista[1].getApellido2());
    		dni_2.setText(lista[1].getDni());
    	}
    	else {
    		nombre_2.setText("--");
    		apellido_21.setText("--");
    		apellido_22.setText("--");
    		dni_2.setText("--");
    	}
    	//Paciente 3
    	if(lista[2]!=null) {
    		nombre_3.setText(lista[2].getNombre());
    		apellido_31.setText(lista[2].getApellido1());
    		apellido_32.setText(lista[2].getApellido2());
    		dni_3.setText(lista[2].getDni());
    	}
    	else {
    		nombre_3.setText("--");
    		apellido_31.setText("--");
    		apellido_32.setText("--");
    		dni_3.setText("--");
    	}
    	//Paciente 4
    	if(lista[3]!=null) {
    		nombre_4.setText(lista[3].getNombre());
    		apellido_41.setText(lista[3].getApellido1());
    		apellido_42.setText(lista[3].getApellido2());
    		dni_4.setText(lista[3].getDni());
    	}
    	else {
    		nombre_4.setText("--");
    		apellido_41.setText("--");
    		apellido_42.setText("--");
    		dni_4.setText("--");
    	}
    	//Paciente 5
    	if(lista[4]!=null) {
    		nombre_5.setText(lista[4].getNombre());
    		apellido_51.setText(lista[4].getApellido1());
    		apellido_52.setText(lista[4].getApellido2());
    		dni_5.setText(lista[4].getDni());
    	}
    	else {
    		nombre_5.setText("--");
    		apellido_51.setText("--");
    		apellido_52.setText("--");
    		dni_5.setText("--");
    	}
    	//Paciente 6
    	if(lista[5]!=null) {
    		nombre_6.setText(lista[5].getNombre());
    		apellido_61.setText(lista[5].getApellido1());
    		apellido_62.setText(lista[5].getApellido2());
    		dni_6.setText(lista[5].getDni());
    	}
    	else {
    		nombre_6.setText("--");
    		apellido_61.setText("--");
    		apellido_62.setText("--");
    		dni_6.setText("--");
    	}
    	//Paciente 7
    	if(lista[6]!=null) {
    		nombre_7.setText(lista[6].getNombre());
    		apellido_71.setText(lista[6].getApellido1());
    		apellido_72.setText(lista[6].getApellido2());
    		dni_7.setText(lista[6].getDni());
    	}
    	else {
    		nombre_7.setText("--");
    		apellido_71.setText("--");
    		apellido_72.setText("--");
    		dni_7.setText("--");
    	}
    	//Paciente 8
    	if(lista[7]!=null) {
    		nombre_8.setText(lista[7].getNombre());
    		apellido_81.setText(lista[7].getApellido1());
    		apellido_82.setText(lista[7].getApellido2());
    		dni_8.setText(lista[7].getDni());
    	}
    	else {
    		nombre_8.setText("--");
    		apellido_81.setText("--");
    		apellido_82.setText("--");
    		dni_8.setText("--");
    	}
    }
    
    
    /*** EVENTOS BOTONES ***/
    
   	// Event Listener on Button[#btn_exit].onAction
   	@FXML
   	public void btn_exit(ActionEvent event) throws Exception {
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
   	
   	// Event Listener on Button[#btn_sms1].onAction
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(sup.getListaPacientes()[0].getDni(), sup);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sms2].onAction
   	@FXML
   	public void btn_sms2(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_2.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
	// Event Listener on Button[#btn_sms3].onAction
   	@FXML
   	public void btn_sms3(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_3.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sms4].onAction
   	@FXML
   	public void btn_sms4(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_4.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sms5].onAction
   	@FXML
   	public void btn_sms5(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_5.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sms6].onAction
   	@FXML
   	public void btn_sms6(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_6.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sms7].onAction
   	@FXML
   	public void btn_sms7(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_7.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sms8].onAction
   	@FXML
   	public void btn_sms8(ActionEvent event) throws Exception {
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
		MensajeriaController mensajeriaController = loader.getController();
		mensajeriaController.iniData(dni_8.getText(), usuario);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	
   	// Event Listener on Button[#btn_sensor1].onAction
   	@FXML
   	public void btn_sensor1(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[0]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	
   	// Event Listener on Button[#btn_sensor2].onAction
   	@FXML
   	public void btn_sensor2(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[1]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sensor3].onAction
   	@FXML
   	public void btn_sensor3(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[2]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sensor].onAction
   	@FXML
   	public void btn_sensor4(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[3]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sensor].onAction
   	@FXML
   	public void btn_sensor5(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[4]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sensor].onAction
   	@FXML
   	public void btn_sensor6(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[5]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sensor].onAction
   	@FXML
   	public void btn_sensor7(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[6]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	// Event Listener on Button[#btn_sensor].onAction
   	@FXML
   	public void btn_sensor8(ActionEvent event) throws Exception {
   		String vistaSensores = "/vista/Sensores2.fxml";
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
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Sensores2Controller sensores2Controller= loader.getController();
		sensores2Controller.iniData(sup, lista[7]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   	}
   	
   	//  Event Listener on Button[#btn_usuario].onAction
   	@FXML
    void btn_usuario0(ActionEvent event) throws Exception {
   		mostrar(0, event);
    }
   	@FXML
    void btn_usuario1(ActionEvent event) throws Exception {
    	mostrar(1,event);
    }
   	@FXML
    void btn_usuario2(ActionEvent event) throws Exception {
    	mostrar(2,event);
    }
   	
   	private void mostrar(int i, ActionEvent event) throws IOException {
   		if(lista[i]!= null && !lista[i].getNombre().equalsIgnoreCase("")) {
   		String vistaPaciente = "/vista/Usuario2.fxml";
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Usuario2Controller.class.getResourceAsStream(vistaPaciente);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Usuario2Controller.class.getResource(vistaPaciente));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automaticosegun vista
		Usuario2Controller usuario2Controller= loader.getController();
		usuario2Controller.iniData(sup, lista[i]);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   		}
   	}
}