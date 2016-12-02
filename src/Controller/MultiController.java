package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class MultiController
{
	@FXML private Button btnNorth, btnSouth, btnEast, btnWest, btnAction;
	@FXML private TextArea txtAreaRoom;
	@FXML
	private TextArea txtAreaEvents;
	@FXML
	private TextArea txtAreaInventory;
	@FXML
	private TextArea txtAreaActions;
	@FXML
	private TextArea txtActionLog;
	@FXML
	private TextArea txtAreaLoot;
	@FXML
	private TextArea txtAreaEnemies;
	@FXML private TextField txtPlayerActions;
	@FXML private ProgressBar healthBar;
	@FXML private Label lblStatus,lblPlayerName;

	private String username;
	private String host="chimeragaming.org";
	private int port=25565;

	
	public void setUsername(String user)
	{
		this.username = user;
	}
	
	
	private static boolean connected;

	public enum ConnectionDisplayState{
		DISCONNECTED, ATTEMPTING , CONNECTED, AUTOCONNECTED, AUTOATTEMPTING
	}
	private SocketClient socket;


	public synchronized static void setIsConnected(boolean c) {
        connected = c;
    }

    private void connect() {
        socket = new SocketClient(host, port, new FxSocketListener());
        socket.connect();
    }

	public void initialize(){
		connected=false;
		while(username==null){
			username=Other.u;
		}
		lblPlayerName.setText(username);
		boolean loggedIn=false;
		connect();
		while(!loggedIn){
			if(connected){
				sendMessage("user:"+username);
				loggedIn=true;
			}	
			System.out.println(loggedIn);
		}

	}



	// Method for assigning cardinality buttons to input into txtPlayerActions.
	@FXML
	public void onPlayerAction(ActionEvent event)
	{
		mainRunner();
	}

	@FXML
	public void mainRunner(){
		String command = txtPlayerActions.getText();
		String extra="";
		/*if(command.contains("use ")){
			System.out.println(command.substring(4));
			useItem(Integer.parseInt(command.substring(4)));
			command="use";
		}*/
		if(command.contains("help ")){
			help(command.substring(5));
			command="help";
		}
		if(command.contains("attack ")){
			extra=command;
			command="attack";
		}
		if(command.contains("say ")){
			extra=command;
			command="say";
		}

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
				sendMessage("back");
				break;
			case "grab":
				sendMessage("grab");
				break;
			case "attack":
				sendMessage(extra);
				break;
			case "list":
				sendMessage("list");
				break;
			case "scan room":
				sendMessage("scan room");
				break;
			case "say":
				sendMessage(extra);
				break;
		}

	}


	private void help(String substring) {
		switch(substring){
		case "north":
			break;
		}

	}

	@FXML
	public void goNorth()
	{
		sendMessage("north");
	}

	@FXML
	public void goSouth()
	{
		sendMessage("south");
	}

	@FXML
	public void goWest()
	{
		sendMessage("west");
	}

	@FXML
	public void goEast()
	{
		sendMessage("east");
	}

	public void showHelp(){

	}
	// Method for placing text into room description

	public void barUpdates()
	{
		double currentPlayerHealth = 0.0;
		healthBar.setProgress(currentPlayerHealth);
	}

	public void useItem(int number){

	}

    class FxSocketListener implements SocketListener {

        @Override
        public void onMessage(String line) {
        	System.out.println(line);
        	String outLine=line;
        	if(line.contains("tM")){
        		txtAreaEvents.setText(outLine.substring(3,outLine.length()));}
        	/*} else{
        		txtAreaEvents.setText(outLine);
        	}*/

        }

		@Override
		public void onClosedStatus(boolean isClosed) {
			// TODO Auto-generated method stub

		}
    }

    private void sendMessage(String s) {
            socket.sendMessage(s);
    }
}
