package Model;

import java.awt.List;
import java.util.ArrayList;

public class Room {
		private String title;
	    private String description;
	    private boolean isOffWorld = false;

	    private List exits;
	    List items;

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

	    public List getItem(){
	    	return items;
	    }

	    public void setItem(List value){
	    	this.items = value;
	    }

	    public Room()
	    {
	        exits = new List();
	        items = new List();
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
	        underline = underline.padLeft(message.length(), '-');

	        if (this.items.Count > 0)
	        {
	            foreach (Item item in this.items)
	            {
	                itemString += "\n[" + item.Title + "]";
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
	        underline = underline.PadLeft(message.Length, '-');

	        if (this.exits.Count > 0)
	        {
	            foreach (string exitDirection in this.exits)
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

	    private String GetCoordinates()
	    {
	        for (int y = 0; y < Level.Rooms.GetLength(1); y++)
	        {
	            for (int x = 0; x < Level.Rooms.GetLength(0); x++)
	            {
	                if (this == Level.Rooms[x, y])
	                    return "[" + x.ToString() + "," + y.ToString() + "]";
	            }
	        } return "This room is not within the Rooms grid";
	    }
}

