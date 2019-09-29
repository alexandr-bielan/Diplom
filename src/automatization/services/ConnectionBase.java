package automatization.services;

import java.net.ConnectException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;


public class ConnectionBase {

    public  static Connection c = null;
    public   static Statement stmt = null;

    public void createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
    }


    public void closeConnection(String sql) {
        try {
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        }
        catch (SQLException t) {
            System.err.println(t.getClass().getName()+": "+t.getMessage());

            System.exit(0);
        }
    }
}
