package ga.components.GRNs;

import com.sun.istack.internal.NotNull;
import ga.components.genes.Gene;

import java.util.ArrayList;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class EvolutionGene extends Gene<IntegerDataGene[][]>{

    private IntegerDataGene[] target;
    private IntegerDataGene[] states;
    private int nodeSize;

    public EvolutionGene(@NotNull IntegerDataGene[][] interactions, IntegerDataGene[] target, IntegerDataGene[] states) {
        super(interactions);
        this.target = target;
        this.states = states;
        this.nodeSize = states.length;
    }

    @Override
    public Gene<IntegerDataGene[][]> copy() {
        return new EvolutionGene(this.value, this.target, this.states);
    }

    public void setValue(@NotNull IntegerDataGene[][] interactions) {
        this.value = interactions;
    }

    public int getDataGeneState(int i) {
        int sum = 0;
        for (int j=1; j<=this.nodeSize; j++) {
            sum += this.value[i][j].getValue() * this.states[j].getValue();
        }
        if (sum > 0) {
            return Variables.active;
        } else {
            return Variables.inactive;
        }
    }

    public IntegerDataGene[] getStates() {
        return this.states;
    }

    public void reEvaluateStates() {
        IntegerDataGene[] statesCopy = this.states.clone();
        for (int i=1; i<=this.nodeSize; i++) {
            statesCopy[i].setValue(this.getDataGeneState(i));
        }
        this.states = statesCopy;
    }

    public void toMutate() {
        /*
        Mutate the specified gene at index i according to the rule specified in Soto's paper
         */

    }
}
