

public class Program {
	public static Boolean quit = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameManager.ShowTitleScreen();
        Level.Initialize();
        GameManager.StartGame();

        while (!quit)
        {
            CommandProcessor.ProcessCommand(Console.ReadLine());
        }
	}

}
