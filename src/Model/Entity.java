package Model;

public class Entity {
	
	private int dmg;
	private int hp;
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
	
	public int attack(){		
		return dmg;
		// this function is pointless so far but with more advanced combat it will come in handy
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
