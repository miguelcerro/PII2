 package app;

import java.io.InputStream;

import controlador.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMain extends Application {

	private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			this.stage = stage;
			stage.setTitle("Login Usuarios");
			LoginController login = (LoginController) replaceSceneContent("/vista/Login.fxml");
			login.setStage(stage);
			stage.show();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = FXMain.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(FXMain.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}

	public static void main(String[] args) throws Exception {
		launch(args);

	}
}
