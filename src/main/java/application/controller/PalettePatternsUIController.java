package application.controller;


import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.model.PatternFiller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class PalettePatternsUIController {

	@FXML
	private ColorPicker colorPicker;

	@FXML
	private CheckBox filled;

	@FXML
	private ColorPicker strokeColor;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private HBox hBox;

	@FXML
	private Pane pane;

	private CanevasController canevasController;


	@FXML private Button image1;
	@FXML private Button image2;
	@FXML private Button image3;
	@FXML private Button image4;
	@FXML private Button image5;
	@FXML private Button image6;
	@FXML private Button image7;
	@FXML private Button image8;
	@FXML private Button image9;


	@FXML
	private void strokShapeColor(ActionEvent event) {
		if(canevasController.getSelectedShape()!=null) {
			canevasController.changeSelectedStrokeColor(strokeColor.getValue());
		}
	}


	@FXML
	private void pickerShapeColor(ActionEvent event) {
		if(canevasController.getSelectedShape()!=null) {
			canevasController.fillSelectedShapeWithColor(colorPicker.getValue());
			canevasController.setImagePattern("");
		}
	}



	public void setCanevasController(CanevasController canevasController) {
		canevasController.setFillColorPicker(colorPicker);
		canevasController.setStrokeColorPicker(strokeColor);
		this.canevasController=canevasController;
		this.strokeColor.setValue(Color.BLACK);
	}

	public CanevasController getCanevasController() {

		return this.canevasController;
	}

	public void filledChecked() {
		canevasController.setFilled(filled.isSelected());

		canevasController.setImagePattern("");
	}

	@FXML
	private void imageClicked(ActionEvent event) {

		String id = ((Control)event.getSource()).getId();

		if(canevasController.getSelectedShape()!=null ) {

			try {
				getCanevasController().getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().setFill(new ImagePattern(new Image(new FileInputStream(getImagePath(id)))));
				canevasController.getSelectedShape().setFiller(new PatternFiller(getImagePath(id),canevasController.getSelectedShape()));
				canevasController.getSelectedShape().setPattern(true);
			} catch (Exception e) {
				Logger logger = Logger.getGlobal(); 
				logger.log(Level.SEVERE, "Exception");
			}	
		}
		else {
			this.filled.setSelected(false);
			canevasController.setImagePattern(getImagePath(id));
		}		
	}


	public AnchorPane getAnchorPane() {
		return anchorPane;
	}
	public Pane getPane() {
		return pane;
	}
	public HBox getHBox() {
		return hBox;
	}
	private String getImagePath(String id) {
		return "src/main/resources/" + id.charAt(5) + ".jpg";
	}















}