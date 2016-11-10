package Controller;

import java.util.ArrayList;

import Model.Item;
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
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest, btnAction;
	@FXML private TextArea txtAreaRoom, txtAreaEvents, txtAreaInventory, txtAreaActions, txtActionLog, txtAreaLoot, txtAreaEnemies;
	@FXML private TextField txtPlayerActions;
	@FXML private ProgressBar healthBar, xpBar, hpEnemy;
	@FXML private Label lblStatus;

	private Player player = new Player("name", 100, 30, 0, 0, 0, 0, 0);
	Level level = new Level();
	public String gameStatus;

	public void initialize(){
		txtAreaEvents.setText("Welcome to Concept Killer!\nPlease Enter Commands in the TextField to Play.");
		//gameStatus="starting";
		gameStatus="main";
		level.generateMap();
		txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
	}
	public void menuSwitch(){
		String command = txtPlayerActions.getText();
		switch (command){
		case "1":
		case "play":
			level.generateMap(); // need to make sure there are no enemies in starting room
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
		case "grab":
			player.addItem(level.removeItem(player.getX(), player.getY()));
			txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
			break;
		case "inventory":
			@SuppressWarnings("unchecked")
			ArrayList<Item> i = player.getInventory();
			String s=" ";
			for (Item x: i){
				s+=(x.getName()+", ");
			}
			txtAreaEvents.setText(s);
			break;
		case "attack":
			if(level.getEnemy(player.getX(), player.getY()).getHp() != 0)
			{
				gameStatus = "fighting";
				txtAreaEvents.setText("Combat Engaged!\n");
				txtActionLog.appendText("Combat Engaged\n");
				txtPlayerActions.setText(command);
				attack();
			}
			else
				lblStatus.setText("Nothing to fight!");
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
		if(level.getEnemy(player.getX(), player.getY()).getHp() != 0)
		{
			txtActionLog.appendText("Hit Enemy\n");
			txtAreaEvents.appendText("You attack!\n");
			txtAreaEvents.appendText(Integer.toString(level.getEnemy(player.getX(), player.getY()).takeDmg(player.attack())) + " damage done!\n");
			System.out.println(level.getEnemy(player.getX(), player.getY()).getHp());
			txtAreaEvents.appendText("Enemy attacks!\n");
			txtAreaEvents.appendText(Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack())) + " damage done!\n");
			System.out.println(player.getHp());
			if(level.getEnemy(player.getX(), player.getY()).getHp() == 0)
				txtAreaEvents.appendText("The enemy died!\n");
		}
		else
			lblStatus.setText("Nothing to fight!");
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
	if(player.getY()>0){
		player.setY(player.getY()-1);
		txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
		txtActionLog.appendText("North\n");
	} else{
		txtAreaEvents.setText("Can't Move North!");
	}
}

@FXML
public void goSouth()
{
	if(player.getY()<level.getSize()-1){
		player.setY(player.getY()+1);
		txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
		txtActionLog.appendText("South\n");
	}else{
		txtAreaEvents.setText("Can't Move South!");
	}
}

@FXML
public void goWest()
{
	if (player.getX()>0){
		player.setX(player.getX()-1);
		txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
		txtActionLog.appendText("West\n");
	}else{
		txtAreaEvents.setText("Can't Move West!");
	}
}

@FXML
public void goEast()
{
	if (player.getX()<level.getSize()-1){
		player.setX(player.getX()+1);
		txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
		txtActionLog.appendText("East\n");
	}else{
		txtAreaEvents.setText("Can't Move East!");
	}
}

// Method for placing text into room description
}