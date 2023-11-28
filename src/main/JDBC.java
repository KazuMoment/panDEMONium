package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    public void toDatabase(String query){
        String url = "jdbc:mysql://localhost:3306/panDEMONium";
        String username = "root";
        String password = "";

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
}
