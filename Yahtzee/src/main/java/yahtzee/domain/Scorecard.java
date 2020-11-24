package yahtzee.domain;

/**
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

    public int[] getPoints() {
        return this.points;
    }
    
    public String[] getCategories() {
        return this.categories;
    }

    private boolean slotAvailable(int i) {
        return this.points[i] == -1;
    }

    public boolean markScore(int i, int points) {
        if (!slotAvailable(i)) {
            return false;
        }
        this.points[i] = points;
        return true;
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

}
