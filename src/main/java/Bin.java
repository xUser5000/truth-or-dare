import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Bin {

    public static void main(String[] args) throws IOException {

        // Welcome Message
        Welcome.welcome();

        Scanner scanner = new Scanner(System.in);
        while (true) {

            // Prompt >>
            System.out.print("Enter a compound proposition >> ");
            String statement = scanner.nextLine().trim();
            System.out.println("");

            // Re-prompt the user when inserting an empty string
            if (statement.equals("")) continue;

            // Stop the program when typing "exit"
            if (statement.equals("exit")) break;

            try {

                // Generate the truth table for the logical expression
                List< List<String> > truthTable = Application.generateTruthTable(statement);

                // Print the table
                TablePrinter.print(truthTable);

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("");
        }
    }
}
