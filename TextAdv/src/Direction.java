
public class Direction {
	public final static String North = "north";
    public final static String South = "south";
    public final static String East = "east";
    public final static String West = "west";

    public static boolean IsValidDirection(String direction)
    {
        switch (direction)
        {
            case Direction.North:
                return true;
            case Direction.South:
                return true;
            case Direction.West:
                return true;
            case Direction.East:
                return true;
        }
        return false;
    }
}
