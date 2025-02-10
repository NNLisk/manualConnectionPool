package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



public class ConnectionPool {
    /* this is the singleton instance of the pool
     * no other variable or method should be static
     * in this class
     */
    private static ConnectionPool currentPool;

    private ArrayList<Connection> connPool = new ArrayList<Connection>();
    private int poolSize;

    /* private constructor for the Connection pool */

    private ConnectionPool(String url, String username, String password, int poolSize) {
        this.poolSize = poolSize;

        for (int i = 0; i < this.poolSize; i++) {
            connPool.add(createConnections(url, username, password));   
        }
    }
    
    /* making a new singleton instance of the connection pool.
     * This ensures there is only one connection pool and the 
     * constructor is private this way.
     */ 
    public ConnectionPool makeConnPool(String url, String userName, String password, int poolSize) {
        if (currentPool == null) {
            currentPool = new ConnectionPool(url, userName, password, poolSize);
        }
        return currentPool;
    }

    /* Actually makes the connections to the pool */
    private Connection createConnections(String url, String name, String password) {
        try {
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException("Connection to database failed.", e);
        }
    }
    

    /* these two methods handle the list of connections;
     * if connection pool is empty: it waits until there is a free one
     * if its full it removes it from the list and returns to caller.
     * once the caller is done the connection will be freed and returned
     */ 

    public synchronized Connection getConnection() {
        while (connPool.isEmpty()) {
            try { wait();} catch (InterruptedException Ignored) {}
        }
        return connPool.remove(0);
    }

    public synchronized void returnConnection(Connection conn) {
        connPool.add(conn);
        notify();
    }
}
