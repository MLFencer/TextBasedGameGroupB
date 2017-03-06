

import java.util.ArrayList;

public class PandorasBox {

	private Item i1= new Item("Excalibur", 30, Item.types.Weapon, Item.weaponTypes.Light, 10);
	private Item i2= new Item("Iron Sword",10, Item.types.Weapon, Item.weaponTypes.Light, 1);
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

	//These Items will not be added to the ArrayList!
	public Item fist=new Item("Fist",0,Item.types.Weapon, Item.weaponTypes.Light, 999999);
}