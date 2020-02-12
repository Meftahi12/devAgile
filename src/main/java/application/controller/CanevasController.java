package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import application.model.ColorFiller;
import application.model.ComposedShape;
import application.model.Filler;
import application.model.Layout;
import application.model.MyRectangle;
import application.model.MyShape;
import application.model.PatternFiller;
import application.model.SingleShape;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.input.ScrollEvent;



public class CanevasController implements Initializable {

	int prefHeight = 500;
	int prefWidth = 770;
	private boolean isSelectionMode;
	private static final double DEFAULT_ROTATION = 0;

	private static final double MAX_SCALE = 10.0d;
	private static final double MIN_SCALE = .1d;

	private ArrayList<Integer> selectedList;

	@FXML
	private Group root;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private AnchorPane canvas;// The canvas on which the image is drawn.

	@FXML
	private ScrollPane scrollPane;

	private boolean isFilled=false;
	@FXML private GridPane gridPane;

	private AnchorPane overlay;// A transparent canvas that lies on top of


	private Double startX;// start point of drag
	private Double startY; // start point of drag

	private Double currentX;// current mouse position during a drag
	private Double currentY;// current mouse position during a drag

	private SingleShape currentShape = null; // The current drawing shape.

	//private ArrayList<MyShape> shapesList;
	private ArrayList<Layout> layoutsList;
	
	private ColorPicker fillColorPicker;

	private ColorPicker strokeColorPicker;

	private double strokeSize=1;

	private String selectedPatternUrl = "";

	private boolean isUsingCenter = true;
	private boolean isUsingMagnetism = false;

	private ChangesController changesController;
	
	private StatusBarController stausBarController;
	private Layout currLayout;
	private int LayoutsCounter = 0;

	public ColorPicker getStrokeColorPicker() {
		return strokeColorPicker;
	}

	public void setStrokeColorPicker(ColorPicker strokeColorPicker) {
		this.strokeColorPicker = strokeColorPicker;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		layoutsList = new ArrayList<>();
		currLayout = new Layout("Layout0");
		layoutsList.add(currLayout);
		canvas.setOnMouseDragged(e -> mouseDragged(e));
		canvas.setOnMousePressed(e -> mousePressed(e));
		canvas.setOnMouseReleased(e -> mouseReleased(e));

		overlay = new AnchorPane();

		changesController = new ChangesController(this);
	}



	void mousePressed(MouseEvent evt) {
		this.clearSelection();
		startX = currentX = evt.getX();

		startY = currentY = evt.getY();
		
		// if selectedShape is null do nothing or write with pen ( en fonction du mode de selection )
		
	}

	public void mouseDragged(MouseEvent evt) {
		currentX = evt.getX();
		currentY = evt.getY();
		if(currentShape!=null ) {
			
			overlay.getChildren().clear();
			if(!isFilled) {
				fillColorPicker.setValue(Color.TRANSPARENT);
			}
			currentShape.setProperties(startX, startY, currentX, currentY, strokeColorPicker.getValue(), getStrokeSize(), getCurrentFiller(),DEFAULT_ROTATION, currLayout.getLayoutName());
			if(isSelectionMode)
				currentShape.createShape(false, isUsingMagnetism, gridPane);
			else
			currentShape.createShape(isUsingCenter, isUsingMagnetism, gridPane);
			if (root.getChildren().size()<2) { //means it's the first time
				root.getChildren().add(overlay);
			}
			overlay.getChildren().add(currentShape.getShape());
			
			stausBarController.setShapeWidth(currentShape.getWidth());
			stausBarController.setShapeHeight(currentShape.getHeight());

		}
		

		

	}



	private Filler getCurrentFiller() {
		Filler filler;

		if(selectedPatternUrl.equals("")) {
			filler = new ColorFiller(fillColorPicker.getValue(), currentShape);
		}
		else
			filler = new PatternFiller(selectedPatternUrl, currentShape);

		return filler;
	}

