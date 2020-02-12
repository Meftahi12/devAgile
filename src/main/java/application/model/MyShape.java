package application.model;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public interface MyShape {

	double getStartX();

	double getStartY();

	double getEndX();

	double getEndY();

	void setSelected(boolean b);

	void disableDraggable();

	boolean isSelected();

	void setPattern(boolean b);

	void updateFields();

	void draw(ObservableList<Node> children);

	void changeStrokeColor(Color value);

	void rotateShape();

	void moveShape(int noStep, int i);

	void enlarge();

	void changeStrokeWidth(int val);

	void setFiller(Filler filler);

	void setFill(Paint pattern);

	void setRoot(ComposedShape compShape);

	void dragOnPressed(double sceneX, double sceneY);

	void dragOnDragged(double sceneX, double sceneY, Shape source);

	void dragOnReleased(Paint color, double str);

	void resizeOnDragged(double sceneX, double sceneY, Shape shape);

	void resizeOnPressed(double sceneX, double sceneY);

	void resizeOnReleased(Paint color, double str);

	boolean isInside(Double startX, Double startY, Double currentX, Double currentY);

	public MyShape getClone();
		
	ArrayList<SingleShape> getList();

	String getLayout();
	
}
