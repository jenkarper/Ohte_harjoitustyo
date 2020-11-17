package yahtzee.domain;

import java.util.ArrayList;

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

//    // SCORE CHECK METHODS
//    
//    public int checkDuplicates(int i) {
//        
//        int sum = 0;
//        for (Integer value : this.values) {
//            if (value == i) {
//                sum += value;
//            }
//        }
//        return sum;
//    }
//
//    public int checkOnePair() {
//        Arrays.sort(this.values);
//
//        int sum = 0;
//        int max = 0;
//        for (int i = 1; i < 5; i++) {
//            if (this.values[i] == this.values[i - 1]) {
//                sum = 2 * this.values[i];
//                max = Math.max(sum, max);
//            }
//        }
//        return max;
//    }
//
//    public int checkTwoPairs() {
//        Arrays.sort(this.values);
//
//        if ((this.values[0] == this.values[1]) && (this.values[2] == this.values[3])) {
//            return this.values[0] + this.values[1] + this.values[2] + this.values[3];
//
//        } else if ((this.values[0] == this.values[1]) && (this.values[3] == this.values[4])) {
//            return this.values[0] + this.values[1] + this.values[3] + this.values[4];
//
//        } else if ((this.values[1] == this.values[2]) && (this.values[3] == this.values[4])) {
//            return this.values[1] + this.values[2] + this.values[3] + this.values[4];
//
//        } else {
//            return 0;
//        }
//    }
//
//    public int checkThreeKind() {
//        Arrays.sort(this.values);
//
//        if (this.values[0] == this.values[2]) {
//            return 3 * this.values[0];
//
//        } else if (this.values[1] == this.values[3]) {
//            return 3 * this.values[1];
//
//        } else if (this.values[2] == this.values[4]) {
//            return 3 * this.values[2];
//
//        } else {
//            return 0;
//        }
//    }
//
//    public int checkFourKind() {
//        Arrays.sort(this.values);
//
//        if ((this.values[0] == this.values[3])
//                || (this.values[1] == this.values[4])) {
//            return 4 * this.values[1];
//        }
//        return 0;
//    }
//
//    public int checkSmallStraight() {
//        Arrays.sort(this.values);
//
//        if (checkStraight()) {
//            if (this.values[0] == 1) {
//                return 15;
//            }
//        }
//        return 0;
//    }
//
//    public int checkLargeStraight() {
//        Arrays.sort(this.values);
//
//        if (checkStraight()) {
//            if (this.values[0] == 2) {
//                return 20;
//            }
//        }
//        return 0;
//    }
//
//    public int checkFullHouse() {
//        Arrays.sort(this.values);
//
//        if ((this.values[0] == this.values[2]) && (this.values[3] == this.values[4])
//                || (this.values[0] == this.values[1]) && (this.values[2] == this.values[4])) {
//            return sum();
//        }
//        return 0;
//    }
//
//    public int checkYahtzee() {
//        Arrays.sort(this.values);
//
//        if (this.values[0] == this.values[4]) {
//            return sum() + 50;
//        }
//        return 0;
//    }

    // HELPER METHODS
    
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

//    public int sum() {
//        int sum = 0;
//        for (int i = 0; i < 5; i++) {
//            sum += this.values[i];
//        }
//        return sum;
//    }
//
//    public boolean checkStraight() {
//        Arrays.sort(this.values);
//
//        Boolean straight = true;
//        for (int i = 1; i < 5; i++) {
//            if (this.values[i] != this.values[i - 1] + 1) {
//                straight = false;
//            }
//        }
//        return straight;
//    }
//    
//    public void setValues(int[] testValues) {
//        this.values = testValues;
//    }

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
    
    // TEST METHODS
    
//    public void setTestValues(int[] testValues) {
//        this.values = testValues;
//    }
}
