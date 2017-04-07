package examples.experiment4;

import ga.collections.Population;
import ga.components.GRNs.GRNSimpleHaploidInitializer;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.genes.DataGene;
import ga.operations.initializers.Initializer;
import ga.operations.postOperators.PostOperator;
import ga.operations.postOperators.SimpleFillingOperatorForNormalizable;
import ga.operations.priorOperators.PriorOperator;
import ga.operations.selectionOperators.selectionSchemes.ProportionalScheme;
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
  private static final int size = 5;
  private static final int maxCycle = 20;
  private static final int edgeSize = 20;
  private static final int numElites = 20;

  public static void main(String[] args) {
    GeneRegulatoryNetworkFactory grnFactory = new GeneRegulatoryNetworkFactory(target, maxCycle, edgeSize);
    GeneRegulatoryNetwork grn = grnFactory.generateGeneRegulatoryNetwork();
    grn.evaluate();
    System.out.println(grn.getFitness());

    Initializer<SimpleHaploid> initializer = new GRNSimpleHaploidInitializer(target.clone(), maxCycle, edgeSize, size);
    Population<SimpleHaploid> population = initializer.initialize();
    System.out.println(population.getIndividualsView());

    Selector<SimpleHaploid> selector = new SimpleProportionalSelector<>();
    PriorOperator<SimpleHaploid> priorOperator = new GeneRegulatoryNetworkPriorOperator(numElites, selector);
    PostOperator<SimpleHaploid> postOperator = new SimpleFillingOperatorForNormalizable<>(new ProportionalScheme());


  }
}
