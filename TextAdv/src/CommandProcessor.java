
public class CommandProcessor {
	public static void ProcessCommand(string line)
    {
        string command = TextUtils.ExtractCommand(line.Trim()).Trim().ToLower();
        string arguments = TextUtils.ExtractArgument(line.Trim()).Trim().ToLower();

        if (Direction.IsValidDirection(command))
        {
            Player.Move(command);
        }
        else
        {

            switch (command)
            {
                case "exit":
                    Program.quit = true;
                    return;
                case "help":
                    ShowHelp();
                    break;
                case "move":
                    Player.Move(arguments);
                    break;
                case "look":
                    Player.GetCurrentRoom().Describe();
                    break;
                case "pickup":
                    Player.PickupItem(arguments);
                    break;
                case "drop":
                    Player.DropItem(arguments);
                    break;
                case "inventory":
                    Player.DisplayInventory();
                    break;
                case "whereami":
                    Player.GetCurrentRoom().ShowTitle();
                    break;
                default:
                    EasterEggs.inputEggs(command);
                    break;
            }
        }
        GameManager.ApplyRules();
        TextBuffer.Display();
    }

    public static void ShowHelp()
    {
        TextBuffer.Add("Availabe Commands:");
        TextBuffer.Add("------------------");
        TextBuffer.Add("help");
        TextBuffer.Add("exit");
        TextBuffer.Add("move[north, south, east, west]");
        TextBuffer.Add("look");
        TextBuffer.Add("pickup");
        TextBuffer.Add("drop");
        TextBuffer.Add("inventory");
        TextBuffer.Add("wheremai");
    }
}
}
