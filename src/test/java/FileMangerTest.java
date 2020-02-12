import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.FileManager;
import application.model.MyShape;

class FileMangerTest {
	
	private FileManager fileManager;
	private ArrayList<MyShape> l;

	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	 
	@BeforeEach
	public void buildUp() {
		fileManager= new FileManager();
		assertNotNull(fileManager);
		l= new ArrayList<MyShape>();
		assertNotNull(l);
	}
	
	@AfterEach
	public void tearDown() {
		fileManager=null;
		assertNull(fileManager);
	}
	
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}

	
	
	
	@Test
	void test() {
		try {
			fileManager.saveTextToFile("", l);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
