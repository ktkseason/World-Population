package main;

import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    private Check check = new Check();
    private OperationFactory op = new OperationFactory();

    Menu() {
        displayMenu();
    }

    private int menu() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                          Menu                          ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Inserting new population data");
        System.out.println("2. Updating population data");
        System.out.println("3. Re-ranking the world rank");
        System.out.println("4. Reporting");
        System.out.println("5. Exit");
        System.out.println("========================================================");

        String option;
        boolean optionStatus = false;
        do {
            System.out.print("Enter menu number between 1 and 5: ");
            option = sc.nextLine();
        } while(!check.checkMenuOption(option));
        return Integer.parseInt(option);
    }

    private void displayMenu() {
        switch(menu()) {
            case 1: op.getOperation(1); break;
            case 2: op.getOperation(2); break;
            case 3: op.getOperation(3); break;
            case 4: op.getOperation(4); break;
            case 5: op.getOperation(5);
        }
        displayMenu();
    }

    public static void main(String[] args) {
        new Menu();
    }
}
