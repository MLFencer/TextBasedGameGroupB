package Model;

public class Room {
	private Item item;
	private Enemy enemy;

	public Room(Item i, Enemy e){
		this.enemy=e;
		this.item=i;
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


}
