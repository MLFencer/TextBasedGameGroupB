package Model;

public class Level {
	private static Room[][] rooms;

    public static void Initialize()
    {
        BuildLevel();
    }

    private static void BuildLevel()
    {
        rooms = new Room[2][2];

        Room room;
        Item item;

        //////////////////////////////////////////////////////////////////////////
        // create the RED ROOM
        room = new Room();

        // Assign this room to location 0, 0
        rooms[0][0] = room;

        // Setup the room
        room.setTitle("Red Room");
        room.setDescription("You have entered the red room. There is a locked door to the south.");
        room.AddExit(Direction.East);

        // create a new item
        item = new Item();

        // setup the item
        item.setTitle("Blu Ball");
        item.setPickupText("You just picked up the blu ball.");

        // add item to the current room
        room.items.add(item);

        //////////////////////////////////////////////////////////////////////////
        // create the BLU ROOM
        room = new Room();

        // Assign this room to location 1, 0
        rooms[1][0] = room;

        // Setup the room
        room.setTitle("Blu Room");
        room.setDescription("You have entered the blu room.");
        room.AddExit(Direction.West);
        room.AddExit(Direction.South);

        // create a new item
        item = new Item();

        // setup the item
        item.setTitle("Anvil");
        item.setPickupText("You struggle to pickup the anvil.");
        item.setWeight(6);

        // add item to the current room
        room.items.add(item);

        // create the GREEN BALL
        item = new Item();

        // setup the item
        item.setTitle("Green Ball");
        item.setPickupText("You have just picked up the green ball.");

        // add item to the current room
        room.items.add(item);

        // create the KEY
        item = new Item();

        // setup the item
        item.setTitle("Key");
        item.setPickupText("You have just picked up the key.");

        // add item to the current room
        room.items.add(item);



        /////////////////////////////////////////////////////////////////
        // create the YELLOW ROOM
        room = new Room();

        // Assign this room to location 1, 1
        rooms[1][1] = room;

        // Setup the room
        room.setTitle("Yellow Room");
        room.setDescription("You have entered the yellow room.");
        room.AddExit(Direction.West);
        room.AddExit(Direction.North);

        // create a new item
        item = new Item();

        // setup the item
        item.setTitle("Red Ball");
        item.setPickupText("You just picked up the red ball.");

        // add item to the current room
        room.items.add(item);

        //////////////////////////////////////////////////////////////////////
        // create the GREEN ROOM
        room = new Room();

        // Assign this room to location 0, 1
        rooms[0][1] = room;

        // Setup the room
        room.setTitle("Green Room");
        room.setDescription("You have entered the green room. There is a locked door the north.");
        room.AddExit(Direction.East);

        // create a new item
        item = new Item();

        // setup the item
        item.setTitle("Yellow Ball");
        item.setPickupText("You just picked up the yellow ball.");

        // add item to the current room
        room.items.add(item);

        // place the player in the starting room
        Player.posX = 0;
        Player.posY = 0;
}
}
