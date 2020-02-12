
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyLine;
import application.model.MyShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

class MyLineTest {
	
	/*
	private MyLine line;
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	
	@BeforeEach
	public void buildUp() {
		line= new MyLine();
		assertNotNull(line);
		line.setStartX(2.1);
		line.setStartY(2.1);
		line.setEndX(1.1);
		line.setEndY(1.1);
		line.setFiller(new ColorFiller(Color.BLACK, line));
		line.setStrokeSize(1.1);
		line.createShape(false);
		line.createShape(true);
	}
	
	@AfterEach
	public void tearDown() {
		line=null;
		assertNull(line);
	}
	@Test
	public void ColoneTest() {
		//MyLine c= new MyLine();
		line.emptyClone();
	}
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	
	
	@Test
	void resizeOnReleasedTest() {
		
		line.resizeOnReleased(Color.BLACK, 1.1);
		assertEquals(StrokeLineCap.BUTT, line.getShape().getStrokeLineCap());
	}
	
	@Test
	void onPressedTest() {
		
		line.onPressed(1.1, 1.1);
		assertEquals(Color.RED, line.getShape().getStroke());
		assertEquals(5, line.getShape().getStrokeWidth());
	}
	
	@Test
	void resizeOnDraggedTest() {
		
		line.resizeOnDragged(1.1, 1.1, line.getShape());
		assertEquals(StrokeLineCap.BUTT, line.getShape().getStrokeLineCap());
	}
	
	@Test
	void Clonetest() {
		MyShape r = line.emptyClone();
		assertNotNull(r);
	}
	
	@Test
	void MoveShapeTest() {
		
		Line c = (Line) (line.getShape());
		double r = c.getLayoutX();
		
		line.moveShape(1, 1);
		assertEquals(r+1, c.getLayoutX());
	}

	
	@Test
	void enlargeTest() {
		
		Line c = (Line) (line.getShape());
		double r = c.getEndX();
		
		line.enlarge();
		assertEquals(r-5, c.getEndX());
	}
	
	
	@Test
	void dragOnDraggedTest() {
		
		line.dragOnDragged(1.1, 1.1, line.getShape());
		//assertEquals(StrokeLineCap.BUTT, rectangle.getShape().getStrokeLineCap());
	}
	
	@Test
	void dragOnReleasedTest() {
		
		line.dragOnReleased(Color.BLACK, 1.1);
		assertEquals(Color.BLACK, line.getShape().getStroke());
	}
	
*/
}
