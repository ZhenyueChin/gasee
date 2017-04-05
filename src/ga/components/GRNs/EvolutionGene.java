package ga.components.GRNs;

import com.sun.istack.internal.NotNull;
import ga.components.genes.BinaryGene;
import ga.components.genes.Gene;

import java.util.ArrayList;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class EvolutionGene extends Gene<Integer[][]> {
    

    public EvolutionGene(int[] target, int maxCycle, @NotNull Integer[][] connections) {
        super(connections);
    }

    @Override
    public Gene<Integer[][]> copy() {
        return null;
    }

    @Override
    public void setValue(@NotNull Integer[][] value) {

    }
}
