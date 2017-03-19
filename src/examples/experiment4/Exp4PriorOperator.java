package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.collections.Population;
import ga.collections.PopulationMode;
import ga.components.chromosomes.SimpleHaploid;
import ga.operations.mutators.Mutator;
import ga.operations.priorOperators.PriorOperator;
import ga.operations.selectionOperators.selectors.Selector;

import java.util.List;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp4PriorOperator implements PriorOperator<SimpleHaploid>{

    private int numOfElites;
    private Selector selector;

    public Exp4PriorOperator(final int numOfElites, Selector selector) {
        if (numOfElites < 1) {
            throw new IllegalArgumentException("Number of elites must be a positive integer. ");
        }
        this.numOfElites = numOfElites;
        this.selector = selector;
    }

    @Override
    public void preOperate(@NotNull Population<SimpleHaploid> population) {
        population.setMode(PopulationMode.PRIOR);
        List<Individual<SimpleHaploid>> individuals = population.getIndividualsView();
        for (int i=0; i<20; i++) {
            population.addCandidate(individuals.get(i));
        }
    }

    private void mutate(@NotNull final List<Individual<SimpleHaploid>> mutant,
                        @NotNull final Mutator<SimpleHaploid> mutator) {
        mutator.mutate(mutant);
    }


}
