package yahtzee.ui;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        YahtzeeTUI tui = new YahtzeeTUI(scanner);
        tui.start();
    }
    
}
