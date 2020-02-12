
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyRectangle;
import application.model.MyShape;
import javafx.scene.paint.Color;

class MyShapeTest {
	/*
	private MyShape shape;
	//private Map<String, Double> properties ;
	private HashMap<String, Double> properties;
	private ColorFiller colorFiller;
	Map<String, Double> properties1; 

	
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	@BeforeEach
	public void buildUp() {
		shape = new MyRectangle();
		properties= new HashMap<String, Double>();
		colorFiller= new ColorFiller(Color.BLACK, shape);
		properties1= new HashMap<String, Double>();
		assertNotNull(shape);
		assertNotNull(properties);
		assertNotNull(properties1);
	}
	
	@AfterEach
	public void tearDown() {
		shape= null;
		assertNull(shape);
	}
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	
	@Test
	void testShapeSelected() {
		shape.setSelected(true);
		assertTrue(shape.isSelected());

	}
	
	@Test
	void testProperties() {
		
		properties.put("strockR", 1.0);
		properties.put("strockG", 1.0);
		properties.put("strockB", 1.0);
		properties.put("fillR", 1.0);
		properties.put("fillG", 1.0);
		properties.put("fillB", 1.0);
		properties.put("fillOpacity", 1.0);
		shape.setProperties(properties); 
		properties.put("imageUrl", 1.0);
		shape.setProperties(properties); 
		shape.getShape();
		
	}

	
	@Test
	public void startXTest() {
		shape.setStartX(1.0);
		assertEquals(1.0, shape.getStartX());
		
	}
	
	@Test
	public void startYTest() {
		shape.setStartY(1.0);
		assertEquals(1.0, shape.getStartY());
		
	}
	
	@Test
	public void endXTest() {
		shape.setEndX(1.0);
		assertEquals(1.0, shape.getEndX());
		
	}
	@Test
	public void endYTest() {
		shape.setEndY(1.0);
		assertEquals(1.0, shape.getEndY());
		
	}
	
	
	@Test
	public void StrokeTest() {
		shape.setStrokeColor(Color.AZURE);
		assertNotNull(shape.getStrokeColor());
		
	}
	
	@Test
	public void StrokeSizeTest() {
		shape.setStrokeSize(1);
		assertEquals(1, shape.getStrokeSize());
		
	}
	
	@Test
	public void propertiesTest() {
		shape.setProperties(0.0, 0.0, 0.0, 0.0, null, 1.0, null, null);
	}

	@Test
	public void fillerTest() {
		shape.setFiller(colorFiller);
		
		assertEquals(colorFiller, shape.getFiller());
		
	}
	
	@Test
	public void getPropTest() {
		shape.setStartX(1.1);
		shape.setStartY(1.1);
		shape.setEndX(1.1);
		shape.setEndY(1.1);
		shape.setFiller(colorFiller);
		//shape.getShape().setStrokeWidth(1);
		//shape.getProperties();
		
		
	}
	
	@Test
	public void patternTest() {
		shape.setPattern(true);
		assertEquals(true, shape.isPattern());	
	}
	
	@Test
	public void changeStrokeWidthTest() {
		//shape.changeStrokeWidth(2);;
		//assertEquals(2, shape.getShape().getStrokeWidth());	
		//shape.changeStrokeColor(Color.BLACK);
	}
	
	
	*/
	


}
