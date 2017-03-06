
import java.util.Random;

public class Entity
{

	private int dmg;
	private int hp;
	private String name;

	public Entity(String nameIn, int hpIn, int dmgIn)
	{
		this.name = nameIn;
		this.hp = hpIn;
		this.dmg = dmgIn;
	}

	public int attack(int modifier)
	{
		Random rollToHit = new Random();
		int hitChance = rollToHit.nextInt(20)+1;

		if(hitChance == 20){
			return (dmg + modifier)*2;
			// crit
		}
		if(hitChance == 1){
			return 0;
			// miss
		}
		else
			return (dmg + modifier);
	}

	public int takeDmg(int dmg)
	{
		hp -= dmg;
		if(hp <= 0)
			hp = 0;
		return dmg;
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
}