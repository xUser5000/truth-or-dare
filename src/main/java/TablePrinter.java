import java.util.List;

public class TablePrinter {
    public static void print (List< List<String> > table) {

        // Print the head row
        for (String item: table.get(0)) {
            System.out.format("%-15s", item);
        }
        System.out.println("");

        // The line after the head row
        int n = table.get(0).size() * 15;
        for (int i = 0; i < n; i++) System.out.print("=");
        System.out.println("");

        // The rest of the table
        for (int i = 1; i < table.size(); i++) {
            List<String> list = table.get(i);
            for (String value: list) {
                System.out.format("%-15s", value);
            }
            System.out.println("");
        }
    }
}
