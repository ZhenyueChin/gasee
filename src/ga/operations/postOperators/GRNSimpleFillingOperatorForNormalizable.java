package ga.operations.postOperators;

import com.sun.istack.internal.NotNull;
import ga.collections.Population;
import ga.components.chromosomes.Chromosome;

import java.util.Set;

/**
 * Created by Zhenyue Qin on 9/04/2017.
 * The Australian National University.
 */
public class GRNSimpleFillingOperatorForNormalizable<T extends Chromosome> implements PostOperator<T> {

  @Override
  public void postOperate(@NotNull Population<T> population) {
    Set<Integer> survivedIndices = population.getSurvivedIndicesView();

  }
}
