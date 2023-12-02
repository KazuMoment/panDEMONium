package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
    String url = "jdbc:sqlite:mydatabase.db";
    public Connection connection = null;
    public Statement statement = null;
    public int SessionID = getLastSessionID();
    
    public void createDatabase() throws ClassNotFoundException {
        try {
            // Register JDBC driver (not needed for SQLite)
            Class.forName("org.sqlite.JDBC");

            // Open a connection to the SQLite database (creates the file if it doesn't exist)
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(url);

            // Execute a query to create a table
            System.out.println("Creating table...");
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS playerrecords (SessionID INT(5) NOT NULL , Level INT(5) NULL , MaxHP"+
                        " INT(5) NULL , HP INT(5) NULL , MaxMana INT(5) NULL , Mana INT(5) NULL , Strength"+
                        " INT(5) NULL , Dexterity INT(5) NULL , Gold INT(10) NULL , Weapon VARCHAR(20) NULL ,"+ 
                        " Shield VARCHAR(20) NULL , Playtime TIME NULL , PRIMARY KEY (SessionID))";
            statement.executeUpdate(sql);
            System.out.println("Table created successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void toDatabase(String query) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            Class.forName("org.sqlite.JDBC");
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    public int getLastSessionID() {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(SessionID) FROM playerrecords")) {
            Class.forName("org.sqlite.JDBC");
    
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public String getValue(int columnIndex, int sessionID) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM playerrecords WHERE sessionID = " + sessionID)) {
            Class.forName("org.sqlite.JDBC");
    
            if (resultSet.next()) {
                return resultSet.getString(columnIndex);
            } else {
                return "null";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    
}
