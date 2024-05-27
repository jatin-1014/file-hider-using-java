package Dao;

import Model.User;
import db.myconnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static boolean isExist(String email) throws SQLException {
        Connection connection = myconnections.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String e = rs.getString(1);
            if(e.equals(email))
                return true;

        }
         return false ;
    }
    public static int saveUsers( User user)throws SQLException{
        Connection connection = myconnections.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into user values(default,?,?)");
        ps.setString(2, user.getEmail());
        return ps.executeUpdate();

    }
}
