package Model;

public class Item
{
    private String name;
    private double value;

    public Item(String n, double a) {
        this.name = n;
        this.value = a;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setHealth(double health) {
		this.value = health;
	}


}
