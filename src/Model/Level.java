package Model;

import java.util.ArrayList;
import java.util.Random;

public class Level {
	final int SIZE =5; //square this to get number of rooms
	@SuppressWarnings("rawtypes")
	private ArrayList rooms = new ArrayList();

	//Generates a Hardcoded map.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateMap(){
		for (int k=0;k<SIZE;k++){
			rooms.add(new ArrayList<Room>());
		}
		Random rand = new Random();

		for(int i=0;i<SIZE;i++){
			for (int j=0; j<SIZE; j++){
				PandorasBox p=new PandorasBox();
				if(i==0&&j==0){
					Item it =p.getItems().get(1);
					Enemy e =null;
					Room r = new Room(it,e);
					ArrayList t =(ArrayList)rooms.get(i);
					t.add(j,r);
				}else{
				Item it =p.getItems().get(rand.nextInt(p.getItems().size()));
				Enemy e = p.getEnemies().get(rand.nextInt(p.getEnemies().size()));
				Room r = new Room(it,e);
				ArrayList t =(ArrayList)rooms.get(i);
				t.add(j,r);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Enemy getEnemy(int x, int y){
		ArrayList t = (ArrayList)rooms.get(x);
		Room r = (Room)t.get(y);
		return r.getEnemy();
	}

	@SuppressWarnings("rawtypes")
	public Item getItem(int x,int y){
		ArrayList t = (ArrayList)rooms.get(x);
		Room r = (Room)t.get(y);
		return r.getItem();
	}

	@SuppressWarnings("rawtypes")
	public Item removeItem(int x, int y){
		ArrayList t = (ArrayList)rooms.get(x);
		Room r = (Room)t.get(y);
		Item i=r.getItem();
		r.setItem(null);
		return i;
	}

	@SuppressWarnings("rawtypes")
	public String getRoom(int x, int y) {
        ArrayList t =(ArrayList)rooms.get(x);
        Room r =(Room)t.get(y);
        String l,m;
        try {
           l="a "+r.getItem().getName();
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

	public int getSize(){
		return SIZE;
	}

	@SuppressWarnings("unchecked")
	public void setEnemy(int x, int y, Enemy e){
		ArrayList<Room> t = (ArrayList<Room>)rooms.get(x);
		Room r = (Room)t.get(y);
		r.setEnemy(e);
	}


}
