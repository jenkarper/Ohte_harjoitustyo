package yahtzee.domain;

import java.util.ArrayList;

/**
 *
 * @author pertjenn
 */
public class Roll {

    private Die[] dice;
    private int[] values;

    public Roll() {
        this.dice = new Die[5];
        this.values = new int[5];

        for (int i = 0; i < 5; i++) {
            this.dice[i] = new Die();
        }
    }

    public void roll() {
        for (int i = 0; i < 5; i++) {
            Die d = this.dice[i];
            if (!d.isHold()) {
                d.roll();
                this.values[i] = d.getValue();
            }
        }
    }
    
    public boolean holdDice(ArrayList<Integer> values) {
        releaseAll();
        int index = 0;
        
        while (index < values.size()) {
            int v = values.get(index);

            if ((v == dice[0].getValue()) && (!dice[0].isHold())) {
                dice[0].setHold(true);
                index++;
                
            } else if ((v == dice[1].getValue()) && (!dice[1].isHold())) {
                dice[1].setHold(true);
                index++;
                
            } else if ((v == dice[2].getValue()) && (!dice[2].isHold())) {
                dice[2].setHold(true);
                index++;
                
            } else if ((v == dice[3].getValue()) && (!dice[3].isHold())) {
                dice[3].setHold(true);
                index++;
                
            } else if ((v == dice[4].getValue()) && (!dice[4].isHold())) {
                dice[4].setHold(true);
                index++;
                
            } else {
                return false;
            }
        }
        return true;
    }
    
    public Die[] getDice() {
        return this.dice;
    }
    
    public int[] getValues() {
        return this.values;
    }
    
    public void releaseAll() {
        for (Die d : this.dice) {
            d.setHold(false);
        }
    }

    @Override
    public String toString() {
        StringBuilder rolled = new StringBuilder();
        StringBuilder held = new StringBuilder();

        for (Die d : this.dice) {
            if (d.isHold()) {
                held.append(d.getValue());
                held.append(" ");
            } else {
                rolled.append(d.getValue());
                rolled.append(" ");
            }
        }

        if (held.toString().length() == 0) {
            return rolled.toString();
        } else {
            held.append("| ");
            held.append(rolled.toString());
            return held.toString();
        }
    }
}
