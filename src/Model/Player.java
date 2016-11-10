package Model;
import java.util.ArrayList;

public class Player extends Entity
{

	private int x, y;
	@SuppressWarnings("rawtypes")
	private ArrayList inventory = new ArrayList<Item>();


	public Player(String nameIn, int hpIn, int dmgIn, int strIn, int dexIn, int conIn, int xIn, int yIn)
	{
		super(nameIn, hpIn, dmgIn, strIn, dexIn, conIn);
		x = xIn;
		y = yIn;
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

}