import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

public class Welcome {

    public static final String PROGRAM_VERSION = "0.1";

    public static void welcome () throws IOException {
        System.out.println(FigletFont.convertOneLine("Truth or Dare"));
        System.out.println("Hi! This is truth or dare version " + PROGRAM_VERSION);
        System.out.println("Type \"exit\" to stop the program\n");
    }
}
