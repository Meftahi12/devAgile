package application.model;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class ComposedShape implements MyShape{
	private ArrayList<MyShape> shapesList;
	private ComposedShape root;
	private String layoutName;
	@Override
	public double getStartX() {
		// TODO Auto-generated method stub
		double min = Double.MAX_VALUE;
		for (MyShape myShape : shapesList) {
			min = Math.min(min, myShape.getStartX());
		}
		return min;
	}

	@Override
	public double getStartY() {
		// TODO Auto-generated method stub
		double min = Double.MAX_VALUE;
		for (MyShape myShape : shapesList) {
			min = Math.min(min, myShape.getStartY());
		}
		return min;

	}

	@Override
	public double getEndX() {
		// TODO Auto-generated method stub
		double max = 0;
		for (MyShape myShape : shapesList) {
			max = Math.max(max, myShape.getEndX());
		}
		return max;

	}

	@Override
	public double getEndY() {
		// TODO Auto-generated method stub
		double max = 0;
		for (MyShape myShape : shapesList) {
			max = Math.max(max, myShape.getEndY());
		}
		return max;
	}


	@Override
	public void setSelected(boolean b) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.setSelected(b);
		}
	}

	@Override
	public void disableDraggable() {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.disableDraggable();
		}

	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			if(!myShape.isSelected())
				return false;
		}
		return true;

	}

	@Override
	public void setFiller(Filler filler) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.setFiller(filler);
		}

	}

	@Override
	public void setPattern(boolean b) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.setPattern(b);
		}
	}

	@Override
	public void updateFields() {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.updateFields();
		}

	}

	@Override
	public void draw(ObservableList<Node> children) {
		// TODO Auto-generated method stub
		for (MyShape shape : shapesList) {
			shape.draw(children);
		}
	}

	@Override
	public void changeStrokeColor(Color value) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.changeStrokeColor(value);
		}
	}

	

	@Override
	public void setFill(Paint pattern) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.setFill(pattern);
		}
	}

	@Override
	public void rotateShape() {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.rotateShape();
		}
	}

	@Override
	public void moveShape(int noStep, int i) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.moveShape(noStep, i);
		}
	}

	@Override
	public void enlarge() {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.enlarge();
		}
	}

	@Override
	public void changeStrokeWidth(int val) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			myShape.changeStrokeWidth(val);
		}
	}

	public void setList(ArrayList<MyShape> selectedList) {
		// TODO Auto-generated method stub
		shapesList = selectedList;
	}
	public ArrayList<SingleShape> getList() {
		ArrayList<SingleShape> list = new ArrayList<SingleShape>();
		for (MyShape myShape : shapesList) {
			list.addAll(myShape.getList());
		}
		return list;
	}

	@Override
	public void setRoot(ComposedShape compShape) {
		// TODO Auto-generated method stub
		root = compShape;
	}

	@Override
	public void dragOnPressed(double sceneX, double sceneY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOnDragged(double sceneX, double sceneY, Shape source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOnReleased(Paint color, double str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resizeOnDragged(double sceneX, double sceneY, Shape shape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resizeOnPressed(double sceneX, double sceneY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resizeOnReleased(Paint color, double str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInside(Double startX, Double startY, Double currentX, Double currentY) {
		// TODO Auto-generated method stub
		for (MyShape myShape : shapesList) {
			if(myShape.isInside(startX, startX, currentX, currentY))
				return true;
		}
		return false;

	}

	@Override
	public MyShape getClone() {
		// TODO Auto-generated method stub
		ArrayList<MyShape> list = new ArrayList<MyShape>();
		for (MyShape myShape : shapesList) {
			list.add(myShape.getClone());
		}
		ComposedShape shape = new ComposedShape();
		shape.setList(list);
		return shape;
	}

	@Override
	public String getLayout() {
		// TODO Auto-generated method stub
		return layoutName;
	}

}
