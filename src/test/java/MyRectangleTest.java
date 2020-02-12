
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyRectangle;
import application.model.MyShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

class MyRectangleTest {
	/*
	private MyRectangle rectangle;
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	
	@BeforeEach
	public void buildUp() {
		rectangle= new MyRectangle();
		assertNotNull(rectangle);
		rectangle.setStartX(2.1);
		rectangle.setStartY(2.1);
		rectangle.setEndX(1.1);
		rectangle.setEndY(1.1);
		rectangle.setFiller(new ColorFiller(Color.BLACK, rectangle));
		rectangle.createShape(true);
	}
	
	@AfterEach
	public void tearDown() {
		rectangle=null;
		assertNull(rectangle);
	}
	
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	
	


	@Test
	void Clonetest() {
		MyShape r = rectangle.emptyClone();
		assertNotNull(r);
	}
	
	@Test
	void MoveShapeTest() {
		
		Rectangle c = (Rectangle) (rectangle.getShape());
		double r = c.getX();
		
		rectangle.moveShape(1, 1);
		assertEquals(r+1, c.getX());
	}
	
	@Test
	void enlargeTest() {
		
		Rectangle c = (Rectangle) (rectangle.getShape());
		double r = c.getWidth();
		
		rectangle.enlarge();;
		assertEquals(r+5, c.getWidth());
	}
	
	@Test
	void onPressedTest() {
		
		rectangle.onPressed(1.1, 1.1);
		assertEquals(Color.RED, rectangle.getShape().getStroke());
		assertEquals(5, rectangle.getShape().getStrokeWidth());
	}
	
	@Test
	void resizeOnDraggedTest() {
		
		rectangle.resizeOnDragged(1.1, 1.1, rectangle.getShape());
		assertEquals(StrokeLineCap.BUTT, rectangle.getShape().getStrokeLineCap());
	}
	
	@Test
	void resizeOnReleasedTest() {
		
		rectangle.resizeOnReleased(Color.BLACK, 1.1);
		assertEquals(StrokeLineCap.BUTT, rectangle.getShape().getStrokeLineCap());
	}
	
	@Test
	void dragOnDraggedTest() {
		
		rectangle.dragOnDragged(1.1, 1.1, rectangle.getShape());
		//assertEquals(StrokeLineCap.BUTT, rectangle.getShape().getStrokeLineCap());
	}
	
	@Test
	void dragOnReleasedTest() {
		
		rectangle.dragOnReleased(Color.BLACK, 1.1);
		assertEquals(Color.BLACK, rectangle.getShape().getStroke());
	}
*/
}
