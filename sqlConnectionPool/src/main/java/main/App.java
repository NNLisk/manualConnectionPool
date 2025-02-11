package main;

import java.util.Scanner;
import java.sql.Connection;

/* ASSUMPTIONS: 
* 
* There is only one table of information FOR NOW
*/

public class App {
  public static void main(String[] args) throws ClassNotFoundException {

    ConnectionPool cp = ConnectionPool.makeConnPool(
      "YourjdcbURL", 
      "postgres", "YourPassWord", 
      5);

    Connection test = cp.getConnection();

    if (test != null) {
      System.out.println("Connections made successfully!");
      cp.returnConnection(test);
    } else {
      System.out.println("Creating connections failed.");
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("What would you like to do?");
      System.out.println("1) Get a list of games\n0) close program");

      int choice = Integer.parseInt(scanner.nextLine());

      switch (choice) {
        case 1:
          break;
        case 0:
          scanner.close();
          System.exit(0);
        default:
          System.out.println("Invalid choice");
        break;
      }
    }
  }
}
