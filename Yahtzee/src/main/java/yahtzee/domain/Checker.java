package yahtzee.domain;

import java.util.Arrays;

/**
 *
 * @author pertjenn
 */
public class Checker {

    public int duplicates(int[] dice, int i) {
        int sum = 0;
        for (Integer value : dice) {
            if (value == i) {
                sum += value;
            }
        }
        return sum;
    }

    public int pair(int[] dice) {
        Arrays.sort(dice);
        int sum = 0;
        int max = 0;
        for (int i = 1; i < 5; i++) {
            if (dice[i] == dice[i - 1]) {
                sum = 2 * dice[i];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    public int twoPairs(int[] dice) {
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

    public int threeKind(int[] dice) {
        Arrays.sort(dice);

        if (dice[0] == dice[2]) {
            return 3 * dice[0];

        } else if (dice[1] == dice[3]) {
            return 3 * dice[1];

        } else if (dice[2] == dice[4]) {
            return 3 * dice[2];

        } else {
            return 0;
        }
    }

    public int fourKind(int[] dice) {
        Arrays.sort(dice);

        if ((dice[0] == dice[3])
                || (dice[1] == dice[4])) {
            return 4 * dice[1];
        }
        return 0;
    }

    public int smallStraight(int[] dice) {
        Arrays.sort(dice);

        if (straight(dice)) {
            if (dice[0] == 1) {
                return 15;
            }
        }
        return 0;
    }

    public int largeStraight(int[] dice) {
        Arrays.sort(dice);

        if (straight(dice)) {
            if (dice[0] == 2) {
                return 20;
            }
        }
        return 0;
    }

    public int fullHouse(int[] dice) {
        Arrays.sort(dice);

        if ((dice[0] == dice[2]) && (dice[3] == dice[4])
                || (dice[0] == dice[1]) && (dice[2] == dice[4])) {
            return sum(dice);
        }
        return 0;
    }
    
    public int yahtzee(int[] dice) {
        Arrays.sort(dice);
        
        if (dice[0] == dice[4]) {
            return sum(dice) + 50;
        }
        return 0;
    }
    
    // HELPER METHODS

    public boolean straight(int[] dice) {
        Arrays.sort(dice);

        Boolean straight = true;
        for (int i = 1; i < 5; i++) {
            if (dice[i] != dice[i - 1] + 1) {
                straight = false;
            }
        }
        return straight;
    }

    public int sum(int[] dice) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += dice[i];
        }
        return sum;
    }

}
