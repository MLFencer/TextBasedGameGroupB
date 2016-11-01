package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest;
	@FXML private TextArea txtAreaRoom, txtAreaEvents, txtAreaInventory, txtAreaActions, txtAreaLog,
					  txtAreaLoot, txtAreaEnemies;
	@FXML private TextField txtPlayerActions;

	// Method for assigning cardinality buttons to input into txtPlayerActions.
	public void clickOnCompass(ActionEvent event)
	{
		if(event.getSource() == btnNorth)
			txtPlayerActions.appendText("North");
		if(event.getSource() == btnSouth)
			txtPlayerActions.appendText("South");
		if(event.getSource() == btnEast)
			txtPlayerActions.appendText("East");
		if(event.getSource() == btnWest)
			txtPlayerActions.appendText("West");
	}

	// Method for placing text into room description
}
