package Model;
import java.util.ArrayList;

public class Player extends Entity 
{
	
	private int x, y;
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

	public ArrayList getInventory() 
	{
		return inventory;
	}

	public void setInventory(ArrayList inventory) 
	{
		this.inventory = inventory;
	}

}
