package eapli.ecafeteria.domain.booking;

/**
 *
 * @author Rúben - 1160998
 */
public class Rating {
    private int score;
    private String comment;

    public Rating() {
    }

    public Rating(int score, String comment) {
        this.score = score;
        this.comment = comment;
    }
}
