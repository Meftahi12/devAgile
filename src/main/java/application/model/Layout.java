package application.model;

import java.util.ArrayList;
import java.util.Collection;

public class Layout {
	private String layoutName;
	private ArrayList<MyShape> shapesList;
	private boolean isDisplayed;
	
	public Layout(String string, boolean isDisplayed) {
		// TODO Auto-generated constructor stub
		layoutName = string;
		shapesList = new ArrayList<MyShape>();
		this.isDisplayed = isDisplayed;
	}

	public void addShape(MyShape lastDrawnShape) {
		// TODO Auto-generated method stub
		shapesList.add(lastDrawnShape);
	}

	public MyShape getLastShape() {
		// TODO Auto-generated method stub
		return shapesList.get(shapesList.size() - 1);
	}

	public ArrayList<MyShape> getShapesList() {
		// TODO Auto-generated method stub
		return shapesList;
	}

	public MyShape getShape(int i) {
		// TODO Auto-generated method stub
		return shapesList.get(i);
	}

	public void removeShape(int i) {
		// TODO Auto-generated method stub
		shapesList.remove(i);
	}

	public void addShape(int i, MyShape tmp) {
		// TODO Auto-generated method stub
		shapesList.add(i, tmp);
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public void setShapesList(ArrayList<MyShape> shapesList) {
		this.shapesList = shapesList;
	}

	public void setShape(int i, MyShape shape) {
		// TODO Auto-generated method stub
		shapesList.set(i, shape);
	}

	public boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}
	
}
