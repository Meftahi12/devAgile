import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.MyEllipse;
import application.model.PatternFiller;

class PatternFillerTest {
	private PatternFiller patternFiller;
	

	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	 
	@BeforeEach
	public void buildUp() {
		patternFiller= new PatternFiller("src/main/resources/5.jpg", new MyEllipse());
		assertNotNull(patternFiller);
		
	}
	
	@AfterEach
	public void tearDown() {
		patternFiller=null;
		assertNull(patternFiller);
	}
	
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}


	@Test
	void testIsColor() {
		assertFalse(patternFiller.isColor());
	}
	
	@Test
	void testGetURL() {
		assertNotNull(patternFiller.getImageUrl());
	}

}
