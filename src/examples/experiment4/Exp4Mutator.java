package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.genes.Gene;
import ga.components.materials.SimpleMaterial;
import ga.operations.mutators.Mutator;

import java.util.List;

/**
 * Created by Chinyuer on 19/03/2017.
 */

public class Exp4Mutator implements Mutator<SimpleHaploid>{
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

    private boolean toFlit() {
        return prob < Math.random();
    }

    @Override
    public void mutate(@NotNull List<Individual<SimpleHaploid>> individuals) {
        for (Individual<SimpleHaploid> h : individuals) {
            SimpleMaterial dna = h.getChromosome().getMaterialsView().get(0);
            for (int i=0; i<dna.getSize(); i++) {
                if (!toFlit()) continue;
                Gene<Integer> gene =  (Gene<Integer>) dna.getGene(i);
                gene.setValue(gene.getValue() ^ 1);
            }
        }
    }
}
