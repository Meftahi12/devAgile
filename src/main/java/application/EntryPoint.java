package application;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntryPoint extends Application{

	private static final String PATH_MAIN = "src/main/java/application/view/MainUI.fxml";
	@Override
	public void start(Stage stage) throws Exception {
		Parent root;
		try {
			URL urlMain = new File(PATH_MAIN).toURI().toURL();
			root = FXMLLoader.load(urlMain);
			root.prefHeight(600);
			root.prefWidth(800);
			Scene scene = new Scene(root);
			stage.setTitle("dDraw4Us");
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			Logger logger = Logger.getGlobal(); 
			logger.log(Level.SEVERE, "Exception");
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
