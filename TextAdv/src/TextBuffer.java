
public class TextBuffer {
	private static String outputBuffer;

    public static void Add(String text)
    {
        outputBuffer += text + "\n";
    }

    public static void Display()
    {
        System.out.flush();

        System.out.print(TextUtils.WordWrap(outputBuffer));
        System.out.println("What shall I do?");
        System.out.println(">");

        outputBuffer = "";
    }
}
