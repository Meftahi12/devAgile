package application.model;

import javafx.scene.paint.Color;

public class ColorFiller implements Filler{
	
	private Color color;
	private MyShape shape;
	public ColorFiller(Color color2, MyShape shape2) {
		color = color2;
		shape = shape2;
	}
	@Override
	public void fill() {
		shape.setFill(color);
		shape.updateFields();
	}
	@Override
	public boolean isColor() {
		return true;
	}
	public Color getColor() {
		return color;
	}

}
