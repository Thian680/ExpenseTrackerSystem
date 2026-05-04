
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
            System.out.println("\n- Nothing here yet!!\n");
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
            System.out.println("\n- Removed successfully.\n");
        } else {
            System.out.println("\n- Invalid index.\n");
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
            System.out.println("\n[--Expense Tracker--]");
            System.out.println("\n1. View Expenses\n2. Add Expense\n3. Remove Expense\n4. Exit");

            int choiceNum;
            
            try {
                System.out.print("\nEnter Choice: ");
                choiceNum = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid Choice! Please select from 1-4.");
                sc.nextLine();
                continue;
            }
            
    
            switch (choiceNum) {
                case 1:
                    System.out.println("\n(Press Enter to go back)");
                    tracker.viewExpenses();
                    sc.nextLine();
                    break;

                case 2:
                    String addAgain;
                    do {
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        double price;
                        try {
                            System.out.print("Enter Price: ");
                            price = sc.nextDouble();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("\n- Invalid input!\n");
                            sc.nextLine();
                            break;
                        }

                        tracker.addExpense(name, price);
                        ExpenseIO.save(tracker.getExpenses());
                        System.out.println("\n- Successfully added!\n");

                        System.out.print("Add another expense? (Y/N): ");
                        addAgain = sc.nextLine();

                    } while (addAgain.equalsIgnoreCase("Y"));
                    break;

                case 3:
                    String removeAgain;
                    do {
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
                            System.out.println("\n- Invalid input!\n");
                            sc.nextLine();
                            break;
                        }

                        tracker.removeExpense(index);
                        ExpenseIO.save(tracker.getExpenses());

                        System.out.print("Remove another expense? (Y/N): ");
                        removeAgain = sc.nextLine();

                    } while (removeAgain.equalsIgnoreCase("Y"));
                    break;

                case 4:
                    System.out.println("- Goodbye!!");
                    System.out.println("[-------------------]");
                    sc.close();
                    return;

                default:
                    System.out.println("\n- Invalid Input\n");
            }
        }
    }
}
