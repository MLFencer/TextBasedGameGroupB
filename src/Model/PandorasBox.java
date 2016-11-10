package Model;

import java.util.ArrayList;

public class PandorasBox {

	private Enemy e1= new Enemy("Hydra",100,10,0,0,0);
	private Enemy e2= new Enemy("Scarlac",100,10,0,0,0);
	private Enemy e3= new Enemy("Kree Warrior",100,10,0,0,0);
	private Enemy e4= new Enemy("Skeleton Warrior",100,10,0,0,0);
	private Enemy e5= new Enemy("Living Armor",100,10,0,0,0);

	private Item i1= new Item("Excalibur", 16, Item.types.Weapon);
	private Item i2= new Item("Iron Sword",8, Item.types.Weapon);
	private Item i3= new Item("War Hammer",12, Item.types.Weapon);
	private Item i4= new Item("Cutlass", 9, Item.types.Weapon);
	private Item i5= new Item("Bandage",20, Item.types.Meds);
	private Item i6= new Item("Healing Potion",100, Item.types.Meds);

	public ArrayList<Item> getItems(){
		ArrayList<Item>i=new ArrayList<Item>();
		i.add(i1);
		i.add(i2);
		i.add(i3);
		i.add(i4);
		i.add(i5);
		i.add(i6);
		return i;
	}
	public ArrayList<Enemy> getEnemies(){
		ArrayList<Enemy>e=new ArrayList<Enemy>();
		e.add(e1);
		e.add(e2);
		e.add(e3);
		e.add(e4);
		e.add(e5);
		return e;
	}
}
