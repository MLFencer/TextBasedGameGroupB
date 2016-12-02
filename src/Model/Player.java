package Model;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity
{

	private int x, y;
	private int attributePoints = 10;
	private Item activeWeapon;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private final double MULTIPLIER = 1.5;
	private double nextLvl = 100;
	private int maxHp;

	public Player(String nameIn, int hpIn, int maxHpIn, int dmgIn, int strIn, int dexIn, int conIn, double xpIn, int levelIn, int xIn, int yIn)
	{
		super(nameIn, hpIn, dmgIn, strIn, dexIn, conIn, xpIn, levelIn);
		x = xIn;
		y = yIn;
		maxHp = maxHpIn;
	}


	public void updateStats()
	{
		setHp(getMaxHp() + getCon());
		setLevel(getLevel() + 1);
		setPoints(getPoints() + 1);
		nextLvl = nextLvl * MULTIPLIER;
	}
	public int block(Enemy enemy)
	{
		Random rollToBlock = new Random();
		int blockChance = rollToBlock.nextInt(20) + (1 + getCon());
		if(blockChance >= 25)
		{
			return enemy.takeDmg((int)(getActiveWeapon().getValue() + getDmg()));			
		}
		if(blockChance == 11)
		{
			return 0;
		}
		else
			return 0;
	}
	public int dodge(Enemy enemy)
	{
		Random rollToDodge = new Random();
		int dodgeChance = rollToDodge.nextInt(20) + (1 + getDex());
		if(dodgeChance >= 25)
		{
			return enemy.takeDmg((int)(getActiveWeapon().getValue() + getDmg()));			
		}
		else
			return 0;
	}
	public int getAttributePoints() {
		return attributePoints;
	}


	public void setAttributePoints(int attributePoints) {
		this.attributePoints = attributePoints;
	}


	public int getMaxHp() 
	{
		return maxHp;
	}


	public void setMaxHp(int maxHp) 
	{
		this.maxHp = maxHp;
	}


	public int calculateModifier(int attribute)
	{
		if(attribute < 8)
		{
			return -2;
		}
		if(attribute == 8 || attribute == 9)
		{
			return -1;
		}
		if(attribute == 10)
		{
			return 0;
		}
		if(attribute == 11 || attribute == 12)
		{
			return 1;
		}
		if(attribute == 13 || attribute == 14)
		{
			return 2;
		}
		if(attribute == 15 || attribute == 16)
		{
			return 3;
		}
		else
			return 0;
	}
	
	public String gainXp(double xpDrop)
	{
		String gainedXp = (int)xpDrop + "xp gained";

		setXp(getXp() + (int)xpDrop);


		if (getXp() >= nextLvl)
		{
			setXp(getXp()- nextLvl);
			levelUp();

		}
		return gainedXp;
	}

	public String levelUp()
	{
		String levelUp = "Level up!";
		updateStats();
		return levelUp;
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


	@SuppressWarnings("rawtypes")
	public ArrayList getInventory()
	{
		return inventory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setInventory(ArrayList inventory)
	{
		this.inventory = inventory;
	}

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
	public double getNextLevel()
	{
		return nextLvl;
	}
	public int getPoints()
	{
		return attributePoints;
	}
	public void setPoints(int points)
	{
		this.attributePoints = points;
	}
}