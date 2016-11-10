
public class EasterEggs {
	public static void inputEggs(String input)
    {
        if (input.toLowerCase() == "")
            TextBuffer.Add("Type something.");
        else if (input.toLowerCase() == "pikachu")
            TextBuffer.Add("Unfortunately, pikachu was one of the rarer pokemon.");
        else if (input.toLowerCase() == "lol")
            TextBuffer.Add("What is so funny?");
        else if (input.toLowerCase() == "pokeball")
            TextBuffer.Add("Unfortunately, this is not Pokemon GO.");
        else if (input.toLowerCase() == "lmao" )
            TextBuffer.Add(TextUtils.WordWrap("I hardly believe the fact that it's possible to laugh oneself buttocks entirely off their person."));
        else TextBuffer.Add("That is not a valid command.");
    }
}
