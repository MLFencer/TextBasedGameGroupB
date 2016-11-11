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

	private Player player = new Player("name", 100, 30, 0, 0, 0, 0, 1, 0, 0);
	Level level = new Level();
	public String gameStatus;

	public void initialize(){
		txtAreaEvents.appendText("Welcome to Concept Killer!\nPlease Enter Commands in the TextField to Play.");
		//gameStatus="starting";
		gameStatus="main";
		level.generateMap();
		showHelp();
		barUpdates();
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
		String command = txtPlayerActions.getText();
		if(command.contains("use ")){
			System.out.println(command.charAt(4));
			useItem(command.charAt(4));
			command="use";
		}
		if(!gameStatus.equals("fighting")){
			lblStatus.setText("");
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
				Item item=level.removeItem(player.getX(), player.getY());
				player.addItem(item);
				txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
				txtAreaInventory.setText(player.InventoryListString());
				txtActionLog.appendText("Grabbed "+player.getInventoryItem(player.getInventory().size()-1).getName()+"\n");
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
				try{
					if(level.getEnemy(player.getX(), player.getY()).getHp() == 0){
						lblStatus.setText("Nothing to fight!");
						level.setEnemy(player.getX(), player.getY(), null);
					}

					if(level.getEnemy(player.getX(), player.getY()).getHp() != 0)
					{
						gameStatus = "fighting";
						showHelp();
						txtAreaEvents.setText("Combat Engaged!\n");
						txtActionLog.appendText("Combat Engaged\n");
						txtPlayerActions.setText(command);
						attack();
					}
				}catch(Exception e){
					lblStatus.setText("Nothing to fight!");
				}
				break;
			case "use":
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
		if(command.contains("use ")){
			useItem(command.charAt(4));
		}
		txtPlayerActions.setText("");
		switch(command){
		case "attack":
			if(level.getEnemy(player.getX(), player.getY()).getHp() != 0)
			{
				txtActionLog.appendText("Hit Enemy\n");
				txtAreaEvents.appendText("You attack!\n");
				txtAreaEvents.appendText(Integer.toString(level.getEnemy(player.getX(), player.getY()).takeDmg(player.attack()+(int)player.getActiveWeapon().getValue())) + " damage done!\n");
				System.out.println(level.getEnemy(player.getX(), player.getY()).getHp());
				txtAreaEvents.appendText("Enemy attacks!\n");
				txtAreaEvents.appendText(Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack())) + " damage done!\n");
				System.out.println(player.getHp());
				barUpdates();
				if(level.getEnemy(player.getX(), player.getY()).getHp() == 0){
					txtAreaEvents.appendText("The enemy died!\n");
					txtAreaEvents.appendText(player.gainXp(level.getEnemy(player.getX(), player.getY()).getXp()));
					gameStatus="main";
					level.setEnemy(player.getX(), player.getY(), null);
					txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
					barUpdates();
					showHelp();
				}
				if(player.getHp() == 0)
				{
					lblStatus.setText("YOU DIED!");
					player.death();
					level = null;
					gameStatus="main";
					showHelp();
					level = new Level();
					level.generateMap();
					barUpdates();
					txtAreaInventory.setText(player.InventoryListString());
					txtAreaEvents.setText("Welcome to Concept Killer!\nPlease Enter Commands in the TextField to Play.");
				}
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
			showHelp();
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
			lblStatus.setText("");
			barUpdates();
		} else{
			lblStatus.setText("Can't Move North!");
		}
	}

	@FXML
	public void goSouth()
	{
		if(player.getY()<level.getSize()-1){
			player.setY(player.getY()+1);
			txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
			txtActionLog.appendText("South\n");
			lblStatus.setText("");
			barUpdates();
		}else{
			lblStatus.setText("Can't Move South!");
		}
	}

	@FXML
	public void goWest()
	{
		if (player.getX()>0){
			player.setX(player.getX()-1);
			txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
			txtActionLog.appendText("West\n");
			lblStatus.setText("");
			barUpdates();
		}else{
			lblStatus.setText("Can't Move West!");
		}
	}

	@FXML
	public void goEast()
	{
		if (player.getX()<level.getSize()-1){
			player.setX(player.getX()+1);
			txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
			txtActionLog.appendText("East\n");
			lblStatus.setText("");
			barUpdates();
		}else{
			lblStatus.setText("Can't Move East!");
		}
	}

	public void showHelp(){
		String value = "";
		if (gameStatus == "main"){
			value = "Aviable Actions: \n north \n south \n east \n west \n grab \n inventory \n attack";
		}
		else if(gameStatus == "fighting"){
			value = "Aviable Actions: \n attack \n run";
		}
		txtAreaActions.setText(value);
	}
	// Method for placing text into room description

	public void barUpdates()
	{
		double currentPlayerHealth = (double)player.getHp()/100;
		double currentEnemyHealth;
		try{
			currentEnemyHealth = (double)level.getEnemy(player.getX(), player.getY()).getHp() / 100;
		} catch(Exception e){
			currentEnemyHealth=0;
		}
		double currentXP = (double)player.getXp() / 100;

		healthBar.setProgress(currentPlayerHealth);
		hpEnemy.setProgress(currentEnemyHealth);
		xpBar.setProgress(currentXP);
	}

	public void useItem(int number){
		number=number-49;
		System.out.println(number);
		Item item = player.getInventoryItem(number);
		if(item.getType()==Item.types.Weapon){
			player.setActiveWeapon(item);
		} else if (item.getType()==Item.types.Meds){
			int healthLost = 100-player.getHp();
			if (healthLost-item.getValue()>=0){
				player.setHp(player.getHp()+(int)item.getValue());
			}else{
				player.setHp(100);
			}
			barUpdates();
			player.removeInventoryItem(number);
			txtAreaInventory.setText(player.InventoryListString());
		}
	}
}