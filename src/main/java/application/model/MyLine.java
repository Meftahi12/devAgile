package application.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyLine extends SingleShape {

	@Override
	public void createShape(boolean isUsingCenter,  boolean isUsingMagnetism, GridPane gridPane) {
		initCircles();

		if(isUsingMagnetism) {
			drawWithMagnetism(gridPane);
		}
		if(isUsingCenter&& !isUsingMagnetism) {
			double centerX = 0;
			double centerY = 0;
			centerX = startX;
	
			centerY = startY;
			
			startX = 2 * centerX - endX;
			startY = 2 * centerY - endY;	
		}

		shape = new Line(startX,startY, endX, endY);
		shape.setStrokeWidth(strokeSize);
		shape.setStroke(strokeColor);
		filler.fill();
		applyRotation();

	}
		

	@Override
	public SingleShape emptyClone() {
		return new MyLine();
	}


	@Override
	public void moveShape(int x, int y) {
		Line l = (Line) (shape);

		l.setLayoutX(l.getLayoutX()+x);
		l.setLayoutY(l.getLayoutY()+y);


		updateFields();
	}

	@Override
	public void enlarge() {

		Line l = (Line) (shape);
		double pente =(l.getEndY()-l.getStartY())/(l.getEndX()-l.getStartX());
		double b= l.getStartY()- pente*l.getStartX();
		if(pente<0  ) {
			if(l.getEndY()>l.getStartY()) {
				l.setEndX(l.getEndX()-ENLARGE_CONST);
			}
			else {
				l.setEndX(l.getEndX()+ENLARGE_CONST);
			}
		}
		else if(pente>0 ) {
			if(l.getEndY()<l.getStartY()) {
				l.setEndX(l.getEndX()-ENLARGE_CONST);
			}
			else {
				l.setEndX(l.getEndX()+ENLARGE_CONST);
			}
		}
		else if(pente ==0) {
			if(l.getEndX()>l.getStartX()) {
				l.setEndX(l.getEndX()+ENLARGE_CONST);
			}
			else {
				l.setEndX(l.getEndX()-ENLARGE_CONST);
			}
		}
		l.setEndY(pente*l.getEndX()+b);
		updateFields();
	}

	@Override
	public void updateFields() {
		Line l = (Line) (shape);

		this.startX=l.getStartX();
		this.startY= l.getStartY();
		this.endX= l.getEndX();
		this.endY= l.getEndY();
		this.strokeColor= (Color) l.getStroke();
		this.strokeSize = l.getStrokeWidth() ;
		this.rotate = l.getRotate();

	}

	@Override
	public void resizeOnDragged(double x, double y, Shape shape) {
		this.shape.setStrokeLineCap(StrokeLineCap.BUTT);

		this.shape.getStrokeDashArray().addAll(10d, 5d);
		
		Line l = (Line) shape;
		
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

				
		l.setStartX(l.getStartX()+offsetX);
		l.setStartY(l.getStartY()+offsetY);


		orgSceneX = x;
		orgSceneY = y;
	}


	@Override
	public void dragOnDragged(double x, double y, Shape shape) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		double offsetX = x- orgSceneX;
		double offsetY = y- orgSceneY;

		Line l = (Line) (shape);



		l.setStartX(l.getStartX()+offsetX);
		l.setStartY(l.getStartY()+offsetY);

		l.setEndX(l.getEndX()+offsetX);
		l.setEndY(l.getEndY()+offsetY);

		orgSceneX = x;
		orgSceneY = y;
		
		

	}


	@Override
	protected void createShapeForImport() {
		createShape(false, false, null);
	}

	@Override
	public Shape createShapeClone (Shape c) {
		Line x = new Line (((Line) c).getStartX(),((Line) c).getStartY(),((Line) c).getEndX(),((Line) c).getEndY());
		x.setLayoutX(((Line) c).getLayoutX());
		x.setLayoutY(((Line) c).getLayoutY());
		x.setRotate(((Line) c).getRotate());
		x.setFill(c.getFill());
		return x ;
	}


	@Override
	public void resizeOnPressed(double x, double y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dragOnPressed(double x, double y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public SingleShape clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
