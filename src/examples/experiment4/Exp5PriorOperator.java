package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.collections.Population;
import ga.components.chromosomes.SimpleHaploid;
import ga.operations.priorOperators.PriorOperator;
import ga.operations.selectionOperators.selectors.Selector;

import java.util.List;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp5PriorOperator implements PriorOperator<SimpleHaploid> {

    int numOfElites;
    Selector selector;

    public Exp5PriorOperator(int numOfElites, Selector selector) {
        this.numOfElites = numOfElites;
        this.selector = selector;
    }

    @Override
    public void preOperate(@NotNull Population<SimpleHaploid> population) {
        List<Individual<SimpleHaploid>> individuals = population.getIndividualsView();
        for (int i=0; i<this.numOfElites; i++)  {
            population.addCandidate(individuals.get(i));
        }
    }
}
