package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest;
	@FXML private TextArea txtAreaRoom, txtAreaEvents, txtAreaInventory, txtAreaActions, txtActionLog, txtAreaLoot, txtAreaEnemies;
	@FXML private TextField txtPlayerActions;
	@FXML private ProgressBar healthBar, xpBar, hpEnemy;
	@FXML private Label lblStatus;

	public String gameStatus = "";

	// Method for assigning cardinality buttons to input into txtPlayerActions.
	@FXML
	public void onPlayerAction(ActionEvent event)
	{
		// Handle Enter Key Press
		txtPlayerActions.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent keyEvent) {
		        if (keyEvent.getCode() == KeyCode.ENTER)  {
		            mainRunner();
		        }
		    }
		});
		
		if(event.getSource() == btnNorth)
		{
			txtPlayerActions.setText("north");
			mainRunner();
		}
		if(event.getSource() == btnSouth)
		{
			txtPlayerActions.setText("south");
			mainRunner();
		}
		if(event.getSource() == btnEast)
		{
			txtPlayerActions.setText("east");
			mainRunner();
		}
		if(event.getSource() == btnWest)
		{
			txtPlayerActions.setText("west");
			mainRunner();
		}
	
	}	
	@FXML
	public void mainRunner(){
		if(!gameStatus.equals("fighting")){
			lblStatus.setText("");
			String command = txtPlayerActions.getText();
			txtPlayerActions.setText("");
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
				txtAreaEvents.setText("Combat Engaged!");
				txtActionLog.appendText("Combat Engaged\n");
				//something along the lines of
				// "level.getmonster().takedamage();
				break;
			default:
				lblStatus.setText("Unknown Command!");
				break;
			}
		} else{
			attack();
		}
		
	}
	@FXML
	public void attack(){
		lblStatus.setText("");
		String command = txtPlayerActions.getText();
		txtPlayerActions.setText("");
		switch(command){
		case "attack":
			txtAreaEvents.setText("Hit!");
			txtActionLog.appendText("Hit Enemy\n");
			//level.getmonster().takedamage();
			break;
		case "block":
			txtAreaEvents.setText("Blocked!");
			txtActionLog.appendText("Blocked Enemy\n");
			break;
		case "run":
			gameStatus = "";
			txtAreaEvents.setText("Ran!");
			txtActionLog.appendText("Ran Away\n");
			txtActionLog.appendText("Combat Disengaged\n");
			break;
		default:
			lblStatus.setText("Unknown Command!");
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
	
	@FXML
	public void goNorth()
	{
		txtAreaEvents.setText("Went North!");
		txtActionLog.appendText("Went North\n");
	}
	
	@FXML
	public void goSouth()
	{
		txtAreaEvents.setText("Went South!");
		txtActionLog.appendText("Went South\n");
	}
	
	@FXML
	public void goWest()
	{
		txtAreaEvents.setText("Went West!");
		txtActionLog.appendText("Went West\n");
	}
	
	@FXML
	public void goEast()
	{
		txtAreaEvents.setText("Went East!");
		txtActionLog.appendText("Went East\n");
	}



	// Method for placing text into room description
}
