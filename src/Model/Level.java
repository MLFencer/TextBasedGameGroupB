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
				PandorasBox p=new PandorasBox();
				Item it =p.getItems().get(rand.nextInt(6));
				Enemy e = p.getEnemies().get(rand.nextInt(5));
				Room r = new Room(it,e);
				ArrayList t =(ArrayList)rooms.get(i);
				t.add(j,r);
			}
		}
	}

	public Enemy getEnemy(int x, int y){
		ArrayList t = (ArrayList)rooms.get(x);
		Room r = (Room)t.get(y);
		return r.getEnemy();
	}

	public Item getItem(int x,int y){
		ArrayList t = (ArrayList)rooms.get(x);
		Room r = (Room)t.get(y);
		return r.getItem();
	}

	public String getRoom(int x, int y) {
        ArrayList t =(ArrayList)rooms.get(x);
        Room r =(Room)t.get(y);
        String l,m;
        try {
           l="a "+r.getItem().toString();
        }catch (NullPointerException e){
         l="No Loot";
        }
        try {
            m="a "+r.getEnemy().getName();
        }catch (NullPointerException e){
            m="No Monster";
        }

        return "There is " + m + " and " + l + " in the room";
    }


}
