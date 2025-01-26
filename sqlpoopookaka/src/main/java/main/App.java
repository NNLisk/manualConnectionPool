package main;

import java.util.Scanner;

/* ASSUMPTIONS: 
 * 
 * There is only one table of information FOR NOW
 */

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        DatabaseUtil.connection();

        while (true) {

            System.out.println("What would you like to do?");
            System.out.println("1) find table data 0) close program");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    DatabaseUtil.getTabledata();
                    System.out.println("Which columns of data would you like to get");

                    break;

                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        /* CLOSE THE CONNECTION FOR NEXT COMMIT */
    }
}
