package yahtzee.domain;

/**
 * Represents the scorecard in a Yahtzee game.
 *
 * @author pertjenn
 */
public class Scorecard {

    private final int[] points;
    private final String[] categories;

    public Scorecard() {
        this.points = new int[19];
        this.categories = new String[19];
        initialiseSlotNames();

        for (int i = 1; i <= 18; i++) {
            this.points[i] = -1;
        }
    }

    private boolean slotAvailable(int i) {
        return this.points[i] == -1;
    }

    /**
     * Marks the given points for the given category in the points array.
     *
     * @param i The index of the category.
     * @param points The points returned by the checker method.
     */
    public void markScore(int i, int points) {
        if (slotAvailable(i)) {
            this.points[i] = points;
        }
    }

    /**
     * Calculates the total of categories 1 through 6 and marks the result in
     * the points array.
     *
     * @return The upper section total.
     */
    public int getUpperTotal() {
        int total = 0;
        for (int i = 1; i < 7; i++) {
            total += points[i];
        }
        markScore(16, total);
        return total;
    }

    /**
     * Checks the upper section total (categories 1 through 6) and awards bonus
     * points accordingly.
     *
     * @return The bonus points (50 or 0).
     */
    public int getBonus() {
        int bonus = 0;
        if (points[16] >= 63) {
            bonus = 50;
        } else {
            bonus = 0;
        }
        points[17] = bonus;
        return bonus;
    }

    /**
     * Calculates the lower section total (categories 7 through 15), adds upper
     * section total and bonus and marks the result in the points array.
     *
     * @return The grand total.
     */
    public int getGrandTotal() {
        int total = getUpperTotal();
        total += getBonus();

        for (int i = 7; i < 16; i++) {
            total += points[i];
        }

        points[18] = total;
        return total;
    }

    /**
     * Resets the points array to -1.
     */
    public void reset() {
        for (int i = 1; i <= 18; i++) {
            this.points[i] = -1;
        }
    }

    public int[] getPoints() {
        return this.points;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public int getScore(int i) {
        return this.points[i];
    }

    private void initialiseSlotNames() {
        this.categories[1] = "Ykköset";
        this.categories[2] = "Kakkoset";
        this.categories[3] = "Kolmoset";
        this.categories[4] = "Neloset";
        this.categories[5] = "Viitoset";
        this.categories[6] = "Kuutoset";
        this.categories[7] = "Pari";
        this.categories[8] = "Kaksi paria";
        this.categories[9] = "Kolmiluku";
        this.categories[10] = "Neliluku";
        this.categories[11] = "Pieni suora";
        this.categories[12] = "Iso suora";
        this.categories[13] = "Mökki";
        this.categories[14] = "Sattuma";
        this.categories[15] = "Jatsi";
        this.categories[16] = "Välisumma";
        this.categories[17] = "Bonus";
        this.categories[18] = "Yhteensä";
    }

    /**
     * Sets points from 1 to 18 in the points array (this method is only called
     * in the ScorecardTest class).
     */
    public void setTestValues() {
        for (int i = 1; i < this.points.length; i++) {
            this.points[i] = i;
        }
    }

}
