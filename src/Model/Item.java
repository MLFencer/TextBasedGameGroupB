package Model;

public class Item
{
    private String name;
 

	private double value;
    private int uses;
    public enum types{
    	Weapon,
    	Meds
    	}
    public enum weaponTypes
    {
    	Heavy,
    	Light,
    	Null
    }
    types type;
    weaponTypes weaponType;

    public Item(String n, double a, types t, weaponTypes w, int usesIn) {
        this.name = n;
        this.value = a;
        this.type=t;
        this.weaponType = w;
        this.uses = usesIn;
    }
    public int getUses() 
    {
 		return uses;
 	}

 	public void setUses(int uses) 
 	{
 		this.uses = uses;
 	}
	public types getType() 
	{
		return type;
	}

	public void setType(types type) {
		this.type = type;
	}
	public weaponTypes getWeaponType()
	{
		return weaponType;
	}
	public void setWeaponType(weaponTypes weaponType)
	{
		this.weaponType = weaponType;
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