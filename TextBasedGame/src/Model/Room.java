package Model;

import java.awt.List;
import java.util.ArrayList;

public class Room {
		private String title;
	    private String description;
	    private boolean isOffWorld = false;

	    private ArrayList<String> exits = new ArrayList<String>();
	    ArrayList<Item> items = new ArrayList<Item>();

	    public String getTitle(){
	    	return title;
	    }

	    public void setTitle(String title){
	    	this.title = title;
	    }

	    public String getDescription(){
	    	return description;
	    }

	    public void setDescription(String desc){
	    	this.description = desc;
	    }

	    public ArrayList<Item> getItem(){
	    	return items;
	    }

	    public void setItem(ArrayList<Item> value){
	    	this.items = value;
	    }

	    public Room()
	    {
	        exits = new ArrayList();
	        items = new ArrayList<Item>();
	    }

	    public void Describe()
	    {
	        TextBuffer.Add(this.description);
	        TextBuffer.Add(this.GetItemList());
	        TextBuffer.Add(this.GetExitList());
	    }

	    public void ShowTitle()
	    {
	        TextBuffer.Add(this.title);
	    }

	    public Item GetItem(String itemName)
	    {
	        for (Item item : items)
	        {
	            if (item.getTitle().toLowerCase() == itemName.toLowerCase())
	                return item;
	        }
	        return null;
	    }

	    public void AddExit(String directon)
	    {
	        if (this.exits.indexOf(directon) == -1)
	            this.exits.add(directon);

	    }

	    public void RemoveExit(String direction)
	    {
	        if (this.exits.indexOf(direction) != -1)
	            this.exits.remove(direction);
	    }

	    public Boolean CanExit(String direction)
	    {
	        for (String validExit : exits)
	        {
	            if (direction == validExit)
	                return true;
	        }
	        return false;
	    }

		private String GetItemList()
	    {
	        String itemString = "";
	        String message = "Items in Rooms:";
	        String underline = "";

	        if (this.items.size() > 0)
	        {
	            for (Item item : this.items)
	            {
	                itemString += "\n[" + item.getTitle() + "]";
	            }
	        }
	        else
	        {
	            itemString = "\n<none>";
	        }

	        return "\n" + message + "\n" + underline + itemString;
	    }

	    private String GetExitList()
	    {
	        String exitString = "";
	        String message = "Possible Directions: ";
	        String underline = "";

	        if (this.exits.size() > 0)
	        {
	            for (String exitDirection : this.exits)
	            {
	                exitString += "\n[" + exitDirection + "]";
	            }
	        }
	        else
	        {
	            exitString = "\n<none>";
	        }
	        return "\n" + message + "\n" + underline + exitString;
	    }
}

