package application.model;

import java.util.prefs.Preferences;

import application.controller.CanevasController;
import javafx.scene.paint.Color;

public class Canevas {
	double strokeSize;
	Color shapeFillColor;
	Color shapeStrokeColor;
	double sceneWidth;
	double sceneHeight;
	double zoomValue;
	String saveRepository;
	boolean gridActivated;
	boolean rulerShown;
	boolean filled;

	public Canevas(CanevasController canevasController) {
		this.sceneHeight =600; // exception in getStage canevasController.getStage().getHeight();
		this.sceneWidth = 500;// exception in getStage canevasController.getStage().getWidth();
		this.zoomValue = canevasController.getCanvas().getScaleX();
		this.gridActivated = true ;
		this.rulerShown =true ; 
		this.shapeStrokeColor = canevasController.getStrokeColorPicker().getValue();
		this.shapeFillColor = canevasController.getFillColorPicker().getValue();
		this.strokeSize = canevasController.getStrokeSize();
		this.filled = canevasController.isFilled();
		
		//// this three values need to change 
		this.saveRepository = " directory ";
		
	}

	public Canevas(Preferences importedPreferences) {
		initializeFromPrefs(importedPreferences);
	}

	private void initializeFromPrefs(Preferences importedPreferences) {
		this.sceneHeight = importedPreferences.getDouble("sceneHeight", 500);
		this.sceneWidth = importedPreferences.getDouble("sceneWidth", 770);
		this.zoomValue = importedPreferences.getDouble("zoomValue", 770);
		
		this.gridActivated = importedPreferences.get("gridActivated", "true").equals("true");
		this.rulerShown = importedPreferences.get("rulerShown", "true").equals("true");
		this.filled = importedPreferences.get("filled", "true").equals("true");

		this.shapeFillColor = new Color(importedPreferences.getDouble("shapeFillColorR", 0),
				importedPreferences.getDouble("shapeFillColorG", 0),
				importedPreferences.getDouble("shapeFillColorB", 0), 
				1);
		this.shapeStrokeColor = new Color(importedPreferences.getDouble("shapeStrokeColorR", 0),
				importedPreferences.getDouble("shapeStrokeColorG", 0),
				importedPreferences.getDouble("shapeStrokeColorB", 0), 
				1);
		
		this.strokeSize = importedPreferences.getDouble("strokeSize", 1);
		this.saveRepository = importedPreferences.get("saveRepository", "");
		
	}

	public Preferences getPrefs() {
		Preferences prefs = Preferences.userNodeForPackage(Canevas.class);

		prefs.putDouble("sceneWidth", sceneWidth);
		prefs.putDouble("sceneHeight", sceneHeight);
		prefs.putDouble("zoomValue", zoomValue);

		prefs.put("saveRepository", saveRepository);

		prefs.put("gridActivated", gridActivated+"");
		prefs.put("rulerShown", rulerShown+"");
		prefs.put("filled", filled+"");
		
		prefs.putDouble("shapeFillColorR", shapeFillColor.getRed());
		prefs.putDouble("shapeFillColorG", shapeFillColor.getGreen());
		prefs.putDouble("shapeFillColorB", shapeFillColor.getBlue());

		prefs.putDouble("shapeStrokeColorR", shapeStrokeColor.getRed());
		prefs.putDouble("shapeStrokeColorG", shapeStrokeColor.getGreen());
		prefs.putDouble("shapeStrokeColorB", shapeStrokeColor.getBlue());

		prefs.putDouble("strokeSize", strokeSize);
		return prefs;
	}

	public double getStrokeSize() {
		return strokeSize;
	}

	public Color getShapeFillColor() {
		return shapeFillColor;
	}

	public Color getShapeStrokeColor() {
		return shapeStrokeColor;
	}

	public double getSceneWidth() {
		return sceneWidth;
	}

	public double getSceneHeight() {
		return sceneHeight;
	}

	public double getZoomValue() {
		return zoomValue;
	}

	public String getSaveRepository() {
		return saveRepository;
	}

	public boolean isGridActivated() {
		return gridActivated;
	}

	public boolean isRulerShown() {
		return rulerShown;
	}
	
	public boolean isFilled() {
		return filled;
	}
	

}
