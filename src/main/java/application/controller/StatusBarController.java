package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StatusBarController implements Initializable {
	
	
	
	@FXML
	private Slider zoomSlider;

	@FXML
	private AnchorPane statusBarAnchor;

	@FXML
	private Pane pane;

	@FXML
	private Label shapeWidth;

	@FXML
	private Label shapeHeight;


	
	private CanevasController canevasController;

	public void setCanevasController(CanevasController canevasController) {
		this.canevasController = canevasController;
		

	}

	public CanevasController getCanevasController() {

		return canevasController;
	}

	
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				canevasController.updateCanvasScale((double) newValue);

			
			
			}
		});

	}

	public AnchorPane getStatusBarAnchor() {
		return statusBarAnchor;
	}

	public Slider getZoomSlider() {
		return zoomSlider;
	}

	public Pane getPane() {
		return pane;
	}

	public void setWidth(double width) {
		statusBarAnchor.setPrefWidth(width);

	}



	public void setShapeWidth(Double widthshape) {
		
		
		shapeWidth.setText("Width : "+Math.round(widthshape) );
	}


	public void setShapeHeight(Double heightshape) {
		shapeHeight.setText("Height : "+Math.round(heightshape));
	}

	
	
	
	

}
