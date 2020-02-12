package application.model;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyCircle extends SingleShape {

	@Override
	public void createShape(boolean isUsingCenter, boolean isUsingMagnetism, GridPane gridPane) {
		initCircles();
		double radius = 0;
		double centerX = 0;
		double centerY = 0;
		if(isUsingCenter && !isUsingMagnetism) {
			radius = Math.sqrt(Math.abs(this.startX - this.endX) * Math.abs(this.startX - this.endX)
					+ Math.abs(this.startY - this.endY) * Math.abs(this.startY - this.endY));
	
			centerX = startX;
	
			centerY = startY;
			
			startX = centerX - radius;
			startY = centerY - radius;
			
			endX = centerX + radius;
			endY = centerY + radius;

		}
		
		arrangeStartAndEndXY();
		
		if(!isUsingCenter || isUsingMagnetism) {
			if(isUsingMagnetism) {
				drawWithMagnetism(gridPane);
			}
			radius = Math.sqrt(Math.abs(this.startX - this.endX) * Math.abs(this.startX - this.endX)
					+ Math.abs(this.startY - this.endY) * Math.abs(this.startY - this.endY)) / 2;

			centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			if (this.startX < this.endX) {
				centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			}

			centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			if (this.startY < this.endY) {
				centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			}
		}
		

		shape = new Circle(centerX, centerY, radius);
		
		
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		applyRotation();

	}
	
	@Override
	public void resizeOnDragged(double x, double y, Shape shape) {
		this.shape.setStrokeLineCap(StrokeLineCap.BUTT);

		this.shape.getStrokeDashArray().addAll(10d, 5d);
		

		Circle c = (Circle) shape;

		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		c.setCenterX(c.getCenterX());
		c.setCenterY(c.getCenterY());

		c.setRadius(c.getRadius() - ((offsetX) / 2 + (offsetY) / 2) / 2);

		orgSceneX = x;
		orgSceneY = y;
		
	}

	@Override
	public SingleShape emptyClone() {
		return new MyCircle();
	}


	@Override
	public void moveShape(int x, int y) {
		Circle c = (Circle) (shape);

		c.setCenterX(c.getCenterX() + x);
		c.setCenterY(c.getCenterY() + y);
		updateFields();
	}

	@Override
	public void enlarge() {
		Circle c = (Circle) (shape);
		c.setRadius(c.getRadius() + ENLARGE_CONST);
		updateFields();
	}

	@Override
	public void updateFields() {
		Circle c = (Circle) (shape);
		this.startX = (c.getCenterX() - c.getRadius());
		this.startY = (c.getCenterY() - c.getRadius());
		this.endX = (c.getCenterX() + c.getRadius());
		this.endY = (c.getCenterY() + c.getRadius());
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth() ;
		this.rotate = c.getRotate();
	}


	@Override
	public void dragOnDragged(double x, double y, Shape shape) {
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		Circle c = (Circle) (shape);
		
		c.setCenterX(c.getCenterX() + offsetX);
		c.setCenterY(c.getCenterY() + offsetY);

		orgSceneX = x;
		orgSceneY = y;

	}


	@Override
	protected void createShapeForImport() {
		double radius = (this.endY - this.startY)/2 ;
		double centerX = this.startX +radius ;
		double centerY = this.startY +radius ;
		shape = new Circle(centerX, centerY, radius);
		
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		applyRotation();
	}
	
	@Override
	public Shape createShapeClone (Shape c) {
		Circle x= new Circle (((Circle) c).getCenterX(),((Circle) c).getCenterY(),((Circle) c).getRadius(),c.getFill());
		x.setLayoutX(((Circle) c).getLayoutX());
		x.setLayoutY(((Circle) c).getLayoutY());
		x.setRotate(((Circle) c).getRotate());
		x.setFill(c.getFill());
		return x ;
	}


	@Override
	public void resizeOnPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	public void dragOnPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		orgSceneX = x;
		orgSceneY = y;

	}

	@Override
	public SingleShape clone() {
		MyCircle c = new MyCircle();
		return c;
	}

	
}