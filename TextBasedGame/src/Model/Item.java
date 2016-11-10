package Model;

public class Item
{
    private String name;
    //private double weight;

    //removed args so i can set later  String n, double a
    public Item() {
        //this.name = n;
        //this.attack_power = a;
    }

    /*
    Item[] items =
    	{
    			new Item("Sword", 10), new Item("Knife", 7), new Item("Gun", 30),
    			new Item("Excalibur", 100)
    	};

    */
    //////////////////////////////////////////////////////////////////////////////
    /*
     *
     * Code for Shannon
     *
     */
    private String title;
    private String pickupText;
    private int weight = 1;


    public int getWeight(){
    	return weight;
    }

    public void setWeight(int value){
    	this.weight = value;
    }

    public String getPickupText(){
    	return pickupText;
    }

    public void setPickupText(String value){
    	this.pickupText = value;
    }


    public String getTitle(){
    	return title;
    }

    public void setTitle(String value){
    	this.title = value;
    }
}
