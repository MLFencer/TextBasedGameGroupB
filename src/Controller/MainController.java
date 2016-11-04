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

	public String gameStatus;

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

	/*
	 * Marcel, Call the 'mainRunner()' method into the the listener for the enter so that it is called everytime
	 * 			the enter key is hit, everything else will work itself out.
	 *
	 * Thanks, Michael
	 */

	public void mainRunner(){
		if(!gameStatus.equals("fighting")){
			String command = txtPlayerActions.getText();
			switch (command){
			case "north":
				goNorth();
				break;
			case "south":
				goSouth();
				break;
			case "west":
				goWest();
				break;
			case "east":
				goEast();
				break;
			case "attack":
				gameStatus = "fighting";
				//something along the lines of
				// "level.getmonster().takedamage();
				attack();
				break;
			default:
				txtAreaLog.setText("Unknown Command!");
				break;
			}
		} else{
			attack();
		}
	}

	public void attack(){
		String command = txtPlayerActions.getText();
		switch(command){
		case "attack":
			//level.getmonster().takedamage();
			break;
		default:
			txtAreaLog.setText("Unknown Command!");
			break;
		} //more cases and more stuff coming soon.
		/*
		 * this will bring back the main switch instead of the atack switch.
		 *
		 * if (level.getMonster().getHp()<=0 || player.getHp()<=0){
		 * 	gameStatus = "main";
		 * }
		 */
	}

	public void goNorth(){}
	public void goSouth(){}
	public void goWest(){}
	public void goEast(){}



	// Method for placing text into room description
}
