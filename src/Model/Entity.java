package Model;
import java.util.Random;

public class Entity
{

	private int dmg;
	private int hp;
	private int str;
	private int dex;
	private int con;
	private double xp;
	private int level;
	private double currentLvl = 100/1.5;
	private final double MULTIPLIER = 1.5;
	private double nextLvl = currentLvl * MULTIPLIER;

	public double getNextLevel()
	{
		return nextLvl;
	}

	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private String name;

	public Entity(String nameIn, int hpIn, int dmgIn, int strIn, int dexIn, int conIn, double xpIn, int levelIn)
	{
		this.name = nameIn;
		this.hp = hpIn;
		this.dmg = dmgIn;
		this.str = strIn;
		this.dex = dexIn;
		this.con = conIn;
		this.xp = xpIn;
		this.level = levelIn;
	}

	public int getDmg()
	{
		return dmg;
	}
	public void setDmg(int dmg)
	{
		this.dmg = dmg;
	}
	public int getHp()
	{
		return hp;
	}
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getStr()
	{
		return str;
	}
	public void setStr(int str)
	{
		this.str = str;
	}
	public int getDex()
	{
		return dex;
	}
	public void setDex(int dex)
	{
		this.dex = dex;
	}
	public int getCon()
	{
		return con;
	}
	public void setCon(int con)
	{
		this.con = con;
	}

	public int attack()
	{
		Random rollToHit = new Random();
		int hitChance = rollToHit.nextInt(20)+1;

		if(hitChance == 20){
			return dmg*2;
			// crit
		}
		if(hitChance == 1){
			return 0;
			// miss
		}
		else
			return dmg;
	}

	public int takeDmg(int dmg)
	{
		hp -= dmg;
		if(hp <= 0)
			hp = 0;
		return dmg;
	}

	public String gainXp(double xpDrop)
	{
		String gainedXp = (int)xpDrop + " gained";

		setXp(xp + (int)xpDrop);


		if (xp >= nextLvl)
		{
			levelUp();
			currentLvl = nextLvl;
		}
		return gainedXp;
	}

	public String levelUp()
	{
		String levelUp = "Level up!";
		setLevel(level + 1);

		return levelUp;
	}

}
