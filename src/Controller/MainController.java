package Controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.*;
import Model.Item;
import Model.Level;
import Model.PandorasBox;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import java.util.Scanner;
public class MainController
{
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest, btnAction;
	@FXML private TextArea txtAreaRoom;
	@FXML public TextArea txtAreaEvents;
	@FXML private TextArea txtAreaInventory;
	@FXML private TextArea txtAreaActions;
	@FXML private TextArea txtActionLog;
	@FXML private TextArea txtAreaLoot;
	@FXML private TextArea txtAreaEnemies;
	@FXML private TextField txtPlayerActions;
	@FXML private ProgressBar healthBar, xpBar, hpEnemy;
	@FXML private Label lblStatus;
	@FXML private Label lblPlayerLevel;
	@FXML private Label lblPlayerName;

	private int lastX,lastY,enemyMaxHealth;

	public static Player player = new Player("name", 90, 90, 5, 10, 10, 10, 0, 1, 0, 0);
	public static Level level = new Level();
	public static String gameStatus;
	Timer timer;
	int aType;
	Random attackType = new Random();

	public void initialize(){
		txtPlayerActions.requestFocus();
		txtAreaEvents.appendText("Welcome to Concept Killer!\nPlease Enter Commands in the TextField to Play.\n");
		System.out.println(player.getXp() + " / " + player.getNextLevel());
		//gameStatus="starting";
		gameStatus="main";		
		level.generateMap();
		showHelp();
		barUpdates();
		nameLabelUpdates();
		PandorasBox pan = new PandorasBox();
		player.setActiveWeapon(pan.fist);
		Item h=pan.i7;
		player.addItem(h);
		player.updateStats();
		txtAreaInventory.setText(player.InventoryListString());
		txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));

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
	/*
	public final static Thread subject1 =  new Thread(new Runnable()
	{
		public void run()
		{			
			txtAreaEvents.setText(Timing.EnterTimer());
			barUpdates();
		}
	});
	*/
