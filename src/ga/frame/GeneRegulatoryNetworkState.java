package ga.frame;

import com.sun.istack.internal.NotNull;
import ga.collections.Population;
import ga.components.chromosomes.Chromosome;
import ga.operations.fitnessFunctions.FitnessFunction;
import ga.operations.mutators.Mutator;
import ga.operations.reproducers.Reproducer;
import ga.operations.selectionOperators.selectors.Selector;

/**
 * Created by Zhenyue Qin on 7/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkState<C extends Chromosome> extends SimpleState<C> {

  public GeneRegulatoryNetworkState(@NotNull Population<C> population,
                                    @NotNull FitnessFunction fitnessFunction,
                                    @NotNull Mutator mutator,
                                    @NotNull Reproducer<C> reproducer,
                                    @NotNull Selector<C> selector,
                                    int numOfMates,
                                    double reproductionRate) {
    super(population, fitnessFunction, mutator, reproducer, selector, numOfMates, reproductionRate);
  }

  public GeneRegulatoryNetworkState(@NotNull final Population<C> population,
                                    @NotNull final Mutator mutator,
                                    @NotNull final Reproducer<C> reproducer,
                                    @NotNull final Selector<C> selector,
                                    final int numOfMates,
                                    final double reproductionRate) {
    super(population, mutator, reproducer, selector, numOfMates, reproductionRate);
  }

  public void evaluate(final boolean recomputePhenotype) {
    population.evaluate();
  }

  @Override
  public void reproduce() {

  }

  @Override
  public void mutate() {

  }
}
