package yahtzee.ui;

import java.util.Scanner;
import yahtzee.domain.Roll;

public class YahtzeeTUI {

    private Scanner scanner;
    private Roll roll;

    public YahtzeeTUI(Scanner scanner) {
        this.scanner = scanner;
        this.roll = new Roll();
    }

    public void start() {
        while (true) {
            System.out.println("[1] Heitä noppaa");
            System.out.println("[2] Lopeta");
            System.out.print("Valitse: ");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                play();
            } else if (input.equals("2")) {
                System.out.println("Hei hei!");
                break;
            } else {
                System.out.println("Virheellinen syöte! Valitse 1 tai 2.");
            }
        }
    }
    
    public void play() {
        roll.roll();
        System.out.println("");
        System.out.println("Heitit " + roll.toString());
        System.out.println("");
    }
}
