package Service;

import java.util.Random;

public class GenerateOTP {
    public static String getOTP(){
        Random random = new Random();
        return String.format("0%4d",random.nextInt(10000) );
    }
}
