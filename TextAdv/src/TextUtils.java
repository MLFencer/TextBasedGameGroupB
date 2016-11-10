
public class TextUtils {
	public static String ExtractCommand(String line)
    {
        int index = line.indexOf(' ');
        if (index == -1)
            return line;
        else
            return line.substring(0, index);
    }

    public static String ExtractArgument(String line)
    {
        int index = line.indexOf(' ');
        if (index == -1)
            return "";
        else
            return line.substring(index + 1, line.length() - index - 1);
    }

    public static String WordWrap(String text)
    {
    	int bufferWidth = 100;
        String result = "";
        String[] lines = text.split("\n");

		for(String line:lines)
        {
            int linelength = 0;
            String[] words = line.split(" ");

			for (String word:words)
            {
                if (word.length() + linelength >= bufferWidth - 1)
                {
                    result += "\n";
                    linelength = 0;
                }
                result += word + " ";
                linelength += word.length() + 1;
            }
            result += "\n";
        }
        return result;
    }
}