	public void mouseReleased(MouseEvent evt) {
		if (root.getChildren().size()>1) { //means it's the first time
			root.getChildren().remove(root.getChildren().size()-1);
			currentShape.setProperties(
					startX, 
					startY, 
					currentX, 
					currentY, 
					strokeColorPicker.getValue(), 
					getStrokeSize(), 
					getCurrentFiller(), 
					DEFAULT_ROTATION,
					currLayout.getLayoutName());
			if(isSelectionMode)
				currentShape.createShape(false, isUsingMagnetism, gridPane);
			else
			currentShape.createShape(isUsingCenter, isUsingMagnetism, gridPane);
			MyShape lastDrawnShape=currentShape;
			changesController.saveStateToUndo();
			currLayout.addShape(lastDrawnShape);
			currentShape= currentShape.clone();
			canvas.getChildren().add(((SingleShape)(currLayout.getLastShape())).getShape());
			
			((SingleShape)(getAllShapesList().get(getAllShapesList().size()-1))).setHadelsVisible(false);
			((SingleShape)(getAllShapesList().get(getAllShapesList().size()-1))).setHandelsPos();

			canvas.getChildren().addAll(((SingleShape)(getAllShapesList().get(getAllShapesList().size()-1))).getCircles());

		}	

	}

	


	public static double clamp( double value, double min, double max) {

		if( Double.compare(value, min) < 0)
			return min;

		if( Double.compare(value, max) > 0)
			return max;

		return value;
	}


	private javafx.event.EventHandler<ScrollEvent> onScrollEventHandler = new javafx.event.EventHandler<ScrollEvent>() {

		@Override
		public void handle(ScrollEvent event) {

			double delta = 1.2;

			double scale = canvas.getScaleY(); // currently we only use Y, same value is used for X
			
			if (event.getDeltaY() < 0)
				scale /= delta;
			else
				scale *= delta;

			scale = clamp( scale, MIN_SCALE, MAX_SCALE);

			canvas.scaleXProperty().set(scale);
			canvas.scaleYProperty().set(scale);
			overlay.scaleXProperty().set(scale);
			overlay.scaleYProperty().set(scale);


			event.consume();

		}

	};
	private MenuUIController menuUIController;

	public MyShape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(SingleShape currentShape) {
		this.currentShape = currentShape;
	}

	public void setFillColorPicker(ColorPicker colorPicker) {
		this.fillColorPicker = colorPicker;
	}

	public void setDraggableMode(boolean value) {
		if(value) {
			for (SingleShape shape : getSingleShapesList()) {
				
				
				shape.getShape().setOnMouseDragged(t -> {
				
				
					ComposedShape compShape = shape.getRoot();
					if(compShape == null)
						shape.dragOnDragged(t.getSceneX(), t.getSceneY(), (Shape)t.getSource());
					else {
						for (MyShape currShape : compShape.getList()) {
							currShape.dragOnDragged(t.getSceneX(), t.getSceneY(), ((SingleShape)currShape).getShape());
						}
					}

				
				});
				
				
				shape.getShape().setOnMousePressed(t ->{
					ComposedShape compShape = shape.getRoot();
					if(compShape == null)
						shape.dragOnPressed(t.getSceneX(), t.getSceneY());
					else {
						for (MyShape currShape : compShape.getList()) {
							currShape.dragOnPressed(t.getSceneX(), t.getSceneY());
						}
					}
				});
				
				
				shape.getShape().setOnMouseReleased(t -> {
					changesController.saveStateToUndo();
					
					ComposedShape compShape = shape.getRoot();
					if(compShape == null) {
						double str = shape.getStrokeSize();
						Color color=(Color) shape.getStrokeColor();
					shape.dragOnReleased(color, str);

					}
					else {
						for (MyShape currShape : compShape.getList()) {
							double str = ((SingleShape) currShape).getStrokeSize();
							Color color=(Color) ((SingleShape) currShape).getStrokeColor();
							currShape.dragOnReleased(color, str);
						}
					}

					stausBarController.setShapeWidth(shape.getWidth());
					stausBarController.setShapeHeight(shape.getHeight());
				});
			}
		}
		else {
			for (MyShape shape : getAllShapesList()) {
				shape.setSelected(false);
				shape.disableDraggable();
			}
		}
	}

