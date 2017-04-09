package ga.operations.selectionOperators.selectors;

import ga.collections.Individual;
import ga.components.chromosomes.Chromosome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Created by Zhenyue Qin on 9/04/2017.
 * The Australian National University.
 */
public class GRNSimpleProportionalSelector<C extends Chromosome> implements Selector<C> {

  protected List<Double> fitnessValues;
  protected List<Individual<C>> individuals;

  @Override
  public List<C> select(int numOfMates) {
    List<C> parents = new ArrayList<>(numOfMates);
    Set<Integer> set = new HashSet<>(numOfMates);

    for (int i=0; i<fitnessValues.size(); i++) {
      set.add(i);
      if (set.size() >= numOfMates) {
        break;
      }
    }

    for (int selectIndex : set) {
      parents.add(individuals.get(selectIndex).getChromosome());
    }
    return parents;
  }

  @Override
  public void setSelectionData(List<Individual<C>> individuals) {
    this.individuals = individuals;
    final int size = individuals.size();
    fitnessValues = new ArrayList<>();
    double sum = 0;
    for (Individual<C> individual : individuals) sum += individual.getFitness();
    for (int i = 0; i < size; i++) {
      if (sum != 0) {
        fitnessValues.add(individuals.get(i).getFitness() / sum);
      } else {
        fitnessValues.add(0.0);
      }
    }
  }
}
