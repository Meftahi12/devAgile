package application.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Translate;


public abstract class SingleShape implements MyShape{


	protected ComposedShape root;

	protected String layoutName;
	
	public abstract void resizeOnPressed(double x, double y);

	public abstract void dragOnPressed(double x, double y);

	public Shape getShape() {
		return shape;
	}


	public abstract SingleShape clone() ;


	public HashMap<String, Double> getProperties(){

		HashMap<String, Double> properties = new HashMap<String, Double>();

		properties.put("LayoutName", Double.parseDouble(layoutName.substring(6)));

		properties.put("startPositionX", (double)startX);
		properties.put("startPositionY", (double)startY);

		properties.put("endPositionX", (double)endX);
		properties.put("endPositionY", (double)endY);

		properties.put("topLeftX", (double)startX);
		properties.put("topLeftY", (double)startY);


		properties.put("strokeSize", (double) shape.getStrokeWidth());
		if(!filler.isColor()) {
			String pattern = ((PatternFiller) filler).getImageUrl().substring(((PatternFiller)filler).getImageUrl().lastIndexOf("/") + 1, ((PatternFiller)filler).getImageUrl().lastIndexOf('.'));
			properties.put("imageUrl", (double) Integer.parseInt(pattern));
		}
		else {
			properties.put("fillR", ((ColorFiller) filler).getColor().getRed());
			properties.put("fillG", ((ColorFiller) filler).getColor().getGreen());
			properties.put("fillB", ((ColorFiller) filler).getColor().getBlue());
			properties.put("fillOpacity", ((ColorFiller) filler).getColor().getOpacity());
		}
		properties.put("strockR", strokeColor.getRed());
		properties.put("strockG", strokeColor.getGreen());
		properties.put("strockB", strokeColor.getBlue());

		properties.put("rotateValue", shape.getRotate());

		//

		return properties;
	}

	public void setProperties(HashMap<String, Double> properties) {
		layoutName = "Layout" + (properties.get("LayoutName")).intValue();
		startX = (Double)properties.get("startPositionX");
		startY = (Double)properties.get("startPositionY");

		endX = (Double)properties.get("endPositionX");
		endY = (Double)properties.get("endPositionY");

		strokeSize = (Double) properties.get("strokeSize");

		rotate = (Double)properties.get("rotateValue");

		Double strockR,strockG,strockB,fillR,fillG,fillB, fillOpacity;
		strockR = (Double) properties.get("strockR");
		strockG = (Double) properties.get("strockG");
		strockB = (Double) properties.get("strockB");

		strokeColor = Color.color(strockR,strockG, strockB);

		if(properties.containsKey("imageUrl")) {
			filler = new PatternFiller("src/main/resources/" + ((Double)properties.get("imageUrl")).intValue() + ".jpg", this);
		}
		else {
			fillR = (Double) properties.get("fillR");
			fillG = (Double) properties.get("fillG");
			fillB = (Double) properties.get("fillB");
			fillOpacity = (Double) properties.get("fillOpacity");
			filler = new ColorFiller(Color.color(fillR, fillG, fillB, fillOpacity), this);
		}



	}

	public void changeStrokeWidth(int x) {
		shape.setStrokeWidth(x);
		updateFields();
	}

	public void changeStrokeColor(Color value) {
		shape.setStroke(value);
		updateFields();
	}

	@Override
	public void draw(ObservableList<Node> children) {
		// TODO Auto-generated method stub
		children.add(getShape());
	}




	public ComposedShape getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void setFill(Paint pattern) {
		// TODO Auto-generated method stub
		getShape().setFill(pattern);
	}

	@Override
	public void setRoot(ComposedShape compShape) {
		// TODO Auto-generated method stub
		root = compShape;
	}

	@Override
	public boolean isInside(Double startX, Double startY, Double currentX, Double currentY) {
		return FindPoint(startX, startY, currentX, currentY, this.startX, this.startY)
				|| FindPoint(startX, startY, currentX, currentY, this.endX, this.endY)
				|| FindPoint(startX, startY, currentX, currentY, this.startX, this.endY)
				|| FindPoint(startX, startY, currentX, currentY, this.endX, this.startY);

	}

