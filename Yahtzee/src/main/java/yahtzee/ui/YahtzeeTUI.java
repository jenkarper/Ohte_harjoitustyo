package yahtzee.ui;

import java.util.ArrayList;
import java.util.Scanner;
import yahtzee.domain.Die;
import yahtzee.domain.Roll;

/**
 *
 * @author pertjenn
 */
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
        System.out.println("Haluatko pitää osan nopista? Y/N");
        System.out.print("Valitse: ");
        String choice = scanner.nextLine();
        System.out.println("");
        if (choice.equalsIgnoreCase("Y")) {
            holdDice();
            System.out.print("Heittosi: ");
            System.out.println(roll.toString());
            System.out.println("");
        } else if (choice.equalsIgnoreCase("N")) {
            return;
        } else {
            System.out.println("Virheellinen syöte! Valitse Y tai N.");
            System.out.print("Valitse: ");
            choice = scanner.nextLine();
            System.out.println("");
        }
    }
    
    public void holdDice() {
        System.out.println("Syötä kaikki silmäluvut, jotka haluat pitää. Syötä kokonaislukuja pilkuin eroteltuna, esim. 5,6,6");
        System.out.println("");
        String diceToHold = scanner.nextLine();
        while (true) {
            if (validateDiceInput(diceToHold)) {
                roll.holdDice(convertInputToInts(diceToHold));
                break;
            } else {
                System.out.println("Virheellinen syöte! Voit syöttää enintään viisi numeroa väliltä 1-6.");
                System.out.print("Valitse: ");
                diceToHold = scanner.nextLine();
            }
        }
    }
    
    public boolean validateDiceInput(String input) {
        String regex = "([1-6],( )?){0,4}[1-6]";
        if (input.matches(regex)) {
            return true;
        }
        return false;
    }
    
    public ArrayList<Integer> convertInputToInts(String input) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] values = input.split(",");
        for (int i = 0; i < values.length; i++) {
            int value = Integer.parseInt(values[i].trim());
            list.add(value);
        }
        
        return list;
    }
}
