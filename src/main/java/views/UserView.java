package views;

import Dao.DataDao;
import Model.data;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Dao.DataDao.*;

public class UserView {
    private String email;
    UserView(String email){
        this.email=email;
    }
    public void home () throws SQLException, IOException {
        do {
            System.out.println("welcome" + this.email);
            System.out.println("press 1 to show already hidden file");
            System.out.println("press 2 to add a new file");
            System.out.println("press 3 to unhide a file ");
            System.out.println("press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch){
                case 1 ->{
                try {
                    List<data> files = getAllFiles(this.email);
                    System.out.println("ID = fileName");
                    for(data file: files){
                        System.out.println(file.getId()+"-"+file.getFileName());
                    }
                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
            case 2 ->{
                System.out.println("enter the file path");
                String path = sc.nextLine();
                File f = new File(path);
                data file = new data(0,f.getName(), path,this.email);
                try {
                    DataDao.hideFile(file);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 3->{
                List<data> files = null;
                files = getAllFiles(this.email);
                System.out.println("ID = fileName");
                for(data file: files){
                    System.out.println(file.getId()+"-" + file.getFileName());
                }
                System.out.println("enter the id of file to unhide ");
                int id = Integer.parseInt(sc.nextLine());
                boolean isValidId = false;
                for(data file : files){
                    if(file.getId()== id){
                        isValidId= true;
                        break;
                    }
                }
                if(isValidId){
                    DataDao.unhide(id);
                }else{
                    System.out.println("wrong id");
                }


            }
            case 0 ->{
                    System.exit(0);

            }

            }
        }while(true);
    }
}
