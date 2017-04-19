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
 * Created by Zhenyue Qin on 6/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkPriorOperator implements PriorOperator<SimpleHaploid> {

  private int numOfElites;
  private Selector selector;
  private Mutator<SimpleHaploid> mutator = null;

  public GeneRegulatoryNetworkPriorOperator(final int numOfElites, Selector selector) {
    if (numOfElites < 1)
      throw new IllegalArgumentException("Number of elites must be a positive integer.");
    this.numOfElites = numOfElites;
    this.selector = selector;
  }

  @Override
  public void preOperate(@NotNull Population<SimpleHaploid> population) {
    population.setMode(PopulationMode.PRIOR);
    List<Individual<SimpleHaploid>> individuals = population.getIndividualsView();
    for (int i = 0; i < individuals.size(); i++) {
//      population.addCandidate(individuals.get(i));
    }
  }
}
