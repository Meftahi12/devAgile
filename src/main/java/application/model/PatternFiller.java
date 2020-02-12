package application.model;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class PatternFiller implements Filler{

	private Paint pattern;
	private String imageUrl;
	private MyShape shape; 
	public PatternFiller(String string, MyShape shape2) {
		imageUrl = string;
		shape = shape2;
		try {
			pattern = new ImagePattern(new Image(new FileInputStream(string)));
		} catch (Exception e) {
			Logger logger = Logger.getGlobal(); 
			logger.log(Level.SEVERE, "Exception");
		}

	}

	@Override
	public void fill() {
		shape.setFill(pattern);
		shape.updateFields();
	}

	@Override
	public boolean isColor() {
		return false;
	}

	public String getImageUrl() {
		return imageUrl;
	}

}