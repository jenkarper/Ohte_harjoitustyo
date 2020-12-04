package yahtzee.domain;

import java.util.Arrays;

/**
 * Handles the score checking for rolls.
 * @author pertjenn
 */
public class Checker {
    
    /**
     * Takes the type and the dice and calculates the resulting points.
     * @param type Index number representing a category in the scorecard.
     * @param dice The dice values as an array.
     * @return The points.
     */
    public int check(int type, int[] dice) {
        switch (type) {
            case 1:
                return duplicates(dice, 1);
            case 2:
                return duplicates(dice, 2);
            case 3:
                return duplicates(dice, 3);
            case 4:
                return duplicates(dice, 4);
            case 5:
                return duplicates(dice, 5);
            case 6:
                return duplicates(dice, 6);
            case 7:
                return pair(dice);
            case 8:
                return twoPairs(dice);
            case 9:
                return threeKind(dice);
            case 10:
                return fourKind(dice);
            case 11:
                return smallStraight(dice);
            case 12:
                return largeStraight(dice);
            case 13:
                return fullHouse(dice);
            case 14:
                return sum(dice);
            case 15:
                return yahtzee(dice);
        }
        return 0;
    }

    private int duplicates(int[] dice, int i) {
        int sum = 0;
        for (Integer value : dice) {
            if (value == i) {
                sum += value;
            }
        }
        return sum;
    }

    private int pair(int[] dice) {
        Arrays.sort(dice);
        int sum = 0;
        int max = 0;
        for (int i = 1; i < 5; i++) {
            if (dice[i] == dice[i - 1]) {
                sum = dice[i] + dice[i];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    private int twoPairs(int[] dice) {
        Arrays.sort(dice);
        if ((dice[0] == dice[1]) && (dice[2] == dice[3])) {
            return dice[0] + dice[1] + dice[2] + dice[3];

        } else if ((dice[0] == dice[1]) && (dice[3] == dice[4])) {
            return dice[0] + dice[1] + dice[3] + dice[4];

        } else if ((dice[1] == dice[2]) && (dice[3] == dice[4])) {
            return dice[1] + dice[2] + dice[3] + dice[4];

        } else {
            return 0;
        }
    }

    private int threeKind(int[] dice) {
        Arrays.sort(dice);
        int multiplier = 3;
        if (dice[0] == dice[2]) {
            return multiplier * dice[0];
        } else if (dice[1] == dice[3]) {
            return multiplier * dice[1];
        } else if (dice[2] == dice[4]) {
            return multiplier * dice[2];
        } else {
            return 0;
        }
    }

    private int fourKind(int[] dice) {
        Arrays.sort(dice);
        int multiplier = 4;
        if ((dice[0] == dice[3])
                || (dice[1] == dice[4])) {
            return multiplier * dice[1];
        }
        return 0;
    }

    private int smallStraight(int[] dice) {
        Arrays.sort(dice);
        int points = 15;
        if (straight(dice)) {
            if (dice[0] == 1) {
                return points;
            }
        }
        return 0;
    }

    private int largeStraight(int[] dice) {
        Arrays.sort(dice);
        int points = 20;
        if (straight(dice)) {
            if (dice[0] == 2) {
                return points;
            }
        }
        return 0;
    }

    private int fullHouse(int[] dice) {
        Arrays.sort(dice);
        if ((dice[0] == dice[2]) && (dice[3] == dice[4])
                || (dice[0] == dice[1]) && (dice[2] == dice[4])) {
            return sum(dice);
        }
        return 0;
    }
    
    private int yahtzee(int[] dice) {
        Arrays.sort(dice);
        int bonus = 50;
        if (dice[0] == dice[4]) {
            return sum(dice) + bonus;
        }
        return 0;
    }
    
    // HELPER METHODS

    private boolean straight(int[] dice) {
        Arrays.sort(dice);

        Boolean straight = true;
        for (int i = 1; i < 5; i++) {
            if (dice[i] != dice[i - 1] + 1) {
                straight = false;
            }
        }
        return straight;
    }

    private int sum(int[] dice) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += dice[i];
        }
        return sum;
    }

}
