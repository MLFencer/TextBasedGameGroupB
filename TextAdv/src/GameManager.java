
public class GameManager {
	 public static void ShowTitleScreen()
     {
         Console.Clear();
         Console.WriteLine();
         Console.WriteLine(TextUtils.WordWrap("*** The Hyperion Project *** An XNA Xtreme 101 game by 3D Buzz.\n\n",
                                             Console.WindowWidth));
         Console.WriteLine("\nNote: You may type 'help' at any time to see a list of commands");
         Console.WriteLine("\nPress a key to begin");

         Console.CursorVisible = false;
         Console.ReadKey();
         Console.CursorVisible = true;
         Console.Clear();
     }

     public static void StartGame()
     {
         Player.GetCurrentRoom().Describe();
         TextBuffer.Display();
     }

     public static void EndGame(string endingText)
     {
         Program.quit = true;

         Console.Clear();

         Console.WriteLine(TextUtils.WordWrap(endingText, Console.WindowWidth));
         Console.Write("\nYou may now close this window.");
         Console.CursorVisible = false;

         while (true)
         {
             Console.ReadKey(true);
         }
     }

     public static void ApplyRules()
     {

     }
}
