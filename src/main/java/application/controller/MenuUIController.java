package application.controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import application.model.FileManager;
import application.model.Layout;
import application.model.MyShape;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;

public class MenuUIController {
	private HorizontalRulerUIControler rulerUIControler;
	private VerticalRulerUIControler verticalRulerUIControler;
	private CanevasController canevasController;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Pane pane;
	@FXML
	private MenuBar menu;
	@FXML
	private MenuItem unPoint;
	@FXML
	private MenuItem deuxPoints;
	@FXML
	private MenuItem quatrePoints;
	@FXML
	private MenuItem sixPoints;
	@FXML
	private MenuItem huitPoints;
	@FXML
	private MenuItem dixPoints;
	@FXML
	private CheckMenuItem activate;
	@FXML
	private CheckMenuItem desactivate;
	@FXML
	private MenuItem undoMenuItem;
	@FXML
	private MenuItem redoMenuItem;
	@FXML private RadioMenuItem showGrid;
	@FXML
	private MenuItem copyMenuItem;
	private ArrayList<ColumnConstraints> cols= new ArrayList<ColumnConstraints>();
	private ArrayList<RowConstraints> rows= new ArrayList<RowConstraints>();
	@FXML
	private MenuItem pastMenuItem;
	@FXML
    private Menu selectLayout;
	@FXML private CheckMenuItem rulerInCm;
	@FXML private CheckMenuItem rulerInInches;
	private FileManager fileManager;
	private String openedFile;
	private ArrayList<MyShape> myList = new ArrayList<>();
	private ChangesController changesController;
	private boolean isInCM=true;
	MyShape myShape;

	public MenuUIController() {
		fileManager = new FileManager();
		openedFile = "";
	}

	@FXML
	void copyAction(ActionEvent event) {
		MyShape selectedShape;
		selectedShape = canevasController.getSelectedShape();

		if (selectedShape != null) {
			myShape = selectedShape.getClone();
			myShape.moveShape(20, 20);
			myShape.updateFields();
		}

	}

	@FXML
	void pastAction(ActionEvent event) {

		if (myShape != null) {

			ArrayList<MyShape> newList = new ArrayList<>();
			newList.addAll(canevasController.getAllShapesList());

			newList.addAll(myList);

			newList.add(myShape.getClone());

			canevasController.refresh(newList);

			canevasController.setDraggableMode(true);

		}

	}
	

	@FXML
	void selectedPen(ActionEvent event) {

		unPoint.setOnAction(eventPenSize);
		deuxPoints.setOnAction(eventPenSize);
		quatrePoints.setOnAction(eventPenSize);
		sixPoints.setOnAction(eventPenSize);
		huitPoints.setOnAction(eventPenSize);
		dixPoints.setOnAction(eventPenSize);

	}

