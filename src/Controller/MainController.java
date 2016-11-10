package Controller;

import Model.Level;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController
{
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest;
	@FXML private TextArea txtAreaRoom, txtAreaEvents, txtAreaInventory, txtAreaActions, txtActionLog, txtAreaLoot, txtAreaEnemies;
	@FXML private TextField txtPlayerActions;
	@FXML private ProgressBar healthBar, xpBar, hpEnemy;
	@FXML private Label lblStatus;

	private Player player = new Player("name", 100, 1, 0, 0, 0, 0, 0);
	Level level = new Level();
	public String gameStatus;

	public void initialize(){
		txtAreaEvents.setText("Welcome to Concept Killer!\nPlease Enter Commands in the TextField to Play.");
		//gameStatus="starting";
		gameStatus="main";
		level.generateMap();
	}
	public void menuSwitch(){
		String command = txtPlayerActions.getText();
		switch (command){
		case "1":
		case "play":
			level.generateMap();
			gameStatus="main";
			mainRunner();
			break;
		}
	}

	// Method for assigning cardinality buttons to input into txtPlayerActions.
	@FXML
	public void onPlayerAction(ActionEvent event)
	{
		System.out.println("enter");
		// Handle Enter Key Press
		switch (gameStatus)
		{
		//case "starting":
			//menuSwitch();
			//break;
		case "main":
			mainRunner();
			break;
		case "fighting":
			attack();
			break;
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
			txtPlayerActions.setText(command);
			attack();
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
		txtActionLog.appendText("Hit Enemy\n");
		txtAreaEvents.appendText("You attack!\n");
		txtAreaEvents.appendText(Integer.toString(level.getEnemy(player.getX(), player.getY()).takeDmg(player.attack())) + " damage done!\n");
		System.out.println(level.getEnemy(player.getX(), player.getY()).getHp());
		txtAreaEvents.appendText("Enemy attacks!\n");
		txtAreaEvents.appendText(Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack())) + " damage done!\n");
		System.out.println(player.getHp());
		break;
	case "block":
		txtAreaEvents.setText("Blocked!");
		txtActionLog.appendText("Blocked Enemy\n");
		break;
	case "run":
		gameStatus = "main";
		txtAreaEvents.setText("Ran!");
		txtActionLog.appendText("Bravely Ran Away\n");
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
	player.setY(player.getY()-1);
	txtAreaEvents.setText(level.getRoom(player.getX(), player.getY()));
	txtActionLog.appendText("North\n");
}

@FXML
public void goSouth()
{
	player.setY(player.getY()+1);
	txtAreaEvents.setText(level.getRoom(player.getX(), player.getY()));
	txtActionLog.appendText("South\n");
}

@FXML
public void goWest()
{
	player.setX(player.getX()-1);
	txtAreaEvents.setText(level.getRoom(player.getX(), player.getY()));
	txtActionLog.appendText("West\n");
}

@FXML
public void goEast()
{
	player.setX(player.getX()+1);
	txtAreaEvents.setText(level.getRoom(player.getX(), player.getY()));
	txtActionLog.appendText("East\n");
}

// Method for placing text into room description
}
