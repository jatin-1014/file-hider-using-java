package views;

import Dao.UserDao;
import Model.User;
import Service.GenerateOTP;
import Service.SendOTPService;
import Service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import static Service.UserService.*;

public class welcome {
    public void welcomePanel() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("welcome to the protected world");
        System.out.println("press 1 to login");
        System.out.println("press 2 to signup");
        System.out.println("press 0 to exit");
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(IOException ex ) {
            ex.printStackTrace();
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);

        }


    }
    private void login() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter email");
        String email = sc.nextLine();
        try{
            if(UserDao.isExist(email)){
                String genOTP= GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("enter the otp");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                   new UserView(email).home();

                }
                else{
                    System.out.println("wrong otp");
                }
            }else{
                System.out.println("user not found");
            }
        }catch (SQLException ex){
             ex.printStackTrace();
        }

    }
    private void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter name");
        String name = sc.nextLine();
        System.out.println("enter email");
        String email = sc.nextLine();
        String genOTP= GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("enter the otp");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            User user = new User(name,email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User registered");
                case 1 -> System.out.println("User existed");
            }
        }
        else{
            System.out.println("wrong otp");
        }




    }
}
