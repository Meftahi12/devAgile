
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyEllipse;
import application.model.MyShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeLineCap;

class MyEllipseTest {
	
	/*
	private MyEllipse ellipse;
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	
	@BeforeEach
	public void buildUp() {
		ellipse= new MyEllipse();
		assertNotNull(ellipse);
		
		ellipse.setStartX(2.1);
		ellipse.setStartY(2.1);
		ellipse.setEndX(1.1);
		ellipse.setEndY(1.1);
		ellipse.setFiller(new ColorFiller(Color.BLACK, ellipse));
		ellipse.setStrokeSize(1.1);
		ellipse.createShape(false);
		ellipse.createShape(true);
	}
	
	@AfterEach
	public void tearDown() {
		ellipse=null;
		assertNull(ellipse);
	}
	
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	
	@Test
	void Clonetest() {
		MyShape r = ellipse.emptyClone();
		assertNotNull(r);
	}
	
	@Test
	void MoveShapeTest() {
		
		Ellipse c = (Ellipse) (ellipse.getShape());
		double r = c.getCenterX();
		
		ellipse.moveShape(1, 1);
		assertEquals(r+1, c.getCenterX());
	}
	
	@Test
	void enlargeTest() {
		
		Ellipse c = (Ellipse) (ellipse.getShape());
		double r = c.getRadiusX();
		
		ellipse.enlarge();;
		assertEquals(r+5, c.getRadiusX());
	}
	
	@Test
	void onPressedTest() {
		
		ellipse.onPressed(1.1, 1.1);
		assertEquals(Color.RED, ellipse.getShape().getStroke());
		assertEquals(5, ellipse.getShape().getStrokeWidth());
	}
	
	@Test
	void dragOnDraggedTest() {
		
		ellipse.dragOnDragged(1.1, 1.1, ellipse.getShape());
		//assertEquals(StrokeLineCap.BUTT, rectangle.getShape().getStrokeLineCap());
	}
	
	@Test
	void dragOnReleasedTest() {
		
		ellipse.dragOnReleased(Color.BLACK, 1.1);
		assertEquals(Color.BLACK, ellipse.getShape().getStroke());
	}
	
	
	@Test
	void resizeOnReleasedTest() {
		
		ellipse.resizeOnReleased(Color.BLACK, 1.1);
		assertEquals(StrokeLineCap.BUTT, ellipse.getShape().getStrokeLineCap());
	}

	
	@Test
	void resizeOnDraggedTest() {
		
		ellipse.resizeOnDragged(1.1, 1.1, ellipse.getShape());
		assertEquals(StrokeLineCap.BUTT, ellipse.getShape().getStrokeLineCap());
	}
	*/


}
