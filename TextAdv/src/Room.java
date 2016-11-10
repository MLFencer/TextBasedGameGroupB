
public class Room {
	private String title;
    private String description;
    private boolean isOffWorld = false;

    private List<string> exits;
    private List<Item> items;

    #region properties

    public string Title
    {
        get { return title; }
        set { title = value; }
    }

    public bool IsOffWorld
    {
        get { return isOffWorld; }
        set { isOffWorld = value; }
    }

    public string Description
    {
        get { return description; }
        set { description = value; }
    }

    public List<Item> Items
    {
        get { return items; }
        set { items = value; }
    }

    #endregion

    public Room()
    {
        exits = new List<string>();
        items = new List<Item>();
    }

    #region public methods

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

    public Item GetItem(string itemName)
    {
        foreach (Item item in this.items)
        {
            if (item.Title.ToLower() == itemName.ToLower())
                return item;
        }
        return null;
    }

    public void AddExit(string directon)
    {
        if (this.exits.IndexOf(directon) == -1)
            this.exits.Add(directon);

    }

    public void RemoveExit(string direction)
    {
        if (this.exits.IndexOf(direction) != -1)
            this.exits.Remove(direction);
    }

    public bool CanExit(string direction)
    {
        foreach (string validExit in this.exits)
        {
            if (direction == validExit)
                return true;
        }
        return false;
    }

    #endregion

    #region private methods

    private string GetItemList()
    {
        string itemString = "";
        string message = "Items in Rooms:";
        string underline = "";
        underline = underline.PadLeft(message.Length, '-');

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

    private string GetExitList()
    {
        string exitString = "";
        string message = "Possible Directions: ";
        string underline = "";
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

    private string GetCoordinates()
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
