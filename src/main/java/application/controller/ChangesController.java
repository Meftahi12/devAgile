package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.model.MyShape;

public class ChangesController {

	private List<List<MyShape>> undoList ;
	private List<List<MyShape>> redoList ;
	private CanevasController canevasController;
	private MenuUIController menuController;

	public ChangesController (CanevasController canevasController) {
		this.canevasController = canevasController;
		undoList = new ArrayList<>();
		redoList  = new ArrayList<>();
	}

	public List<List<MyShape>> getUndoList() {
		return undoList;
	}

	public List<List<MyShape>> getRedoList() {
		return redoList;
	}


	public void undo () {
		if(!undoList.isEmpty()) {
			List<MyShape> theChangeToGoTo = undoList.remove(undoList.size()-1);
			saveStateToRedo();
			canevasController.refresh(theChangeToGoTo);
		}
	}

	public void redo() {
		if(!redoList.isEmpty()) {
			List<MyShape> theChangeToGoTo = redoList.remove(redoList.size()-1);
			saveStateToUndo();
			canevasController.refresh(theChangeToGoTo);
		}
	}

	private void saveStateToRedo() {
		redoList.add(canevasController.getShapesListClone());
		menuController.updateMenuItemState();
	}


	public void saveStateToUndo() {
		undoList.add(canevasController.getShapesListClone());
		menuController.updateMenuItemState();
	}

	public void setMenuController(MenuUIController menuUIController) {
		this.menuController = menuUIController ;
		
	}


}
