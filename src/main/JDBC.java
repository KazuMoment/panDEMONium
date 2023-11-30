package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    String url = "jdbc:mysql://localhost:3306/panDEMONium";
    String username = "root";
    String password = "";
    public int SessionID;
    
    public void toDatabase(String query){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();

            statement.execute(query);
           /* 
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
            }*/
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public int getLastSessionID(){
        String url = "jdbc:mysql://localhost:3306/panDEMONium";
        String username = "root";
        String password = "";
        int i;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select max(SessionID) from playerdata");

            if (resultSet.next()) {
                i = resultSet.getInt(1);
                return i;
            }
            else{
                return -1;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    public String getValue(int columnIndex, int sessionID){
        String url = "jdbc:mysql://localhost:3306/panDEMONium";
        String username = "root";
        String password = "";
        String i;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from playerdata where sessionID = " + sessionID);

            if (resultSet.next()) {
                i = resultSet.getString(columnIndex);
                return i;
            }
            else{
                return "null";
            }
        }
        catch(Exception e){
            System.out.println(e);
            return "null";
        }
    }
}
