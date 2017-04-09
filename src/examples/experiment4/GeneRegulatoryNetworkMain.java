package examples.experiment4;

import examples.experiment1.Exp1Reproducer;
import examples.experiment1.Exp1Statistics;
import ga.collections.Population;
import ga.collections.Statistics;
import ga.components.GRNs.GRNSimpleHaploidInitializer;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.genes.DataGene;
import ga.frame.Frame;
import ga.frame.SimpleFrame;
import ga.frame.SimpleState;
import ga.frame.State;
import ga.operations.fitnessFunctions.FitnessFunction;
import ga.operations.initializers.Initializer;
import ga.operations.mutators.Mutator;
import ga.operations.postOperators.PostOperator;
import ga.operations.postOperators.SimpleFillingOperatorForNormalizable;
import ga.operations.priorOperators.PriorOperator;
import ga.operations.reproducers.Reproducer;
import ga.operations.selectionOperators.selectionSchemes.ProportionalScheme;
import ga.operations.selectionOperators.selectors.GRNSimpleProportionalSelector;
import ga.operations.selectionOperators.selectors.Selector;
import ga.operations.selectionOperators.selectors.SimpleProportionalSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenyue Qin on 6/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkMain {
  private static final int[] target = {-1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
  private static final int size = 200;
  private static final int maxCycle = 30;
  private static final int edgeSize = 20;
  private static final int numElites = 5;
  private static final double mutationRate = 0.05;
  private static final double crossoverRate = .8;

  private static final int maxGen = 1000;
  private static final double maxFit = 10;
  private static final double epsilon = .5;
  private static final String outfile = "Exp4.out";

  public static void main(String[] args) {

    GeneRegulatoryNetworkFactory grnFactory = new GeneRegulatoryNetworkFactory(target, maxCycle, edgeSize);

    FitnessFunction fitnessFunction = new GeneRegulatoryNetworkFitnessFunction(
      grnFactory.convertIntArrayToDataGenes(target),
      maxCycle);

    Initializer<SimpleHaploid> initializer = new GRNSimpleHaploidInitializer(target.clone(), maxCycle, edgeSize, size);
    Population<SimpleHaploid> population = initializer.initialize();

    Mutator mutator = new GeneRegulatoryNetworkMutator(mutationRate);

    Selector<SimpleHaploid> selector = new SimpleProportionalSelector<>();
    // PriorOperator is optional.
    PriorOperator<SimpleHaploid> priorOperator = new GeneRegulatoryNetworkPriorOperator(numElites, selector);
    // PostOperator is required to fill up the vacancy.
    PostOperator<SimpleHaploid> postOperator = new SimpleFillingOperatorForNormalizable<>(new ProportionalScheme());

    // Statistics for keeping track the performance in generations
    Statistics<SimpleHaploid> statistics = new Exp1Statistics();
    // Reproducer for reproduction
    Reproducer<SimpleHaploid> reproducer = new Exp1Reproducer();

    State<SimpleHaploid> state = new SimpleState<>(population, fitnessFunction, mutator, reproducer, selector, 2, crossoverRate);
    state.record(statistics);
    Frame<SimpleHaploid> frame = new SimpleFrame<>(state,postOperator,statistics);
    frame.setPriorOperator(priorOperator);
    statistics.print(0);

    for (int i = 1; i < maxGen; i++) {
      System.out.println("Current Generation: " + i);
      frame.evolve();
      if (statistics.getOptimum(i) > maxFit - epsilon)
        break;
    }
    statistics.save(outfile);

  }
}
