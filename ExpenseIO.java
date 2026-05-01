
import java.io.*;
import java.util.ArrayList;

class ExpenseIO {

    // savwe file
    public static void save(ArrayList<Expense> list) {
        try {
            FileWriter fw = new FileWriter("expenses.txt");

            for (Expense e : list) {
                fw.write(e.getName() + "," + e.getPrice() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // get file
    public static double load(ArrayList<Expense> list) {
        double total = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("expenses.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                double price = Double.parseDouble(parts[1]);

                list.add(new Expense(name, price));
                total += price;
            }

            br.close();
        } catch (FileNotFoundException e) {
        
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }

        return total;
    }
}
