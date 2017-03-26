package ga.components.GRNs;

import com.sun.istack.internal.NotNull;
import ga.components.genes.Gene;

/**
 * Created by Chinyuer on 26/03/2017.
 */
public class EvolutionGene extends Gene<DataGene>{

    public EvolutionGene(@NotNull DataGene value) {
        super(value);
    }

    @Override
    public Gene<DataGene> copy() {
        return null;
    }

    @Override
    public void setValue(@NotNull DataGene value) {

    }
}
