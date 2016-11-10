package Model;

import java.util.ArrayList;
import java.util.Random;

public class Level {
	final int SIZE =5; //square this to get number of rooms
	private ArrayList rooms = new ArrayList();

	//Generates a Hardcoded map.
	public void generateMap(){
		for (int k=0;k<SIZE;k++){
			rooms.add(new ArrayList<Room>());
		}
		Random rand = new Random();

		for(int i=0;i<SIZE;i++){
			for (int j=0; j<SIZE; j++){
				Item it = new Item();
				Enemy e = new Enemy();
				Room r = new Room(it,e);
			}
		}
	}

	public Enemy getEnemy(int x, int y){
		ArrayList t = (ArrayList)rooms.get(x);
		Room r = (Room)t.get(y);
		return r.getEnemy();
	}


}
