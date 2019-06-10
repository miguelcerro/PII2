/*
 * @autor Pablo
 */

package controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Mensaje;
import modelo.Paciente;
import modelo.Supervisor;
import modelo.User;

public class MensajeriaController implements Initializable {
	
	@FXML
    private TextField time_1;
    @FXML
    private TextField time_2;
    @FXML
    private TextField time_3;
    @FXML
    private TextField time_4;
    @FXML
    private TextField time_5;
    @FXML
    private TextField time_6;
    @FXML
    private TextField time_7;
    @FXML
    private TextField time_8;
    @FXML
    private TextField time_9;
    @FXML
    private TextField time_10;
    @FXML
    private TextField time_11;
    @FXML
    private TextField time_12;
    @FXML
    private TextField time_13;
    @FXML
    private TextField time_14;
    @FXML
    private TextField time_15;
    @FXML
    private TextField time_16;
    @FXML
    private TextField time_17;
    @FXML
    private TextField time_18;
    @FXML
    private TextField time_19;
    @FXML
    private TextField time_20;
    
    @FXML
    private TextField envio_1;
    @FXML
    private TextField envio_2;
    @FXML
    private TextField envio_3;
    @FXML
    private TextField envio_4;
    @FXML
    private TextField envio_5;
    @FXML
    private TextField envio_6;
    @FXML
    private TextField envio_7;
    @FXML
    private TextField envio_8;
    @FXML
    private TextField envio_9;
    @FXML
    private TextField envio_10;
    @FXML
    private TextField envio_11;
    @FXML
    private TextField envio_12;
    @FXML
    private TextField envio_13;
    @FXML
    private TextField envio_14;
    @FXML
    private TextField envio_15;
    @FXML
    private TextField envio_16;
    @FXML
    private TextField envio_17;
    @FXML
    private TextField envio_18;
    @FXML
    private TextField envio_19;
    @FXML
    private TextField envio_20;

    @FXML
    private TextField txt_1;
    @FXML
    private TextField txt_2;
    @FXML
    private TextField txt_3;
    @FXML
    private TextField txt_4;
    @FXML
    private TextField txt_5;
    @FXML
    private TextField txt_6;
    @FXML
    private TextField txt_7;
    @FXML
    private TextField txt_8;
    @FXML
    private TextField txt_9;
    @FXML
    private TextField txt_10;
    @FXML
    private TextField txt_11;
    @FXML
    private TextField txt_12;
    @FXML
    private TextField txt_13;
    @FXML
    private TextField txt_14;
    @FXML
    private TextField txt_15;
    @FXML
    private TextField txt_16;
    @FXML
    private TextField txt_17;
    @FXML
    private TextField txt_18;
    @FXML
    private TextField txt_19;
    @FXML
    private TextField txt_20;

    @FXML
    private TextField new_sms;
	
	private Mensaje[] listaMensajes;
    private String dni;
    //private String tipoUsuario;
    private User usuario;
    
    /* METODOS */
    @Override
    public void initialize(URL url, ResourceBundle rb) { }
    
    /*public void iniData(String dni, String tipoUsuario) throws FileNotFoundException, IOException {
    	this.dni = dni;
    	this.tipoUsuario = tipoUsuario;
    	Mensaje[] listaMensajes = new Mensaje[20];
    	String archCSV = "datos\\"+dni+".csv";
		listaMensajes=leerMensajes(archCSV);
		refrescar(dni);
    }*/
    
    public void iniData(String dni, User usuario) throws FileNotFoundException, IOException {
    	this.dni = dni;
    	this.usuario = usuario;
    	Mensaje[] listaMensajes = new Mensaje[20];
    	//String archCSV = "datos\\"+dni+".csv";
	//	listaMensajes=leerMensajes(usuario.getDni());
		refrescar(usuario.getDni());
    }
    
