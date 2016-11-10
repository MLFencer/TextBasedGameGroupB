package Controller;

import Model.TextBuffer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest;
	@FXML private TextArea txtAreaRoom, txtAreaEvents, txtAreaInventory, txtAreaActions, txtAreaLog,
	txtAreaLoot, txtAreaEnemies;
	@FXML private TextField txtPlayerActions;
	@FXML private ProgressBar healthBar, xpBar;

	public String gameStatus = "";

	// Method for assigning cardinality buttons to input into txtPlayerActions.
	@FXML
	public void onPlayerAction(ActionEvent event)
	{
		// Handle Enter Key Press
		txtPlayerActions.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
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
	@FXML
	public void attack(){
		String command = txtPlayerActions.getText();
		txtPlayerActions.setText("");
		switch(command){
		case "attack":
			txtAreaEvents.setText("Hit!");
			//level.getmonster().takedamage();
			break;
		case "block":
			txtAreaEvents.setText("Blocked!");
			break;
		case "run":
			gameStatus = "";
			txtAreaEvents.setText("Ran!");
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
	@FXML
	public void goNorth(){txtAreaEvents.setText("Went North!");}
	@FXML
	public void goSouth(){txtAreaEvents.setText("Went South!");}
	@FXML
	public void goWest(){txtAreaEvents.setText("Went West!");}
	@FXML
	public void goEast(){txtAreaEvents.setText("Went East!");}

	public static void ShowHelp()
    {
        TextBuffer.Add("Availabe Commands:");
        TextBuffer.Add("------------------");
        TextBuffer.Add("help");
        TextBuffer.Add("exit");
        TextBuffer.Add("move[north, south, east, west]");
        TextBuffer.Add("look");
        TextBuffer.Add("pickup");
        TextBuffer.Add("drop");
        TextBuffer.Add("inventory");
        TextBuffer.Add("wheremai");
    }


	// Method for placing text into room description
}
