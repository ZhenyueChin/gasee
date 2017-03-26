package ga.components.GRNs;

import com.sun.istack.internal.NotNull;
import ga.components.genes.Gene;

import java.util.ArrayList;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class EvolutionGene extends Gene<IntegerDataGene[]>{

    private ArrayList<Interaction> interactions = new ArrayList<Interaction>();

    public EvolutionGene(@NotNull IntegerDataGene[] integerDataGenes, ArrayList<Interaction> interactions) {
        super(integerDataGenes);
        this.interactions = interactions;
    }

    @Override
    public Gene<IntegerDataGene[]> copy() {
        return null;
    }

    @Override
    public void setValue(@NotNull IntegerDataGene[] value) {

    }
}
