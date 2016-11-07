package Model;
import java.util.Random;

public class Entity {
	
	private int dmg;
	private int hp;
	private int str;
	private int dex;
	private int con;
	private String name;
	
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}

	public int attack(){		
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
	
	public int takeDmg(int dmg){
		hp -= dmg;
		
		System.out.println(dmg + " damage done");
		return dmg;
		
		// attack flow will look something like this:
		// player attacks
		// enemy.takeDmg(player.attack());
		// enemy attacks
		// player.takeDmg(enemy.attack());
	}
	
}