    // Refrescar mensajes
    public void refrescar(String dni) {
    //	String archCSV = "datos\\"+dni+".csv";
		listaMensajes=leerMensajes(dni);
		/**/for(int i =0; i<listaMensajes.length; i++)
 			System.out.println(listaMensajes[i]);
		
		if(listaMensajes[0]!=null) {
			time_1.setText("**"+listaMensajes[0].getTimeStamp() );
			envio_1.setText(listaMensajes[0].getEnviado());
			txt_1.setText(listaMensajes[0].getTexto());
		}
    	else { time_1.setText("--"); envio_1.setText("--"); txt_1.setText("--"); }
		if(listaMensajes[1]!=null) {
			time_2.setText(listaMensajes[1].getTimeStamp() );
			envio_2.setText(listaMensajes[1].getEnviado());
			txt_2.setText(listaMensajes[1].getTexto());
		}
    	else { time_2.setText("--"); envio_2.setText("--"); txt_2.setText("--"); }
		if(listaMensajes[2]!=null) {
			time_3.setText(listaMensajes[2].getTimeStamp() );
			envio_3.setText(listaMensajes[2].getEnviado());
			txt_3.setText(listaMensajes[2].getTexto());
		}
    	else { time_3.setText("--"); envio_3.setText("--"); txt_3.setText("--"); }
		if(listaMensajes[3]!=null) {
			time_4.setText(listaMensajes[3].getTimeStamp() );
			envio_4.setText(listaMensajes[3].getEnviado());
			txt_4.setText(listaMensajes[3].getTexto());
		}
    	else { time_4.setText("--"); envio_4.setText("--"); txt_4.setText("--"); }
		if(listaMensajes[4]!=null) {
			time_5.setText(listaMensajes[4].getTimeStamp() );
			envio_5.setText(listaMensajes[4].getEnviado());
			txt_5.setText(listaMensajes[4].getTexto());
		}
    	else { time_5.setText("--"); envio_5.setText("--"); txt_5.setText("--"); }
		if(listaMensajes[5]!=null) {
			time_6.setText(listaMensajes[5].getTimeStamp() );
			envio_6.setText(listaMensajes[5].getEnviado());
			txt_6.setText(listaMensajes[5].getTexto());
		}
    	else { time_6.setText("--"); envio_6.setText("--"); txt_6.setText("--"); }
		if(listaMensajes[6]!=null) {
			time_7.setText(listaMensajes[6].getTimeStamp() );
			envio_7.setText(listaMensajes[6].getEnviado());
			txt_7.setText(listaMensajes[6].getTexto());
		}
    	else { time_7.setText("--"); envio_7.setText("--"); txt_7.setText("--"); }
		if(listaMensajes[7]!=null) {
			time_8.setText(listaMensajes[7].getTimeStamp() );
			envio_8.setText(listaMensajes[7].getEnviado());
			txt_8.setText(listaMensajes[7].getTexto());
		}
    	else { time_8.setText("--"); envio_8.setText("--"); txt_8.setText("--"); }
		if(listaMensajes[8]!=null) {
			time_9.setText(listaMensajes[8].getTimeStamp() );
			envio_9.setText(listaMensajes[8].getEnviado());
			txt_9.setText(listaMensajes[8].getTexto());
		}
    	else { time_9.setText("--"); envio_9.setText("--"); txt_9.setText("--"); }
		if(listaMensajes[9]!=null) {
			time_10.setText(listaMensajes[9].getTimeStamp() );
			envio_10.setText(listaMensajes[9].getEnviado());
			txt_10.setText(listaMensajes[9].getTexto());
		}
    	else { time_10.setText("--"); envio_10.setText("--"); txt_10.setText("--"); }
		if(listaMensajes[10]!=null) {
			time_11.setText(listaMensajes[10].getTimeStamp() );
			envio_11.setText(listaMensajes[10].getEnviado());
			txt_11.setText(listaMensajes[10].getTexto());
		}
    	else { time_11.setText("--"); envio_11.setText("--"); txt_11.setText("--"); }
		if(listaMensajes[11]!=null) {
			time_12.setText(listaMensajes[11].getTimeStamp() );
			envio_12.setText(listaMensajes[11].getEnviado());
			txt_12.setText(listaMensajes[11].getTexto());
		}
    	else { time_12.setText("--"); envio_12.setText("--"); txt_12.setText("--"); }
		if(listaMensajes[12]!=null) {
			time_13.setText(listaMensajes[12].getTimeStamp() );
			envio_13.setText(listaMensajes[12].getEnviado());
			txt_13.setText(listaMensajes[12].getTexto());
		}
    	else { time_13.setText("--"); envio_13.setText("--"); txt_13.setText("--"); }
		if(listaMensajes[13]!=null) {
			time_14.setText(listaMensajes[13].getTimeStamp() );
			envio_14.setText(listaMensajes[13].getEnviado());
			txt_14.setText(listaMensajes[13].getTexto());
		}
    	else { time_14.setText("--"); envio_14.setText("--"); txt_14.setText("--"); }
		if(listaMensajes[14]!=null) {
			time_15.setText(listaMensajes[14].getTimeStamp() );
			envio_15.setText(listaMensajes[14].getEnviado());
			txt_15.setText(listaMensajes[14].getTexto());
		}
    	else { time_15.setText("--"); envio_15.setText("--"); txt_15.setText("--"); }
		if(listaMensajes[15]!=null) {
			time_16.setText(listaMensajes[15].getTimeStamp() );
			envio_16.setText(listaMensajes[15].getEnviado());
			txt_16.setText(listaMensajes[15].getTexto());
		}
    	else { time_16.setText("--"); envio_16.setText("--"); txt_16.setText("--"); }
		if(listaMensajes[16]!=null) {
			time_17.setText(listaMensajes[16].getTimeStamp());
			envio_17.setText(listaMensajes[16].getEnviado());
			txt_17.setText(listaMensajes[16].getTexto());
		}
    	else { time_17.setText("--"); envio_17.setText("--"); txt_17.setText("--"); }
		if(listaMensajes[17]!=null) {
			time_18.setText(listaMensajes[17].getTimeStamp());
			envio_18.setText(listaMensajes[17].getEnviado());
			txt_18.setText(listaMensajes[17].getTexto());
		}
    	else { time_18.setText("--"); envio_18.setText("--"); txt_18.setText("--"); }
		if(listaMensajes[18]!=null) {
			time_19.setText(listaMensajes[18].getTimeStamp());
			envio_19.setText(listaMensajes[18].getEnviado());
			txt_19.setText(listaMensajes[18].getTexto());
		}
    	else { time_19.setText("--"); envio_19.setText("--"); txt_19.setText("--"); }
		if(listaMensajes[19]!=null) {
			time_20.setText(listaMensajes[19].getTimeStamp());
			envio_20.setText(listaMensajes[19].getEnviado());
			txt_20.setText(listaMensajes[19].getTexto());
		}
    	else { time_20.setText("--"); envio_20.setText("--"); txt_20.setText("--"); }
		
    }
    
    
    // Leer archivo mensajes
 	public Mensaje[] leerMensajes(String dni) {
 
 		Mensaje[] lista = HandlerBBDD.getBBDD().leerMensajes(dni);
 		/**/for(int i =0; i<lista.length; i++)
 			System.out.println(lista[i]);
 	
 		System.out.println("**************************************");
 		return lista;
 	}
    
    
   	// Event Listener on Button[#btn_exit].onAction
   	@FXML
   	public void btn_exit(ActionEvent event) throws Exception {
   		String vista;
   		InputStream in;
   		FXMLLoader loader = new FXMLLoader();
   		
   		if (usuario.getTipoUsuario().equalsIgnoreCase("supervisor")) {
   			vista = "/vista/Supervisor.fxml";
   			in= SupervisorController.class.getResourceAsStream(vista);
   	    	loader.setBuilderFactory(new JavaFXBuilderFactory());
   	    	loader.setLocation(SupervisorController.class.getResource(vista));
		}
		else {
			vista = "/vista/Usuario.fxml";
			in= UsuarioController.class.getResourceAsStream(vista);
    	loader.setBuilderFactory(new JavaFXBuilderFactory());
    	loader.setLocation(UsuarioController.class.getResource(vista));
		}
   		
   		

    	AnchorPane page;
    	try {
    		page = (AnchorPane) loader.load(in);
    	} finally {
    		in.close();
    	}
		Scene scene = new Scene(page);// nevo escenario según la opcion con tamaño automatico segun vista
		if (usuario.getTipoUsuario().equalsIgnoreCase("supervisor")) {
			SupervisorController supervisorController = loader.getController();
			supervisorController.iniData(usuario);
		}
		else {
			UsuarioController usuarioControler = loader.getController();
			usuarioControler.iniData(usuario);
		}
		
		//SupervisorController supervisorController = loader.getController();
		//supervisorController.iniData(usuario1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

   	// Event Listener on Button[#btn_enviar].onAction
   	@FXML
   	public void btn_enviar(ActionEvent event) throws Exception {
   	//	String archCSV = "datos\\"+dni+".csv";
	//	CSVReader csvReader = new CSVReader(new FileReader(archCSV));
	//	List<String[]> csvBody =csvReader.readAll();
        // Creando nuevo mensaje
        Calendar calendar = Calendar.getInstance ();
		java.util.Date now = calendar.getTime ();
		java.sql.Timestamp time = new java.sql.Timestamp (now.getTime ());
		String fecha=time.toLocalDateTime().toString();
		HandlerBBDD.getBBDD().insertarMensaje(fecha,usuario,new_sms.getText());
		
		/*
		csvBody.add(sms);
        csvReader.close();
        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(archCSV), ',',CSVWriter.NO_ESCAPE_CHARACTER);
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();*/
        refrescar(dni);
	}

   	
}