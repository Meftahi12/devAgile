package application.model;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyEllipse extends SingleShape {
	@Override
	public void createShape(boolean isUsingCenter, boolean isUsingMagnetism, GridPane gridPane) {
		initCircles();
		double radiusX = 0;
		double radiusY = 0;
		double centerX = 0;
		double centerY = 0;
		if(isUsingCenter && !isUsingMagnetism) {
			radiusX = Math.abs(this.startX - this.endX);
			
			radiusY = Math.abs(this.startY - this.endY);
	
			centerX = startX;
	
			centerY = startY;
			
			startX = centerX - radiusX;
			startY = centerY - radiusY;
			
			endX = centerX + radiusX;
			endY = centerY + radiusY;
			
		}
		arrangeStartAndEndXY();
		if(!isUsingCenter || isUsingMagnetism) {
			if(isUsingMagnetism) {
				drawWithMagnetism(gridPane);
			}
			radiusX = (Math.abs(this.startX - this.endX) / 2);
	
			radiusY = (Math.abs(this.startY - this.endY) / 2);
	
			centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			if (this.startX < this.endX) {
				centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			}
	
			centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			if (this.startY < this.endY) {
				centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			}
		}

		shape = new Ellipse(centerX, centerY, radiusX, radiusY);
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		applyRotation();
	}	
	

	@Override
	public SingleShape emptyClone() {
		return new MyEllipse();
	}


	@Override
	public void moveShape(int x, int y) {
		Ellipse c = (Ellipse) (shape);

		c.setCenterX(c.getCenterX() + x);
		c.setCenterY(c.getCenterY() + y);

		updateFields();
	}

	@Override
	public void enlarge() {
		Ellipse c = (Ellipse) (shape);
		c.setRadiusX(c.getRadiusX() + ENLARGE_CONST);
		c.setRadiusY(c.getRadiusY() + ENLARGE_CONST);
		updateFields();
	}
 
	@Override
	public void updateFields() {
		Ellipse c = (Ellipse) (shape);
		this.startX = (c.getCenterX() - c.getRadiusX());
		this.startY = (c.getCenterY() - c.getRadiusY());
		this.endX = (c.getCenterX() + c.getRadiusX());
		this.endY = (c.getCenterY() + c.getRadiusY());
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth() ;
		this.rotate = c.getRotate();
	}

	@Override
	public void resizeOnDragged(double x, double y, Shape shape) {
		this.shape.setStrokeLineCap(StrokeLineCap.BUTT);

		this.shape.getStrokeDashArray().addAll(10d, 5d);

		Ellipse c = (Ellipse) shape;

		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		c.setCenterX(c.getCenterX() + offsetX);
		c.setCenterY(c.getCenterY() + offsetY);

		c.setRadiusX(c.getRadiusX() - offsetX);
		c.setRadiusY(c.getRadiusY() - offsetY);

		orgSceneX = x;
		orgSceneY = y;
		
	}

	@Override
	public void dragOnDragged(double x, double y, Shape shape) {
		double offsetX = x-orgSceneX;
		double offsetY = y-orgSceneY;

		Ellipse c = (Ellipse) (shape);

		c.setCenterX(c.getCenterX() + offsetX);
		c.setCenterY(c.getCenterY() + offsetY);

		
		orgSceneX = x;
		orgSceneY = y;

	}

	@Override
	protected void createShapeForImport() {
		double radiusY = (this.endY - this.startY)/2 ;
		double radiusX = (this.endX - this.startX)/2 ;
		double centerX = this.startX +radiusX ;
		double centerY = this.startY +radiusY ;
		shape = new Ellipse(centerX, centerY, radiusX,radiusY);
		
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		applyRotation();
	}
	
	@Override
	public Shape createShapeClone (Shape c) {
		Ellipse x = new Ellipse (((Ellipse) c).getCenterX(),((Ellipse) c).getCenterY(),((Ellipse) c).getRadiusX(),((Ellipse) c).getRadiusY());
		x.setLayoutX(((Ellipse) c).getLayoutX());
		x.setLayoutY(((Ellipse) c).getLayoutY());
		x.setRotate(((Ellipse) c).getRotate());
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
		MyEllipse c = new MyEllipse();
		return c;
	}
}
