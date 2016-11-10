
public class Item {
	private string title;
    private string pickupText;
    private int weight = 1;

    #region properties

    public int Weight
    {
        get { return weight; }
        set { weight = value; }
    }

    public string PickupText
    {
        get { return pickupText; }
        set { pickupText = value; }
    }

    public string Title
    {
        get { return title; }
        set { title = value; }
    }
}
