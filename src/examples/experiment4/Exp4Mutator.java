package examples.experiment4;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp4Mutator {
    private double prob;

    public Exp4Mutator(final double prob) {
        if (prob < 0 || prob > 1) {
            throw new IllegalArgumentException("Invalid probability");
        }
        this.prob = prob;
    }

    public double getProbability() {
        return prob;
    }

    public void setProbability(final double prob) {
        this.prob = prob;
    }
}
