package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class VerticalRulerUIControler extends RulesController implements Initializable {

	@FXML private Canvas rulerCanvas;
	@FXML private AnchorPane anchorPane;
	
	private CanevasController canevasController;

    public void setRulerCanvas(Canvas rulerCanvas) {
		this.rulerCanvas = rulerCanvas;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	public CanevasController getCanevasController() {
		return canevasController;
	}

	public void setCanevasController(CanevasController canevasController) {
		this.canevasController = canevasController;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//we did not initialize this ruler because it appears after we click on menu button 
	}
	
	public void drawRules() {
		rulerCanvas.getGraphicsContext2D().clearRect(0, 0, rulerCanvas.getWidth(), rulerCanvas.getHeight());
		GraphicsContext g= rulerCanvas.getGraphicsContext2D();
		drawRuler(g, 30 , 20, 30,(int) rulerCanvas.getHeight(),true);
		setRulerExisted(true);
	}
	
	public Canvas getRulerCanvas() {
		return rulerCanvas;
	}

	
	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	@Override
	public void rulerResize() {
		getRulerCanvas().heightProperty().addListener(e->{
			if(isRulerExisted()) {
				drawRules();		
			}
			});	
		
	}

	
	
   	
	
}
