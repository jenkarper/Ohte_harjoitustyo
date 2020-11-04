package yahtzee.main;

import java.util.Scanner;
import yahtzee.domain.Die;
import yahtzee.domain.Roll;
import yahtzee.ui.YahtzeeTUI;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        YahtzeeTUI tui = new YahtzeeTUI(scanner);
        tui.start();
    }
    
}
