package Service;

import Dao.UserDao;
import Model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer  saveUser(User user){
        try{
            if(UserDao.isExist(user.getEmail())){
                return 0 ;
            }else{
                return UserDao.saveUsers(user);

            }
        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return null;
    }
}
