package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainUIController implements Initializable {

	private static final int HEIGHT_OFFSET = 160;
	private static final int WIDTH_OFFSET = 65;

	private static final int MOVING_STEP = 5;
	private static final int NO_STEP = 0;

	private static final String PATH_MENU = "src/main/java/application/view/MenuUI.fxml";
	private static final String PATH_PALETTE_PATTERNS="src/main/java/application/view/PalettePatternsUI.fxml";
	private static final String PATH_PALETTE_OUTILS="src/main/java/application/view/PaletteOutilsUI.fxml";
	private static final String PATH_CANEVAS="src/main/java/application/view/CanevasUI.fxml";
	private static final String PATH_STATUSBAR="src/main/java/application/view/StatusBarUI.fxml";
	private static final String PATH_RULER="src/main/java/application/view/RulerUIView.fxml";
	private static final String PATH_VERTICALRULER="src/main/java/application/view/RulerUIViewVertical.fxml";
	
	//GREAT

	@FXML
	private BorderPane root;

	@FXML
	private AnchorPane menuAnchor;
	@FXML
	private AnchorPane canevasAnchor;
	@FXML
	private AnchorPane paletteOutilsAnchor;   
	@FXML
	private AnchorPane statusbBarAnchor;
	@FXML
	private AnchorPane rulerAnchor;

	@FXML
	private AnchorPane verticalRulerAnchor;


	PaletteOutilsUIController paletteOutilsController;
	PalettePatternsUIController palettePatternsController;
	MenuUIController menuController;
	CanevasController canevasController;
	StatusBarController satusbarController;
	HorizontalRulerUIControler rulerUIControler;
	VerticalRulerUIControler verticalRulerUIControler;

	@Override
	public void initialize(URL url, ResourceBundle rb) {   

		Platform.runLater(() -> {
			URL urlMenu;
			URL urlPalettePatterns;
			URL urlPaletteOutils;
			URL urlCanevas;
			URL urlStatusBar;
			URL urlRuler;
			URL urlVericalRuler;
			try {
				//Initialisation de la partie Top de l'interface
				urlMenu = new File(PATH_MENU).toURI().toURL();
				urlPalettePatterns = new File(PATH_PALETTE_PATTERNS).toURI().toURL();
				urlRuler= new File(PATH_RULER).toURI().toURL();
				VBox vbox = new VBox();
				FXMLLoader fxmlLoaderMenu = new FXMLLoader(urlMenu);
				vbox.getChildren().add((Node) fxmlLoaderMenu.load());
				FXMLLoader fxmlLoaderPalettePatterns = new FXMLLoader(urlPalettePatterns);
				vbox.getChildren().add((Node) fxmlLoaderPalettePatterns.load());
				FXMLLoader fxmlLoaderRuler= new FXMLLoader(urlRuler);
				vbox.getChildren().add((Node) fxmlLoaderRuler.load());
				menuAnchor.getChildren().clear();
				menuAnchor.getChildren().add(vbox);

				//Initialisation de la partie Left de l'interface
				urlPaletteOutils = new File(PATH_PALETTE_OUTILS).toURI().toURL();
				urlVericalRuler =new File(PATH_VERTICALRULER).toURI().toURL();
				paletteOutilsAnchor.getChildren().clear();
				
				HBox hBox= new HBox();
				FXMLLoader fxmlLoaderPaletteOutils = new FXMLLoader(urlPaletteOutils);
				hBox.getChildren().add((Node) fxmlLoaderPaletteOutils.load());
				FXMLLoader fxmlLoaderVerticalRuler= new FXMLLoader(urlVericalRuler);
				hBox.getChildren().add((Node) fxmlLoaderVerticalRuler.load());
				
				paletteOutilsAnchor.getChildren().clear();
				paletteOutilsAnchor.getChildren().add(hBox);
				//Initialisation de la partie Center de l'interface
				urlCanevas = new File(PATH_CANEVAS).toURI().toURL();
				canevasAnchor.getChildren().clear();
				FXMLLoader fxmlLoaderCanevas = new FXMLLoader(urlCanevas);
				canevasAnchor.getChildren().add((Node) fxmlLoaderCanevas.load());

				//Initialisation de la partie bas de l'interface Staus bar
				urlStatusBar = new File(PATH_STATUSBAR).toURI().toURL();
				statusbBarAnchor.getChildren().clear();
				FXMLLoader fxmlLoaderStatusBar = new FXMLLoader(urlStatusBar);
				statusbBarAnchor.getChildren().add((Node) fxmlLoaderStatusBar.load());




				paletteOutilsController = new PaletteOutilsUIController();
				palettePatternsController = new PalettePatternsUIController();
				menuController =  new MenuUIController();
				canevasController = new CanevasController();
				satusbarController=new StatusBarController();
				rulerUIControler = new HorizontalRulerUIControler();
				verticalRulerUIControler= new VerticalRulerUIControler();
				
				paletteOutilsController = fxmlLoaderPaletteOutils.getController();
				palettePatternsController = fxmlLoaderPalettePatterns.getController();
				menuController =  fxmlLoaderMenu.getController();
				canevasController =  fxmlLoaderCanevas.getController();
				satusbarController=fxmlLoaderStatusBar.getController();
				rulerUIControler= fxmlLoaderRuler.getController();
				verticalRulerUIControler= fxmlLoaderVerticalRuler.getController();
				
				canevasController.setMenuUIController(menuController);
				menuController.setCanevasController(canevasController);
				menuController.setRulerUIControler(rulerUIControler);
				menuController.setVerticalRulerUIControler(verticalRulerUIControler);
				paletteOutilsController.setCanevasController(canevasController);
				palettePatternsController.setCanevasController(canevasController);
				satusbarController.setCanevasController(canevasController);
				
				canevasController.setStausBarController(satusbarController);

				
				menuController.setChangesController(canevasController.getChangesController());
				this.bindSizes();

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}
	public void keyPressed(KeyEvent e) {
		if(canevasController.getSelectedShape()!=null) {

			if(e.getCode().equals(KeyCode.R) ) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().rotateShape();	
			}
			else if(e.getCode().equals(KeyCode.W)) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().moveShape(NO_STEP,-MOVING_STEP);
			}
			else if(e.getCode().equals(KeyCode.S)) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().moveShape(NO_STEP,MOVING_STEP);
			}
			else if(e.getCode().equals(KeyCode.D)) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().moveShape(MOVING_STEP,NO_STEP);
			}
			else if(e.getCode().equals(KeyCode.A)) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().moveShape(-MOVING_STEP,NO_STEP);
			}
			else if(e.getCode().equals(KeyCode.E)) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().enlarge();
			}
		}



	}
	private void gridPaneResize() {
		canevasController.getGridPane().heightProperty().addListener( e->{
			menuController.cleanGridPane();	
			menuController.showGridLines();	
			
		});
		canevasController.getGridPane().widthProperty().addListener( e->{
			menuController.cleanGridPane();
			menuController.showGridLines();	
			
		});
	}

	private void bindSizes() {
		canevasController.getCanvas().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		canevasController.getScrollPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		canevasController.getAnchorPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		canevasController.getGridPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		paletteOutilsController.getAnchorPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		paletteOutilsController.getPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		paletteOutilsController.getVBox().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		verticalRulerUIControler.getAnchorPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		verticalRulerUIControler.getRulerCanvas().heightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		

		canevasController.getCanvas().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		canevasController.getScrollPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		canevasController.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		canevasController.getGridPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		satusbarController.getStatusBarAnchor().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		satusbarController.getPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		
		menuController.getMenu().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		menuController.getPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		menuController.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		palettePatternsController.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		palettePatternsController.getHBox().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		palettePatternsController.getPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		rulerUIControler.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		rulerUIControler.getRulerCanvas().widthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		gridPaneResize();
		rulerUIControler.rulerResize();
		verticalRulerUIControler.rulerResize();
		
	}
}
