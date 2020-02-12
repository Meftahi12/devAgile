
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyCircle;
import application.model.MyShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;

class MyCircleTest {
	/*
	private MyCircle circle;
	
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	@BeforeEach
	public void buildUp() {
		circle= new MyCircle();
		assertNotNull(circle);
		circle.setStartX(2.1);
		circle.setStartY(2.1);
		circle.setEndX(1.1);
		circle.setEndY(1.1);
		circle.setFiller(new ColorFiller(Color.BLACK, circle));
		circle.setStrokeSize(1.1);
		circle.createShape(false);
		circle.createShape(true);
	}
	@AfterEach
	public void tearDown() {
		circle=null;
		assertNull(circle);
	}
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	
	
	@Test
	void resizeOnReleasedTest() {
		
		circle.resizeOnReleased(Color.BLACK, 1.1);
		assertEquals(StrokeLineCap.BUTT, circle.getShape().getStrokeLineCap());
	}
	
	@Test
	void resizeOnPressedTest() {
		
		circle.onPressed(1.1, 1.1);
		assertEquals(Color.RED, circle.getShape().getStroke());
		assertEquals(5, circle.getShape().getStrokeWidth());
	}
	
	@Test
	void resizeOnDraggedTest() {
		
		circle.resizeOnDragged(1.1, 1.1, circle.getShape());
		assertEquals(StrokeLineCap.BUTT, circle.getShape().getStrokeLineCap());
	}
	
	@Test
	void Clonetest() {
		MyShape r = circle.emptyClone();
		assertNotNull(r);
	}
	
	@Test
	void MoveShapeTest() {
		
		Circle c = (Circle) (circle.getShape());
		double r = c.getCenterX();
		
		circle.moveShape(1, 1);
		assertEquals(r+1, c.getCenterX());
	}

	
	@Test
	void enlargeTest() {
		
		Circle c = (Circle) (circle.getShape());
		double r = c.getRadius();
		
		circle.enlarge();;
		assertEquals(r+5, c.getRadius());
	}
	
	@Test
	void dragOnDraggedTest() {
		circle.dragOnDragged(1.1, 1.1, circle.getShape());
	}
	
	@Test
	void dragOnReleasedTest() {
		circle.dragOnReleased(Color.BLACK, 1.1);
		assertEquals(Color.BLACK, circle.getShape().getStroke());
	}
	
	@Test
	void toStringTest() {
		assertNotNull(circle.toString());
		
	}
	
	*/
}