/*
	@SuppressWarnings("resource")
	public void characterCreation()
	{
		txtAreaEvents.setText("Create your character.\n");
		txtAreaEvents.appendText("Enter your character name.\n");
		txtAreaEvents.appendText("Now assign points to your attributes\n");
		txtAreaEvents.appendText("The attributes are strength, dexterity, and constitution\n");
		txtAreaEvents.appendText("Strength decides heavy weapon damage\n");
		txtAreaEvents.appendText("Dexterity decides light weapon damage\n");
		txtAreaEvents.appendText("Constitution decides health points\n");
		txtAreaEvents.appendText("Current Attributes\n");
		txtAreaEvents.appendText("Strength: " + player.getStr() + "\n");
		txtAreaEvents.appendText("Dexterity: " + player.getDex()+ "\n");
		txtAreaEvents.appendText("Constitution: " + player.getCon()+ "\n");
		txtAreaEvents.appendText("You have " + player.getPoints() + " points to spend\n");
	}
*/	
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
			System.out.println(command.substring(4));
			useItem(Integer.parseInt(command.substring(4)));
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
			case "back":
				player.setX(lastX);
				player.setY(lastY);
				txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
				txtActionLog.appendText("Went Back\n");
				barUpdates();
				break;	
			case "grab":
				if(level.getEnemy(player.getX(), player.getY())==null){
					Item item=level.removeItem(player.getX(), player.getY());
					player.addItem(item);
					txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
					txtAreaInventory.setText(player.InventoryListString());
					txtActionLog.appendText("Grabbed "+player.getInventoryItem(player.getInventory().size()-1).getName()+"\n");		
				}else{
					lblStatus.setText("Can't Collect Loot Till Enemy is Dead");
				}
				break;
				/*case "inventory":
				@SuppressWarnings("unchecked")
				ArrayList<Item> i = player.getInventory();
				String s=" ";
				for (Item x: i){
					s+=(x.getName()+", ");
				}
				txtAreaEvents.setText(s);
				break;*/
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
						timer.cancel();
						attack();
					}
				}catch(Exception e){
					lblStatus.setText("Nothing to fight!");
				}
				break;
			case "use":
				txtActionLog.appendText("Used Item or Equipped Weapon\n");
				break;
			case "assign strength":
				player.setStr(player.getStr() + 1);
				player.updateStats();
				break;
			case "assign dexterity":
				player.setDex(player.getDex() + 1);
				player.updateStats();
				break;
			case "assign constitution":
				player.setCon(player.getCon() + 1);
				player.updateStats();
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
			command="use";
		}
		txtPlayerActions.setText("");
		switch(command){
		case "attack": 			

			timer.cancel();
			combatTimer(5); 

			if(level.getEnemy(player.getX(), player.getY()).getHp() != 0)
			{
				txtActionLog.appendText("Hit Enemy\n");
				
				switch(player.getActiveWeapon().getWeaponType())
				{
					
					case Heavy:
						txtAreaEvents.appendText("You did "+Integer.toString(level.getEnemy(player.getX(), player.getY()).takeDmg(player.attack(player.calculateModifier(player.getStr()))+(int)player.getActiveWeapon().getValue())) + " damage to enemy!\n");
						System.out.println(level.getEnemy(player.getX(), player.getY()).getHp());
						txtAreaEvents.appendText(level.getEnemy(player.getX(), player.getY()).getName()+" did "+Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack(0))) + " damage to you!\n");					
						aType =  attackType.nextInt(2);
						break;
					case Light:
						txtAreaEvents.appendText("You did "+Integer.toString(level.getEnemy(player.getX(), player.getY()).takeDmg(player.attack(player.calculateModifier(player.getDex()))+(int)player.getActiveWeapon().getValue())) + " damage to enemy!\n");
						System.out.println(player.getHp());
						txtAreaEvents.appendText(level.getEnemy(player.getX(), player.getY()).getName()+" did "+Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack(0))) + " damage to you!\n");
						aType =  attackType.nextInt(2);
						break;
					case Null:
						break;						
				}
				player.getActiveWeapon().setUses(player.getActiveWeapon().getUses()-1);
				

				if(aType == 0)
				{
					lblStatus.setText("The enemy will use a light attack!");
				}
				if(aType == 1)
				{
					lblStatus.setText("The enemy will use a heavy attack!");
				}
				
				if(player.getActiveWeapon().getUses() == 0)
				{
					lblStatus.setText("Your weapon broke!");					
					player.removeInventoryItem(player.getInventory().indexOf(player.getActiveWeapon()));
					txtAreaInventory.setText(player.InventoryListString());
					PandorasBox pan = new PandorasBox();
					player.setActiveWeapon(pan.fist);
					
				}
				barUpdates();
				if(level.getEnemy(player.getX(), player.getY()).getHp() == 0){
					timer.cancel();
					txtAreaEvents.appendText("The enemy died!\n");
					lblStatus.setText("");
					txtAreaEvents.appendText(player.gainXp(level.getEnemy(player.getX(), player.getY()).getXp()));					
					gameStatus="main";
					level.setEnemy(player.getX(), player.getY(), null);
					txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
					barUpdates();
					levelLabelUpdates();
					nameLabelUpdates();
					System.out.println(player.getXp() + " / " + player.getNextLevel());
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
					PandorasBox pan = new PandorasBox();
					player.setActiveWeapon(pan.fist);
					txtActionLog.setText("");
					player.setXp(0);
					player.setLevel(0);
					barUpdates();
					txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
					PandorasBox pan1 = new PandorasBox();
					player.setActiveWeapon(pan1.fist);
					Item h=pan1.i7;
					player.addItem(h);
					txtAreaInventory.setText(player.InventoryListString());
					txtAreaInventory.setText(player.InventoryListString());
					txtAreaEvents.setText("Game Restarted\nWelcome to Concept Killer!\nPlease Enter Commands in the TextField to Play.");
				}
			}
			else
				lblStatus.setText("Nothing to fight!");
			break;
		case "block":
			timer.cancel();
			combatTimer(5);
			if(aType == 1)
			{
				lblStatus.setText("Heavy attacks can't be blocked!\n");
				break;
			}	
			else if(aType == 0)
			{
				int counterAttackB = 0;
				counterAttackB = player.block(level.getEnemy(player.getX(), player.getY()));
				txtAreaEvents.appendText("Blocked!\n");
				if(counterAttackB > 0)
				{
					txtAreaEvents.appendText("Counter Attack! You did " + counterAttackB + " damage!\n");
					barUpdates();
				}
				else if(counterAttackB == 0)
				{
					txtAreaEvents.appendText("Failed to block! You take " + player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack(0)) + " damage!\n");
					barUpdates();
				}
				txtActionLog.appendText("Attempted to block Enemy\n");
				break;
			}
		case "dodge":
			timer.cancel();
			combatTimer(5);
			if(aType == 0)
			{
				lblStatus.setText("light attacks can't be dodged!\n");
				break;
			}
			else if(aType == 1)
			{
				int counterAttackD = 0;
				counterAttackD = player.dodge(level.getEnemy(player.getX(), player.getY()));
				txtAreaEvents.appendText("Dodged!\n");
				if(counterAttackD > 0)
				{
					txtAreaEvents.appendText("Counter Attack! " + "You did " + counterAttackD + " damage!\n");
					barUpdates();
				}
				else if(counterAttackD == 0)
				{
					txtAreaEvents.appendText("Failed to dodge! You take " + player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack(0)) + " damage!\n");
					barUpdates();
				}
				txtActionLog.appendText("Attempted to dodge Enemy\n");
				break;
			}			
		case "run":
			gameStatus = "main";
			showHelp();
			player.setX(lastX);
			player.setY(lastY);
			txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
			txtActionLog.appendText("Ran!\n");
			txtAreaEvents.appendText("You Bravely Ran Away!\nYou Bravely Turned Your Tail and Fled!\n");
			txtAreaEvents.appendText("Combat Disengaged\n");
			barUpdates();
			break;
		case "use":
			txtActionLog.appendText("Used Item or Equipped Weapon\n");
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
	public void combatTimer(int seconds)
	{
		timer = new Timer();
		timer.schedule(new enemyAttack(), seconds * 1000, seconds * 1000);

	}
	public void enterTimer(int seconds)
	{
		timer = new Timer();
		timer.schedule(new enemyAttack(), seconds * 1000 , seconds * 1000);

	}
	class enemyAttack extends TimerTask
	{
		public void run()
		{
			if(aType == 0)
			{
				lblStatus.setText("The enemy will use a light attack!");
			}
			if(aType == 1)
			{
				lblStatus.setText("The enemy will use a heavy attack!");
			}
			long startTime = System.nanoTime();			
			System.out.println("attack debug");
			long elapsedTime = System.nanoTime() - startTime;

			System.out.println(aType);
			if(aType == 0 && level.getEnemy(player.getX(), player.getY()) != null)
			{
				txtAreaEvents.appendText(level.getEnemy(player.getX(), player.getY()).getName()+" did "+Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack(0) - 3)) + " damage to you!\n");
			}
			if(aType == 1 && level.getEnemy(player.getX(), player.getY()) != null)
			{
				txtAreaEvents.appendText(level.getEnemy(player.getX(), player.getY()).getName()+" did "+Integer.toString(player.takeDmg(level.getEnemy(player.getX(), player.getY()).attack(0))) + " damage to you!\n");
			}
			
			System.out.println(elapsedTime/10000);
			barUpdates();
		}
	}
	@FXML
	public void goNorth()
	{
		if (level.getEnemy(player.getX(), player.getY())==null){
			if(player.getY()>0){
				lastX=player.getX();
				lastY=player.getY();
				player.setY(player.getY()-1);
				txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
				enemyMaxHealth = level.getEnemy(player.getX(), player.getY()).getHp();
				enterTimer(10);
				txtActionLog.appendText("North\n");
				lblStatus.setText("");
				barUpdates();
			} else{
				lblStatus.setText("Can't Move North!");
			}
		} else{
			lblStatus.setText("There is an Enemy Blocking the Path!");
		}
	}

	@FXML
	public void goSouth()
	{
		if (level.getEnemy(player.getX(), player.getY())==null){
			if(player.getY()<level.getSize()-1){
				lastX=player.getX();
				lastY=player.getY();
				player.setY(player.getY()+1);
				txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
				enemyMaxHealth = level.getEnemy(player.getX(), player.getY()).getHp();
				enterTimer(10);
				txtActionLog.appendText("South\n");
				lblStatus.setText("");
				barUpdates();
			}else{
				lblStatus.setText("Can't Move South!");
			}
		}else{
			lblStatus.setText("There is an Enemy Blocking the Path!");
		}
		txtPlayerActions.requestFocus();
	}

	@FXML
	public void goWest()
	{
		if (level.getEnemy(player.getX(), player.getY())==null){
			if (player.getX()>0){
				lastX=player.getX();
				lastY=player.getY();
				player.setX(player.getX()-1);
				txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
				enemyMaxHealth = level.getEnemy(player.getX(), player.getY()).getHp();
				enterTimer(10);
				txtActionLog.appendText("West\n");
				lblStatus.setText("");
				barUpdates();
			}else{
				lblStatus.setText("Can't Move West!");
			}
		}else{
			lblStatus.setText("There is an Enemy Blocking the Path!");
		}
		txtPlayerActions.requestFocus();
	}

	@FXML
	public void goEast()
	{
		if (level.getEnemy(player.getX(), player.getY())==null){
			if (player.getX()<level.getSize()-1){
				lastX=player.getX();
				lastY=player.getY();
				player.setX(player.getX()+1);
				txtAreaRoom.setText(level.getRoom(player.getX(), player.getY()));
				System.out.println("entered debug");
				enemyMaxHealth = level.getEnemy(player.getX(), player.getY()).getHp();
				enterTimer(10);
				txtActionLog.appendText("East\n");
				lblStatus.setText("");
				barUpdates();
			}else{
				lblStatus.setText("Can't Move East!");
			} 
		}else{
			lblStatus.setText("There is an Enemy Blocking the Path!");
		}
		txtPlayerActions.requestFocus();
	}

	public void showHelp(){
		String value = "";
		if (gameStatus == "main"){
			value = "Aviable Actions: \n north \n south \n east \n west \n grab \n attack\n use \'#\'\nback";
		}
		else if(gameStatus == "fighting"){
			value = "Aviable Actions: \n attack \n run\n use \'#\'";
		}
		txtAreaActions.setText(value);
	}
	// Method for placing text into room description

	public void barUpdates()
	{
		double currentPlayerHealth = (double)player.getHp()/(double)player.getMaxHp();
		double currentEnemyHealth;
		try{
			currentEnemyHealth = (double)level.getEnemy(player.getX(), player.getY()).getHp() / enemyMaxHealth;
		} catch(Exception e){
			currentEnemyHealth=0;
		}
		double currentXP = (double)player.getXp() / player.getNextLevel();

		healthBar.setProgress(currentPlayerHealth);
		hpEnemy.setProgress(currentEnemyHealth);
		xpBar.setProgress(currentXP);
	}
	public void levelLabelUpdates()
	{
		lblPlayerLevel.setText("level: " + Integer.toString(player.getLevel()));
	}
	public void nameLabelUpdates()
	{
		lblPlayerName.setText("Attribute Points: " + player.getPoints());
	}

	public void useItem(int number){
		System.out.println(number);
		number=number-1;
		System.out.println("Inv "+player.getInventory().size());
		if(number<player.getInventory().size()){
			System.out.println(number);
			Item item = player.getInventoryItem(number);
			if(item.getType()==Item.types.Weapon){
				player.setActiveWeapon(item);
				if(gameStatus=="fighting"){
					txtAreaEvents.appendText("Equipped "+item.getName()+" as Main Weapon");
				}else{
					txtAreaEvents.setText("Equipped "+item.getName()+" as Main Weapon");
				}
			} else if (item.getType()==Item.types.Meds){
				int healthLost = 100-player.getHp();
				if (healthLost-item.getValue()>=0){
					player.setHp(player.getHp()+(int)item.getValue());
					if(gameStatus=="fighting"){
						txtAreaEvents.appendText("Healed "+item.getValue()+" Health Points. Health is now "+player.getHp()+"\n");
					}else{
						txtAreaEvents.setText("Healed "+item.getValue()+" Health Points. Health is now "+player.getHp()+"\n");
					}
				}else{
					if(gameStatus=="fighting"){
						txtAreaEvents.appendText("Healed "+ (100-player.getHp()) +" Health Points. Your Health is now 100\n");
					}else{
						txtAreaEvents.setText("Healed "+ (100-player.getHp()) +" Health Points. Your Health is now 100\n");
					}
					player.setHp(100);
				}
				barUpdates();
				player.removeInventoryItem(number);
				txtAreaInventory.setText(player.InventoryListString());
			}
		}else{
			txtAreaEvents.setText("You don't have that many items!\n");
			//lblStatus.setText(gameStatus);
		}
	}
}
