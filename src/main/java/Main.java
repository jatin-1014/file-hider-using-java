import views.welcome;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        welcome w = new welcome();
        do {
            w.welcomePanel();
        } while (true);

    }
}
