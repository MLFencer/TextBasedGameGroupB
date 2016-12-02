package Model;

public class Room {
	private Item item;
	private Enemy enemy;
	private String description;

	public Room(Item i, Enemy e, String d){
		this.enemy=e;
		this.item=i;
		this.description = d;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