	EventHandler<ActionEvent> eventPenSize = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {

			String s = ((MenuItem) e.getSource()).getText();

			String[] separated = s.split(" ");

			int val = Integer.parseInt(separated[0]);

			canevasController.setStrokeSize(val);

			if (canevasController.getSelectedShape() != null) {
				canevasController.getChangesController().saveStateToUndo();
				canevasController.getSelectedShape().changeStrokeWidth(val);
				canevasController.setStrokeSize(val);

			}
		}
	};

	@FXML
	void rotateSelected(ActionEvent event) {
		this.canevasController.getSelectedShape().rotateShape();
	}

	@FXML
	void clearCanevas(ActionEvent event) {
		this.changesController.saveStateToUndo();
		this.canevasController.clearAll();
	}
	@FXML
	void showRulesInCM() {
		if(rulerInCm.isSelected()) {
			rulerUIControler.setUnits(38, 3.8,19);
			rulerUIControler.drawRules();
			verticalRulerUIControler.setUnits(38, 3.8,19);
			verticalRulerUIControler.drawRules();
			isInCM=true;
			cleanGridPane();
			showGridLines();
			rulerInInches.setSelected(false);
		}else hideRulers() ;
	}
	
	
	@FXML
	void showRulerInInches() {
		if(rulerInInches.isSelected()) {
			rulerUIControler.setUnits(96, 9.6,48);
			rulerUIControler.drawRules();
			verticalRulerUIControler.setUnits(96, 9.6,48);
			verticalRulerUIControler.drawRules();
			isInCM=false;
			cleanGridPane();
			showGridLines();
			rulerInCm.setSelected(false);
		}else hideRulers() ;
	}
	
	private void hideRulers() {
		if(!rulerInInches.isSelected() && !rulerInCm.isSelected()) {
			rulerUIControler.getRulerCanvas().getGraphicsContext2D().clearRect(0, 0, rulerUIControler.getRulerCanvas().getWidth(),  rulerUIControler.getRulerCanvas().getHeight());
			verticalRulerUIControler.getRulerCanvas().getGraphicsContext2D().clearRect(0, 0, verticalRulerUIControler.getRulerCanvas().getWidth(),  verticalRulerUIControler.getRulerCanvas().getHeight());		
				}
	}


	@FXML
	void activate(ActionEvent event) {
		String id = ((CheckMenuItem) event.getSource()).getId();
		if (id.equals("activate")) {
			this.canevasController.setUsingCenter(true);
			this.activate.setSelected(true);
			this.desactivate.setSelected(false);
		} else {
			this.canevasController.setUsingCenter(false);
			this.activate.setSelected(false);
			this.desactivate.setSelected(true);
		}

	}

	@FXML
	void moveToFront(ActionEvent event) {
		this.changesController.saveStateToUndo();
		canevasController.moveSelectedToFront();
	}

	@FXML
	void moveToBack(ActionEvent event) {
		this.changesController.saveStateToUndo();
		canevasController.moveSelectedToBack();
	}

	@FXML
	void moveLayoutToFront(ActionEvent event) {
		this.changesController.saveStateToUndo();
		canevasController.moveSelectedLayoutToFront();
	}

	@FXML
	void moveLayoutToBack(ActionEvent event) {
		this.changesController.saveStateToUndo();
		canevasController.moveSelectedLayoutToBack();
	}

	
	public CanevasController getCanevasController() {
		return canevasController;
	}

	public void setCanevasController(CanevasController canevasController) {
		this.canevasController = canevasController;
	}

	@FXML
	void saveAsFile(ActionEvent event) throws ParserConfigurationException {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(canevasController.getStage());
		fileManager.saveTextToFile(file.getAbsolutePath(), canevasController.getAllShapesList());
	}

	@FXML
	void newPage(ActionEvent event) throws ParserConfigurationException {
		showDialog();
		canevasController.clearAll();
	}

	@FXML
	void saveFile(ActionEvent event) throws ParserConfigurationException {
		if (openedFile.equals("")) {
			FileChooser fileChooser = new FileChooser();

			// Set extension filter for text files
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			// Show save file dialog
			File file = fileChooser.showSaveDialog(canevasController.getStage());
			fileManager.saveTextToFile(file.getAbsolutePath(), canevasController.getAllShapesList());
			openedFile = file.getAbsolutePath();
		} else {
			fileManager.saveTextToFile(openedFile, canevasController.getAllShapesList());}}

	@FXML
	void importFile(ActionEvent event) {
		try {
			fileManager.importFromFile(canevasController.getStage(), myList);
			ArrayList<MyShape> newList = new ArrayList<>();
			newList.addAll(canevasController.getAllShapesList());
			newList.addAll(myList);

			canevasController.refresh(newList);

		} catch (Exception e) {
			Logger logger = Logger.getGlobal();
			logger.log(Level.SEVERE, "Exception during importing file");
		}
	}

	@FXML
	void openFile(ActionEvent event) throws ParserConfigurationException {

		showDialog();

		myList = new ArrayList<>();

		fileManager.importFromFile(canevasController.getStage(), myList);
		canevasController.refresh(myList);

	}

	private void showDialog() throws ParserConfigurationException {
		if (!canevasController.getAllShapesList().isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Your changes will be lost if you do not save them.");
			alert.setContentText("do you want to save the changes?");

			ButtonType buttonTypeOne = new ButtonType("Yes");
			ButtonType buttonTypeTwo = new ButtonType("No");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent()) {
				if (result.get() == buttonTypeOne) {
					saveFile(null);
				} else {
					if (result.get() == buttonTypeTwo) {
						canevasController.clearAll();
					}
				}
			}

		}
	}

	@FXML
	void saveAsAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(canevasController.getStage());

		if (file != null) {
			try {
				WritableImage writableImage = new WritableImage(770, 540);
				canevasController.getCanvas().snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				ImageIO.write(renderedImage, "png", file);
			} catch (Exception e) {
				Logger logger = Logger.getGlobal();
				logger.log(Level.SEVERE, "Exception during saving file");
			}
		}
	}

	@FXML
	void undoAction(ActionEvent event) {
		changesController.undo();
		updateMenuItemState();
	}

	@FXML
	void redoAction(ActionEvent event) {
		changesController.redo();
		updateMenuItemState();
	}

	public void updateMenuItemState() {
		undoMenuItem.setDisable(changesController.getUndoList().isEmpty());

		redoMenuItem.setDisable(changesController.getRedoList().isEmpty());
	}

	public void setWidth(double width) {
		pane.setPrefWidth(width);
		menu.setPrefWidth(width);
		anchorPane.setPrefWidth(width);
	}

	@FXML
	void moveForward(ActionEvent event) {
		canevasController.moveForward();
	}

	@FXML
	void moveBackward(ActionEvent event) {
		canevasController.moveBackward();
	}

	@FXML
	void moveLayoutForward(ActionEvent event) {
		canevasController.moveLayoutForward();
	}

	@FXML
	void moveLayoutBackward(ActionEvent event) {
		canevasController.moveLayoutBackward();
	}

	
	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public Pane getPane() {
		return pane;
	}

	public MenuBar getMenu() {
		return menu;
	}

	public void setChangesController(ChangesController changesController) {
		this.changesController = changesController;
		this.changesController.setMenuController(this);
	}
	public void setRulerUIControler(HorizontalRulerUIControler rulerUIControler) {
		this.rulerUIControler = rulerUIControler;
	}
	
	public void setVerticalRulerUIControler(VerticalRulerUIControler verticalRulerUIControler) {
		this.verticalRulerUIControler = verticalRulerUIControler;
	}
	
	@FXML
	void deleteLayout(ActionEvent event) {
		for (MenuItem item : selectLayout.getItems()) {
			CheckMenuItem it = (CheckMenuItem) item;
			if(it.isSelected()) {
				canevasController.removeLayout(it.getText());
			}
		}
		refreshSelectLayoutList(canevasController.getLayoutsList());

	}

	@FXML
	void newLayout(ActionEvent event) {
		canevasController.createNewLayout();
		refreshSelectLayoutList(canevasController.getLayoutsList());
	}
	
	@FXML
	void showGridLines() { 
		 long numCols=0;
		 long numRows=0;
		if(showGrid.isSelected()) {
			rows.clear();
			cols.clear();
			if(isInCM) {
				numCols = Math.round(canevasController.getGridPane().getWidth()/38);
				numRows = Math.round(canevasController.getGridPane().getHeight()/38);
			}
			else {
				numCols = Math.round(canevasController.getGridPane().getWidth()/96);
				numRows = Math.round(canevasController.getGridPane().getHeight()/96);
			}
			 for (int i = 0; i < numCols; i++) {
		            ColumnConstraints colConst = new ColumnConstraints();
		            colConst.setPercentWidth(100.0 / numCols);
		            canevasController.getGridPane().getColumnConstraints().add(colConst);
		            cols.add(colConst);    
			 }
		        for (int i = 0; i < numRows; i++) {
		            RowConstraints rowConst = new RowConstraints();
		            rowConst.setPercentHeight(100.0 / numRows);
		            canevasController.getGridPane().getRowConstraints().add(rowConst);         
		            rows.add(rowConst); 
		        }
		        showGridOnCanvas(true);
		}else {
			cleanGridPane();
			 showGridOnCanvas(false);
		}
		
	}
	public void showGridOnCanvas(boolean e) {
		canevasController.getGridPane().setGridLinesVisible(e);
		canevasController.setUsingMagnetism(e);
	}

	
	
	

	@FXML
    void groupSelection(ActionEvent event) {
    	canevasController.groupSelection();
    }
    
    @FXML
    void ungroupSelection(ActionEvent event) {
    	canevasController.ungroupSelection();
    }

    @FXML
    void selectLayout(ActionEvent event) {
    	if(selectLayout.getItems().size() == 0)
    		refreshSelectLayoutList(canevasController.getLayoutsList());
    }
    
    void refreshSelectLayoutList(ArrayList<Layout> list) {
    	selectLayout.getItems().removeAll();
    	selectLayout.getItems().clear();
    	int i = 0;
    	for (Layout layout : list) {
			selectLayout.getItems().add(new CheckMenuItem(layout.getLayoutName()));
			i++;
    	}
    }

	public ArrayList<Integer> getSelectedLayouts() {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		int i = 0;
		for (MenuItem item : selectLayout.getItems()) {
			CheckMenuItem it = (CheckMenuItem) item;
			if(it.isSelected()) {
				list.add(i);
			}
			i++;
		}
		
		return list;
	}
	public void cleanGridPane() {
		for(RowConstraints row: rows) {
			for(ColumnConstraints col: cols) {	
			canevasController.getGridPane().getRowConstraints().remove(row);
			canevasController.getGridPane().getColumnConstraints().remove(col);
		}}
	}
}
