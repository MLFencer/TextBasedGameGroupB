package Model;

import java.util.ArrayList;

public class PandorasBox {

	private Enemy e1= new Enemy("Hydra",30,10,0,0,0,10,1);
	private Enemy e2= new Enemy("Scarlac",30,10,0,0,0,10,1);
	private Enemy e3= new Enemy("Kree Warrior",40,10,0,0,0,10,1);
	private Enemy e4= new Enemy("Skeleton Warrior",10,10,0,0,0,10,1);
	private Enemy e5= new Enemy("Living Armor",15,10,0,0,0,10,1);
	private Enemy e6= new Enemy("Killer Croc",50,10,0,0,0,10,1);
	private Enemy e7= new Enemy("John Cena",50,20,0,0,0,10,1);
	private Enemy e8= new Enemy("Klingon Warrior",40,10,0,0,0,10,1);
	private Enemy e9= new Enemy("StormTrooper",15,5,0,0,0,10,1);
	private Enemy e0= new Enemy("Brown Coat",20,10,0,0,0,10,1);
	

	private Item i1= new Item("Excalibur", 30, Item.types.Weapon, Item.weaponTypes.Light, 10);
	private Item i2= new Item("Iron Sword",10, Item.types.Weapon, Item.weaponTypes.Light, 30);
	private Item i3= new Item("War Hammer",20, Item.types.Weapon, Item.weaponTypes.Heavy, 20);
	private Item i4= new Item("Cutlass", 15, Item.types.Weapon, Item.weaponTypes.Light, 25);
	private Item i5= new Item("Bandage",20, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i6= new Item("Healing Potion",100, Item.types.Meds, Item.weaponTypes.Null, 0);
	public Item i7= new Item("Minor Healing Potion",30, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i8= new Item("Suture Kit",40, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i9= new Item("Fishing String and Hook",15, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i0= new Item("Rags",10, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i10= new Item("The First Blade",80, Item.types.Weapon, Item.weaponTypes.Light, 1);

	
	public ArrayList<Item> getItems(){
		ArrayList<Item>i=new ArrayList<Item>();
		i.add(i1);
		i.add(i2);
		i.add(i3);
		i.add(i4);
		i.add(i5);
		i.add(i6);
		i.add(i7);
		i.add(i8);
		i.add(i9);
		i.add(i0);
		i.add(i10);
		return i;
	}
	public ArrayList<Enemy> getEnemies(){
		ArrayList<Enemy>e=new ArrayList<Enemy>();
		e.add(e1);
		e.add(e2);
		e.add(e3);
		e.add(e4);
		e.add(e5);
		e.add(e6);
		e.add(e7);
		e.add(e8);
		e.add(e9);
		e.add(e0);
		return e;
	}

	//These Items will not be added to the ArrayList!
	public Item fist=new Item("Fist",0,Item.types.Weapon, Item.weaponTypes.Light, 999999);
}