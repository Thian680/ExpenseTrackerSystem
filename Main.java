
import java.util.ArrayList;
import java.util.Scanner;

class Expense {

    private String name;
    private double price;

    public Expense(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    
    public String getName() {
        return name;
    }

    public String toString() {
        return name + " - " + price;
    }
}

class ExpenseTracker {

    private ArrayList<Expense> expenses = new ArrayList<>();
    private double total = 0;

    public void addExpense(String name, double price) {
        expenses.add(new Expense(name, price));
        total += price;
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("\nNothing here yet!!\n");
        } else {
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println(i + ". " + expenses.get(i));
            }
            System.out.println("Total Expense: " + total + "\n");
        }
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            total -= expenses.get(index).getPrice();
            expenses.remove(index);
            System.out.println("Removed successfully.\n");
        } else {
            System.out.println("Invalid index.\n");
        }
    }

    public boolean isEmpty() {
        return expenses.isEmpty();
    }

    public int size() {
        return expenses.size();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        
        double total = ExpenseIO.load(tracker.getExpenses());
        tracker.setTotal(total);
        

        while (true) {
            System.out.println("Expense Tracker");
            System.out.println("\n1. View Expenses\n2. Add Expense\n3. Remove Expense\n4. Exit");

            System.out.print("\nEnter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    tracker.viewExpenses();
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    double price;
                    try {
                        System.out.print("Enter Price: ");
                        price = sc.nextDouble();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                        sc.nextLine();
                        break;
                    }

                    tracker.addExpense(name, price);
                    ExpenseIO.save(tracker.getExpenses());
                    System.out.println("Successfully added!\n");
                    break;

                case 3:
                    tracker.viewExpenses();
                    if (tracker.isEmpty()) {
                        break;
                    }

                    int index;
                    try {
                        System.out.print("Enter index to remove: ");
                        index = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input!\n");
                        sc.nextLine();
                        break;
                    }

                    tracker.removeExpense(index);
                    ExpenseIO.save(tracker.getExpenses());
                    break;

                case 4:
                    System.out.println("Goodbye!!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Input\n");
            }
        }
    }
}
