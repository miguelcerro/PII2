/*
 * @autor Miguel
 * 
 */
package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.GraphBarInfo;
import modelo.GraphLineInfo;
import modelo.User;


public class Sensores2Controller implements Initializable {
	
	  @FXML
	    private AnchorPane panel;

	    @FXML
	    private Button volver;

	    @FXML
	    private LineChart<Number, Number> temperature;
	    
	    @FXML
	    private LineChart<Number, Number> smoke;
	    
	    @FXML
	    private LineChart<Number, Number> movement;

	    @FXML
	    private BarChart<String, Number> diary;
 
	private User user;
	private User supervisor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub		
			
	}
		
		
	//metodo recuperamos el usuario
	
	public void iniData (User supervisor, User user) {		
		this.user = user;
		this.supervisor = supervisor;
		//leemos el fichero con los datos y creamos las colecciones
		
		String archCSV = "datos\\datossensores.csv";
		List<GraphLineInfo> temperatureData = new ArrayList<GraphLineInfo>();
		List<GraphLineInfo> smoke = new ArrayList<GraphLineInfo>();
		List<GraphLineInfo> movement = new ArrayList<GraphLineInfo>();
		List<GraphBarInfo> data = new ArrayList<GraphBarInfo>();

		try (BufferedReader br = new BufferedReader(new FileReader(archCSV));) {
			String linea;

			do {
				linea = br.readLine();
				if (linea != null) {
				String[] fila = linea.split(",");
				
				if (fila[0].equals(this.user.getlogin()) ) {
				
				for(String dataCollection : fila[1].split(";")) {
					String[] values = dataCollection.split("-");
					temperatureData.add(new GraphLineInfo(Integer.parseInt(values[0]),Integer.parseInt(values[1])));
				}
				
				for(String dataCollection : fila[2].split(";")) {
					String[] values = dataCollection.split("-");
					smoke.add(new GraphLineInfo(Integer.parseInt(values[0]),Integer.parseInt(values[1])));
				}
				
				for(String dataCollection : fila[3].split(";")) {
					String[] values = dataCollection.split("-");
					movement.add(new GraphLineInfo(Integer.parseInt(values[0]),Integer.parseInt(values[1])));
				}
				
				for(String dataCollection : fila[4].split(";")) {
					String[] values = dataCollection.split("-");
					data.add(new GraphBarInfo(values[0],Integer.parseInt(values[1])));
				}
				}}
			} while (linea != null);
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "¡Error al cargar el fichero");
			e.printStackTrace();
		}
		//colecciones de las graficas
		XYChart.Series<Number, Number> tempSeries = new XYChart.Series<>();
		XYChart.Series<Number, Number> smokeSeries = new XYChart.Series<>();
		XYChart.Series<Number, Number> movementSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> diarySeries = new XYChart.Series<>();
		
		for(GraphLineInfo lineInfo:temperatureData) {
			tempSeries.getData().add(new XYChart.Data(lineInfo.getX(),lineInfo.getY()));
		}
		this.temperature.getData().add(tempSeries);
		
		for(GraphLineInfo lineInfo:smoke) {
			smokeSeries.getData().add(new XYChart.Data(lineInfo.getX(),lineInfo.getY()));
		}
		this.smoke.getData().add(smokeSeries);
		
		for(GraphLineInfo lineInfo:movement) {
			movementSeries.getData().add(new XYChart.Data(lineInfo.getX(),lineInfo.getY()));
		}
		this.movement.getData().add(movementSeries);
		
		for(GraphBarInfo barInfo:data) {
			diarySeries.getData().add(new XYChart.Data(barInfo.getX(),barInfo.getY()));
		}
		this.diary.getData().add(diarySeries);
	}
	
	
	//Metodo volver a la pantalla del usuario
	
	@FXML
	public void btn_volver(ActionEvent event) throws Exception {
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
