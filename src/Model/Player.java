package Model;
import java.util.ArrayList;

public class Player extends Entity
{

	private int x, y;
	private Item activeWeapon;
	@SuppressWarnings("rawtypes")
	private ArrayList<Item> inventory = new ArrayList<Item>();

	public Player(String nameIn, int hpIn, int dmgIn, int strIn, int dexIn, int conIn, double xpIn, int levelIn, int xIn, int yIn)
	{
		super(nameIn, hpIn, dmgIn, strIn, dexIn, conIn, xpIn, levelIn);
		x = xIn;
		y = yIn;
	}

	public String InventoryListString(){
		String out ="";
		int i=0;
		for (Item p: inventory){
			i++;
			out+=i+". "+p.getName()+"\n";
		}
		return out;
	}

	public void removeInventoryItem(int i){
		inventory.remove(i);
	}

	public Item getInventoryItem(int i){
		System.out.println(i);
		System.out.println(inventory.size());
		System.out.println(inventory.get(i).getName());
		return inventory.get(i);
	}

	public Item getActiveWeapon() {
		return activeWeapon;
	}

	public void setActiveWeapon(Item activeWeapon) {
		this.activeWeapon = activeWeapon;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getInventory()
	{
		return inventory;
	}

	@SuppressWarnings("rawtypes")
	public void setInventory(ArrayList inventory)
	{
		this.inventory = inventory;
	}

	@SuppressWarnings("unchecked")
	public void addItem(Item i){
		this.inventory.add(i);
	}

	public void death()
	{
		this.x = 0;
		this.y = 0;
		setHp(100);
		inventory.clear();

	}
}