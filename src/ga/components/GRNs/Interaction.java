package ga.components.GRNs;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class Interaction {
    private DataGene a;
    private DataGene b;
    private boolean activating;

    public Interaction(DataGene a, DataGene b, boolean activating) {
        this.a = a;
        this.b = b;
        this.activating = activating;
    }

    public boolean isEqual(Interaction anInteraction) {
        return ((this.a == anInteraction.a && this.b == anInteraction.b) ||
                (this.a == anInteraction.b && this.b == anInteraction.a));
    }

    public boolean isActivating() {
        return this.activating;
    }
}
