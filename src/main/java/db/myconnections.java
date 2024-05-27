package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myconnections {
    private  static Connection connection;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hider?useSSL=false", "root", "Raghu999");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connection done");
        return connection;
    }
    public static void closeConnection(){
        if(connection!= null){
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

 public static void main(String[] args) {myconnections.getConnection();
   }
}