	ArrayList<MyShape> getAllShapesList() {
		// TODO Auto-generated method stub
		ArrayList<MyShape> list = new ArrayList<MyShape>();
		for (Layout layout : layoutsList) {
			list.addAll(layout.getShapesList());
		}
		return list;
	}

	private ArrayList<SingleShape> getSingleShapesList() {
		// TODO Auto-generated method stub
		ArrayList<SingleShape> list = new ArrayList<SingleShape>();
		for (MyShape myShape : getAllShapesList()) {
			list.addAll(myShape.getList());
		}
		return list;
	}
	

	public boolean isFilled() {
		return isFilled;
	}
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public void clearAll() {
		this.getLayoutsList().clear();
		this.canvas.getChildren().clear();
		canvas.getChildren().add(gridPane);
		LayoutsCounter++;
		currLayout = new Layout("Layout"+LayoutsCounter);
		layoutsList.add(currLayout);
	}

	ArrayList<Layout> getLayoutsList() {
		// TODO Auto-generated method stub
		return layoutsList;
	}

	public void clearSelection() {
		for (MyShape myShape : getAllShapesList()) {
			myShape.setSelected(false);
		}
	}

	public MyShape getSelectedShape() {
		for (MyShape myShape : getAllShapesList()) {
			if (myShape.isSelected()) {
				return myShape;

			}
		}
		return null ;
	}

	public double getStrokeSize() {
		return strokeSize;
	}

	public ColorPicker getFillColorPicker() {
		return fillColorPicker;
	}

	public void setStrokeSize(double strokeSize) {
		this.strokeSize = strokeSize;
	}



	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public Stage getStage() {

		return (Stage) canvas.getScene().getWindow();				// ceci est la mienne (Ayoub)	
	}

	public void refresh(List<MyShape> theChangeToGoTo) {
		System.out.println(theChangeToGoTo.size());
		clearAll();
		for (MyShape myShape : theChangeToGoTo) {
			Layout layout = findLayoutByName(myShape.getLayout());
			if(layout == null) {
				layout = new Layout(myShape.getLayout());
				layoutsList.add(layout);
			}
			layout.addShape(myShape);
		}

		for(int i=0;i<getAllShapesList().size();i++){
			getAllShapesList().get(i).draw(this.canvas.getChildren());
		}
	}



	private Layout findLayoutByName(String string) {
		// TODO Auto-generated method stub
		for (Layout layout : layoutsList) {
			if(layout.getLayoutName().equals(string))
				return layout;
		}
		return null;
	}

	public void setResizeMode(boolean value) {
		if (value) {
			for (SingleShape shape : getSingleShapesList()) {
				shape.getShape().setOnMouseDragged(t ->{
					ComposedShape compShape = shape.getRoot();
					if(compShape == null) {
						shape.resizeOnDragged(t.getSceneX(), t.getSceneY(), (Shape)t.getSource());
					
						
						shape.setHandelsPos();			
						shape.setHadelsVisible(true);
						}
						
					
					else {
						for (MyShape currshape : compShape.getList()) {
							currshape.resizeOnDragged(t.getSceneX(), t.getSceneY(), ((SingleShape)currshape).getShape());
							
						}
					}
				});
				
				shape.getShape().setOnMousePressed(t->{
					ComposedShape compShape = shape.getRoot();
					if(compShape == null)
						shape.resizeOnPressed(t.getSceneX(), t.getSceneY());
					else {
						for (MyShape currShape : compShape.getList()) {
							currShape.resizeOnPressed(t.getSceneX(), t.getSceneY());
						}
					}

				});

				shape.getShape().setOnMouseReleased(t -> {
					changesController.saveStateToUndo();
					
					ComposedShape compShape = shape.getRoot();
					if(compShape == null) {
						double str = shape.getStrokeSize();
						Color color=(Color) shape.getStrokeColor();
					shape.resizeOnReleased(color, str);
					shape.setHadelsVisible(false);

					}
					else {
						for (MyShape currshape : compShape.getList()) {
							double str = ((SingleShape) currshape).getStrokeSize();
							Color color=(Color) ((SingleShape) currshape).getStrokeColor();
							currshape.resizeOnReleased(color, str);
						}
					}
					
					stausBarController.setShapeWidth(shape.getWidth());
					stausBarController.setShapeHeight(shape.getHeight());
				});

			}
		} else {
			for (MyShape shape : getAllShapesList()) {
				shape.setSelected(false);
				((SingleShape)shape).setHadelsVisible(false);
				shape.disableDraggable();
			}
		}
	}

