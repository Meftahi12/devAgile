package application.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import application.model.Canevas;

public class StateController {
	private static final String CONFIG_FILE_PATH= "src/main/resources/config.xml";
	private Canevas canevas;
	private CanevasController canevasController;

	public StateController(CanevasController canevasController) {
		this.canevasController = canevasController;
		this.loadState();
	}

	public void saveState(){
		canevas = new Canevas(canevasController);
		try {
			canevas.getPrefs().exportNode( new FileOutputStream (CONFIG_FILE_PATH) );
		} catch (IOException | BackingStoreException e) {
			e.printStackTrace();
			System.out.println("Exception ici");
		}
	}

	public void loadState() {
		Preferences preferences = Preferences.userNodeForPackage(this.getClass());
		try {
			preferences.importPreferences(new FileInputStream(CONFIG_FILE_PATH));
		} catch (Exception e) {
			this.saveState();
			try {
				preferences.importPreferences(new FileInputStream(CONFIG_FILE_PATH));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// Show Exception Dialog
				e1.printStackTrace();
			}
		}

		canevas = new Canevas(preferences);
		//canevasController.updateScreenState(canevas);
	}
}
