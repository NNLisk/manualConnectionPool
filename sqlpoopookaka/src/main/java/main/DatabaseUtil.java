package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.core.SqlCommand;

public class DatabaseUtil {
    private static Connection connection;

    public static void connection() throws ClassNotFoundException {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "Lisko";
        String password = "masterchief";

        Class.forName("org.postgresql.Driver");

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection Success");
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
            System.exit(0);
        }
    }

    /*
     * method for fetching table data
     * 
     * Right now getTableData handles also printing,
     * if data were to be used for other purposes,
     * take the print statements out.
     */
    public static void getTabledata() {

        if (connection == null) {
            System.out.println("No connection.");
            return;
        }

        String query = "SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'public'";
        Statement statement;
        ResultSet results;
        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while (results.next()) {
                String tableName = results.getString("TABLE_NAME");
                String columnName = results.getString("COLUMN_NAME");

                System.out.println("Table name: " + tableName + ", Column name: " + columnName);
            }

        } catch (SQLException e) {
            System.out.println("Statement failed " + e.getMessage());
            return;
        }
    }

    /* Closes connection if there is one */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close Database Connection." + e.getMessage());
                return;
            }
        }
    }
}
