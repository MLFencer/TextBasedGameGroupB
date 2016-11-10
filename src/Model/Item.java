package Model;

public class Item
{
    private String name;
    private double value;
    public enum types{
    	Weapon,
    	Meds
    	}
    types type;

    public Item(String n, double a, types t) {
        this.name = n;
        this.value = a;
        this.type=t;
    }

	public types getType() {
		return type;
	}

	public void setType(types type) {
		this.type = type;
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

	public void setValue(double health) {
		this.value = health;
	}


}