	public void setImagePattern(String selectedPatternUrl) {
		this.selectedPatternUrl = selectedPatternUrl;
	}

	public String getImagePattern() {
		return selectedPatternUrl;
	}

	public AnchorPane getCanvas() {
		return canvas;
	}

	public void setCanvas(AnchorPane canvas) {
		this.canvas = canvas;
	}

	private int getSelectedShapeIndex() {
		for (int i = 0; i < currLayout.getShapesList().size(); i++) {
			if (currLayout.getShapesList().get(i).isSelected()) {
				return i;

			}
		}
		return -1 ;
	}





	public void resize(double parseDouble) {
		double scale = parseDouble / 100.0; // currently we only use Y, same value is used for X        
		canvas.scaleXProperty().set(scale);
		canvas.scaleYProperty().set(scale);


	}

	public void setUsingCenter(boolean b) {
		isUsingCenter = b;
	}
	public boolean isUsingCenter() {
		return isUsingCenter;
	}

	public void fillSelectedShapeWithColor(Color value) {
		getSelectedShape().setFill(value);
		getSelectedShape().setFiller(new ColorFiller(value, getSelectedShape()));
		getSelectedShape().setPattern(false);
		getSelectedShape().updateFields();
	}

	public void changeSelectedStrokeColor(Color value) {
		getSelectedShape().changeStrokeColor(value);
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void updateCanvasScale(double newValue) {
		this.canvas.scaleXProperty().set( newValue/100.0);
		this.canvas.scaleYProperty().set( newValue/100.0);
		this.overlay.scaleXProperty().set( newValue/100.0);
		this.overlay.scaleYProperty().set( newValue/100.0);
	}

	public ChangesController getChangesController() {
		return changesController;
	}

	public ArrayList<MyShape> getShapesListClone(){
		ArrayList<MyShape> tmpList = new ArrayList<>();
		
		for (MyShape myShape : getAllShapesList()) {
			tmpList.add(myShape.getClone());
		}
		return tmpList;
	}

	public GridPane getGridPane() {
		return gridPane;
	}
	public void setUsingMagnetism(boolean isUsingMagnetism) {
		this.isUsingMagnetism = isUsingMagnetism;
	} 
	/*
		 * public void updateScreenState(Canevas canevas) {
		 * this.fillColorPicker.setValue(canevas.getShapeFillColor());
		 * this.strokeColorPicker.setValue(canevas.getShapeStrokeColor());
		 * this.strokeSize = canevas.getStrokeSize(); // update Width // update Height
		 * 
		 * // update zoomvalue // update save repo this.isFilled = canevas.isFilled();
		 * // update rulerShown // update gridActivated
		 * 
		 * }
		 */

	/**
	 * @return the stausBarController
	 */
	public StatusBarController getStausBarController() {
		return stausBarController;
	}

	/**
	 * @param stausBarController the stausBarController to set
	 */
	public void setStausBarController(StatusBarController stausBarController) {
		this.stausBarController = stausBarController;
	}

	public void groupSelection() {
		// TODO Auto-generated method stub
		ComposedShape compShape = new ComposedShape();
		ArrayList<MyShape> list = new ArrayList<MyShape>();
		for (Integer index : selectedList) {
			ArrayList<SingleShape> currList = currLayout.getShape(index).getList();
			for (MyShape myShape : currList) {
				myShape.setRoot(compShape);
				list.add(myShape);
			}
			currLayout.removeShape(index.intValue());
		}
		compShape.setList(list);
		currLayout.addShape(compShape);
	}

	public void ungroupSelection() {
		// TODO Auto-generated method stub
		for (Integer index : selectedList) {
			ArrayList<SingleShape> list = currLayout.getShape(index).getList();
			for (MyShape myShape : list) {
				myShape.setRoot(null);
				currLayout.addShape(myShape);
			}
			currLayout.removeShape(index.intValue());
		}
	}

	public void setSelectionMode(boolean b) {
		isSelectionMode = b;
		// TODO Auto-generated method stub
		if(b) {
			canvas.setOnMouseDragged(e -> mouseDraggedSelection(e));
			canvas.setOnMousePressed(e -> mousePressedSelection(e));
			canvas.setOnMouseReleased(e -> mouseReleasedSelection(e));	
		}
		else {
			canvas.setOnMouseDragged(e -> mouseDragged(e));
			canvas.setOnMousePressed(e -> mousePressed(e));
			canvas.setOnMouseReleased(e -> mouseReleased(e));
		}
	}
	
	public void mouseDraggedSelection(MouseEvent evt) {
		currentX = evt.getX();
		currentY = evt.getY();
		if(currentShape==null ) {


		}
		else {
			//the shapes that have to be drew on the overlay canvas
			overlay.getChildren().clear();
			fillColorPicker.setValue(Color.TRANSPARENT);
			currentShape.setProperties(startX, startY, currentX, currentY, strokeColorPicker.getValue(), getStrokeSize(), getCurrentFiller(),DEFAULT_ROTATION, currLayout.getLayoutName());
			if(isSelectionMode)
				currentShape.createShape(false, isUsingMagnetism, gridPane);
			else
				currentShape.createShape(isUsingCenter, isUsingMagnetism, gridPane);
			if (root.getChildren().size()<2) { //means it's the first time
				root.getChildren().add(overlay);
			}
			overlay.getChildren().add(currentShape.getShape());
			
			

		}
	}

	void mousePressedSelection(MouseEvent evt) {

		this.clearSelection();

		startX = currentX = evt.getX();

		startY = currentY = evt.getY();
		setCurrentShape(new MyRectangle());
	}
	
	public void mouseReleasedSelection(MouseEvent evt) {
		if (root.getChildren().size()>1) { //means it's the first time
			root.getChildren().remove(root.getChildren().size()-1);
			if(startX < currentX) {
				if(startY < currentY) {

				}
				else {
					double temp = startY;
					startY = currentY;
					currentY = temp;
				}
			}
			else {
				if(startY > currentY) {
					double temp = startY;
					startY = currentY;
					currentY = temp;
					temp = startX;
					startX = currentX;
					currentX = temp;
				}
				else {
					double temp = startX;
					startX = currentX;
					currentX = temp;

				}
			}
			selectedList = new ArrayList<Integer>();
			for(int i = currLayout.getShapesList().size() - 1; i >= 0; i--) {
				if(currLayout.getShape(i).isInside(startX, startY, currentX, currentY))
						selectedList.add(i);
			}
		}


	}

	public void createNewLayout() {
		// TODO Auto-generated method stub
		LayoutsCounter++;
		currLayout = new Layout("Layout" + LayoutsCounter);
		layoutsList.add(currLayout);
	}

	public void deleteCurrentLayout() {
		// TODO Auto-generated method stub
		layoutsList.remove(currLayout);
		this.canvas.getChildren().clear();
		for (MyShape myShape : getAllShapesList()) {
			myShape.draw(this.canvas.getChildren());
		}
	}

	public void setMenuUIController(MenuUIController menuUIController) {
		// TODO Auto-generated method stub
		this.menuUIController = menuUIController;
	}

	public void removeLayout(String text) {
		// TODO Auto-generated method stub
		for(int i = layoutsList.size() - 1; i >= 0; i--) {
			if(layoutsList.get(i).getLayoutName().equals(text))
				layoutsList.remove(i);
		}
		this.canvas.getChildren().clear();
		for (MyShape myShape : getAllShapesList()) {
			myShape.draw(this.canvas.getChildren());
		}
	}

	public void moveForward() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		

		if (i!= -1 && i < currLayout.getShapesList().size()-1) {
			MyShape tmp = currLayout.getShape(i);
			currLayout.setShape(i, currLayout.getShape(i+1)) ;
			currLayout.setShape(i + 1, tmp) ;

			this.canvas.getChildren().clear();
			for (MyShape myShape : getAllShapesList()) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	
	public void moveLayoutForward() {
		// TODO Auto-generated method stub
		ArrayList<Integer> selected = menuUIController.getSelectedLayouts();
		for (Integer i : selected) {
			if (i!= -1 && i < layoutsList.size()-1) {
				Layout tmp = layoutsList.get(i);
				layoutsList.set(i, layoutsList.get(i+1)) ;
				layoutsList.set(i + 1, tmp) ;
				this.canvas.getChildren().clear();
				for (MyShape myShape : getAllShapesList()) {
					myShape.draw(this.canvas.getChildren());
				}
			}
		}
		menuUIController.refreshSelectLayoutList(layoutsList);
	}

	public void moveBackward() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i > 0) {
			MyShape tmp = currLayout.getShape(i);
			currLayout.setShape(i, currLayout.getShape(i - 1)) ;
			currLayout.setShape(i - 1, tmp) ;

			this.canvas.getChildren().clear();
			for (MyShape myShape : getAllShapesList()) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	
	public void moveLayoutBackward() {
		// TODO Auto-generated method stub
		ArrayList<Integer> selected = menuUIController.getSelectedLayouts();
		for (Integer i : selected) {
			if (i!= -1 && i > 0) {
				Layout tmp = layoutsList.get(i);
				layoutsList.set(i, layoutsList.get(i - 1)) ;
				layoutsList.set(i - 1, tmp) ;

				this.canvas.getChildren().clear();
				for (MyShape myShape : getAllShapesList()) {
					myShape.draw(this.canvas.getChildren());
				}
			}
		}
		menuUIController.refreshSelectLayoutList(layoutsList);
	}

	public void moveSelectedToFront() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i < currLayout.getShapesList().size()-1) {
			MyShape tmp = currLayout.getShape(i);
			currLayout.removeShape(i);
			currLayout.addShape(tmp); 

			this.canvas.getChildren().clear();
			for (MyShape myShape : getAllShapesList()) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	
	public void moveSelectedLayoutToFront() {
		// TODO Auto-generated method stub
		ArrayList<Integer> selected = menuUIController.getSelectedLayouts();
		for (Integer i : selected) {
			if (i!= -1 && i < currLayout.getShapesList().size()-1) {
				Layout tmp = layoutsList.get(i);
				layoutsList.remove(i.intValue());
				layoutsList.add(tmp); 

				this.canvas.getChildren().clear();
				for (MyShape myShape : getAllShapesList()) {
					myShape.draw(this.canvas.getChildren());
				}
			}
		}
		menuUIController.refreshSelectLayoutList(layoutsList);	
	}
	
	public void moveSelectedToBack() {
		int i =-1; 
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i > 0) {
			MyShape tmp = currLayout.getShape(i);
			currLayout.removeShape(i);
			currLayout.addShape(0, tmp); 

			this.canvas.getChildren().clear();
			for (MyShape myShape : getAllShapesList()) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	public void moveSelectedLayoutToBack() {
		// TODO Auto-generated method stub
		ArrayList<Integer> selected = menuUIController.getSelectedLayouts();
		for (Integer i : selected) {
			if (i!= -1 && i > 0) {
				Layout tmp = layoutsList.get(i);
				layoutsList.remove(i);
				layoutsList.add(0, tmp); 

				this.canvas.getChildren().clear();
				for (MyShape myShape : getAllShapesList()) {
					myShape.draw(this.canvas.getChildren());
				}
			}
		}
		menuUIController.refreshSelectLayoutList(layoutsList);
	}
	
	

}