	public boolean FindPoint(double x1, double y1, double x2, double y2, double x, double y) { 
		if (x > x1 && x < x2 && y > y1 && y < y2) 
			return true; 

		return false; 
	} 

	public ArrayList<SingleShape> getList(){
		ArrayList<SingleShape> list = new ArrayList<SingleShape>();
		list.add(this);
		return list;
	}

	protected static final int ENLARGE_CONST = 5;
	private static final int ROTATE_CONST = 5;
	private static final String IMAGE_URL = "imageUrl";
	private static final double HANDELS_SIZE_CONST = 4; 

	protected Shape shape;
	protected boolean isSelected = false ;
	protected Double startX ;
	protected Double startY;
	protected Double endX;
	protected Double endY;
	protected Color strokeColor;
	protected Double strokeSize;
	protected Double rotate;
	protected Translate translate;
	protected boolean isPattern;
	protected Filler filler;

	protected Circle[] circles;

	double orgSceneX;
	double orgSceneY;

	public abstract void createShape(boolean isUsingCenter, boolean isUsingMagnetism, GridPane gridPane);

	public void onPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);

		orgSceneX = x;
		orgSceneY = y;
	}

	public abstract void resizeOnDragged(double x, double y, Shape c);

	public void resizeOnReleased(Paint color, double str) {
		this.isSelected = true;
		this.shape.setStroke(color);
		this.shape.setStrokeWidth(str);
		this.shape.setStrokeLineCap(StrokeLineCap.BUTT);
		this.shape.getStrokeDashArray().clear();
		this.shape.setStrokeDashOffset(str);
		updateFields();
	}

	public abstract void dragOnDragged(double x, double y, Shape c);

	public void dragOnReleased(Paint color, double str) {
		this.isSelected = true ;
		this.shape.setStroke(color);
		this.shape.setStrokeWidth(str);
		updateFields();
	}

	public abstract void moveShape(int x, int y);

	public abstract void enlarge();

	public abstract SingleShape emptyClone() ;

	protected abstract void createShapeForImport();

	public abstract Shape createShapeClone(Shape c) ;

	public MyShape getClone() {
		SingleShape clone = emptyClone() ;
		clone.setProperties(this.getProperties());
		clone.createShapeForImport();
		return clone ;
	}

	public boolean isPattern() {
		return isPattern;
	}

	public void setPattern(boolean isPattern) {
		this.isPattern = isPattern;
	}

	public void setFiller(Filler filler) {
		this.filler = filler;
	}

	public void disableDraggable() {
		shape.setOnMousePressed(t -> {});
		shape.setOnMouseDragged(t -> {});
	}

	public void setProperties(
			Double startX, 
			Double startY, 
			Double endX, 
			Double endY, 
			Color strokeColor,
			Double strokeSize,
			Filler filler,
			Double rotate,
			String layoutName
			) 
	{
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		this.filler=filler;
		this.strokeSize = strokeSize;
		this.strokeColor=strokeColor;
		this.rotate= rotate;
		this.layoutName = layoutName;
		
	}


	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void rotateShape() {
		shape.setRotate(shape.getRotate()+ROTATE_CONST);
		updateFields();
	}


	public void setProperties(Map<String, Double> map) {
		startX = map.get("startPositionX");
		startY = map.get("startPositionY");

		endX = map.get("endPositionX");
		endY = map.get("endPositionY");

		strokeSize = map.get("strokeSize");

		rotate = map.get("rotateValue");

		Double strockR;
		Double strockG;
		Double strockB;
		Double fillR;
		Double fillG;
		Double fillB;
		Double fillOpacity;
		strockR = map.get("strockR");
		strockG = map.get("strockG");
		strockB = map.get("strockB");

		strokeColor = Color.color(strockR,strockG, strockB);

		if(map.containsKey(IMAGE_URL)) {
			filler = new PatternFiller("src/main/resources/" + map.get(IMAGE_URL).intValue() + ".jpg", this);
		}
		else {
			fillR = map.get("fillR");
			fillG = map.get("fillG");
			fillB = map.get("fillB");
			fillOpacity = map.get("fillOpacity");
			filler = new ColorFiller(Color.color(fillR, fillG, fillB, fillOpacity), this);
		}



	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(Double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(Double startY) {
		this.startY = startY;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(Double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(Double endY) {
		this.endY = endY;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public double getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(double strokeSize) {
		this.strokeSize = strokeSize;
	}

	public Filler getFiller() {
		return filler;
	}

	public void setFiller(ColorFiller colorFiller) {
		filler = colorFiller;
	}
	public void applyRotation(){
		shape.setRotate(this.rotate);
	}

	public void arrangeStartAndEndXY() {
		if (startX > endX) {
			Double tmp = startX;
			startX = endX;
			endX = tmp;
		}


		if (startY > endY) {
			Double tmp = startY;
			startY = endY;
			endY = tmp;
		}
	}

	public Double getWidth() {

		return endY-startY;
	}


	public Double getHeight() {
		return endX-startX;
	}



	public void setHandelsPos() {

		this.circles[0].setCenterX(this.shape.getLayoutBounds().getMinX());
		this.circles[0].setCenterY(this.shape.getLayoutBounds().getMinY());


		this.circles[1].setCenterX(this.shape.getLayoutBounds().getMaxX());
		this.circles[1].setCenterY(this.shape.getLayoutBounds().getMinY());


		this.circles[2].setCenterX(this.shape.getLayoutBounds().getMinX());
		this.circles[2].setCenterY(this.shape.getLayoutBounds().getMaxY());


		this.circles[3].setCenterX(this.shape.getLayoutBounds().getMaxX());
		this.circles[3].setCenterY(this.shape.getLayoutBounds().getMaxY());

	}



	public  Circle [] getCircles () {
		return this.circles;
	}

	public void setHadelsVisible(Boolean value) {

		for (Circle circle : circles) {
			circle.setVisible(value);
		}
	}


	public void initCircles () {

		this.circles = new Circle[4] ;
		for (int i = 0; i < circles.length; i++) {
			circles[i] = new Circle(HANDELS_SIZE_CONST, Color.GOLD);
		}




	}


	@Override
	public String toString() {
		return "MyShape [shape=" + shape + ", isSelected=" + isSelected + ", startX=" + startX + ", startY=" + startY
				+ ", endX=" + endX + ", endY=" + endY + ", strokeColor=" + strokeColor + ", strokeSize=" + strokeSize
				+ ", rotate=" + rotate + ", translate=" + translate + ", isPattern=" + isPattern + ", filler=" + filler
				+ "]";
	}


	public void drawWithMagnetism(GridPane gridPane) {
			double width= gridPane.getWidth();
			double height= gridPane.getHeight();
			 final long numCols = gridPane.getColumnCount();
			 final long numRows = gridPane.getRowCount()-1;
			 System.out.println(numCols);
			 System.out.println(numRows);
			double cellWidth= width/numCols;
			double cellHeight= height/numRows;
		  for(int i=0; i<numCols;i++) { 
			 if(startX>i*cellWidth && startX<=(i+1)*cellWidth) {
				 startX=i*cellWidth;
			 }
		  for(int j=0; j<numRows;j++) {
			  if(startY>j*cellHeight&& startY<(j+1)*cellHeight) {
				startY=j*cellHeight;
}}}
		  for(int i=0; i<numCols;i++) { 
			  if(endX>i*cellWidth && endX<=(i+1)*cellWidth) { 
				endX=(i+1)*cellWidth;}
			for(int j=0; j<numRows;j++) {
				if(endY>j*cellHeight&& endY<(j+1)*cellHeight) {
				endY=(j+1)*cellHeight;}}}
		  
	}

	public String getLayout() {
		return layoutName;
	}
}
