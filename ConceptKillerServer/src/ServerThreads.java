import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class ServerThreads extends Thread {
	private Socket socket = null;
	Random r = new Random();
	public Player player = new Player("player",100,5,r.nextInt(5),r.nextInt(5));
	public ServerThreads(Socket socket) {
		super("MultiServerThread");
		this.socket = socket;
	}

	public void run() {
		
		

		try (
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()));
				) {
			String inputLine, outputLine;
			
			new Thread(){
				public void run(){
					int i=0;
					while (true){
						if(player.getFromOthers().size()>i){
							System.out.println("To: "+player.getName()+" - "+player.getFromOthers().get(i));
							out.println(player.getFromOthers().get(i));
							i++;
						} else{
							try{
								Thread.sleep(1000);
							}catch(Exception e){
								System.out.print(e);
							}
							
						}
					}
				}
			}.start();
			
			while ((inputLine = in.readLine()) != null) {
				outputLine = processInput(inputLine);
				System.out.println("To: "+player.getName()+" - "+outputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String processInput(String s){
		if (s.startsWith("user:")){
			player.setName(s.substring(5, s.length()));
			s="Welcome "+s.substring(5, s.length());
		}else if(s.contains("attack ")){
			s=attack(s.substring(7));
		}else if(s.contains("say ")){
			for(ServerThreads t:ServerMain.threads){
				t.player.addFromOthers("tM:"+player.getName()+": "+s.substring(4));
			}
			s="sent";
		}else{
			switch(s){
			case "north":
				if(player.getY()-1>=0){
					changeRoom(player.getX(),player.getY()-1);
					s="tM:Went North!";
				} else{
					s="tM:Could Not Move in that Direction.";
				}
				break;
			case "south":
				if(player.getY()+1<=Other.world.SIZE-1){
					changeRoom(player.getX(),player.getY()+1);
					s="tM:Went South!";
				} else{
					s="tM:Could Not Move in that Direction.";
				}
				break;
			case "east":
				if(player.getX()-1>=0){
					changeRoom(player.getX()-1,player.getY());
					s="tM:Went East!";
				} else{
					s="tM:Could Not Move in that Direction.";
				}
				break;
			case "west":
				if(player.getX()+1<=Other.world.SIZE-1){
					changeRoom(player.getX()+1,player.getY());
					s="tM:Went West!";
				} else{
					s="tM:Could Not Move in that Direction.";
				}
				break;
			case "grab":
				Item i = Other.world.removeItem(player.getX(), player.getY());
				player.addItem(i);
				s="tI:"+i.getName();
				break;
			case "attack":
				break;
			case "use":
				break;
			case "scan room":
				s=scanRoom(player.getX(),player.getY());
				break;
			case "list":
				s=playerList();
				//System.out.println(s);
				break;

			}
		}
		//System.out.println(s);
		return s;
	}

	public String attack(String s){
		String returnValue="This Command is WIP";
		
		//if(playerInRoomB(player.getX(),player.getY())){
		//	ArrayList<Integer> a = playerInRoomP(player.getX(),player.getY());
		//	int j=0;
		//	for(int i=0;i<a.size();i++){
		//		if(ServerMain.threads.get((int)a.get(i)).player.getName().equalsIgnoreCase(s)){
		//			j=i;
		//			break;
		//		}
		//	}
		//	ServerThreads st = ServerMain.threads.get(j);
		//	int x=st.player.takeDmg(player.attack((int)(/*player.getActiveWeapon().getValue()+*/player.getDmg())));
		//	returnValue="tM:You delt "+x+" damage to "+st.player.getName();
		//	st.player.addFromOthers("tM:"+player.getName()+" delt "+x+" damage to you!");
	//	} else{
	//		returnValue="tM:No one in room!";
	//	}
		
		return returnValue;
	}

	public String scanRoom(int x, int y){
		String out ="";
		try{
			if(Other.world.getItem(x, y)!=null){
				out="tM:There is a "+Other.world.getItem(x, y).getName();
			}
		}catch(Exception e){
			System.out.println(e);
		}
		if(playerInRoomB(player.getX(), player.getY())){
			ArrayList<Integer> a = playerInRoomP(player.getX(),player.getY());
			for(int i=0;i<a.size();i++){
				out= out+" and "+ServerMain.threads.get((int)a.get(i)).player.getName();
			}
			
		}
		return out;
	}

	public String playerList(){
		String string=" ";
		int i=0;
		while(i<ServerMain.threads.size()){
			string= string+(String)ServerMain.threads.get(i).player.getName()+", ";
			//System.out.println(string);
			i++;
		}
		return "tM:Player List: "+string;
	}

	public boolean playerInRoomB(int x, int y){
		boolean returnValue=false;
		for(ServerThreads t: ServerMain.threads){
			if(t.player.getX()==x && t.player.getY()==y && !t.player.getName().equals(player.getName())){
				returnValue=true;
			}
		}
		return returnValue;
	}

	public ArrayList<Integer> playerInRoomP(int x, int y){
		ArrayList<Integer> returnValue = new ArrayList<Integer>();
		int i=0;
		for(ServerThreads t: ServerMain.threads){
			if(t.player.getX()==x && t.player.getY()==y && !t.player.getName().equals(player.getName())){
				returnValue.add(i);
			}
			i++;
		}
		return returnValue;
	}

	public String changeRoom(int x, int y){
		player.setX(x);
		player.setY(y);
		return Other.world.getRoom(x, y);

	}
}