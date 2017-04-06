package ga.components.GRNs;

import examples.experiment4.GeneRegulatoryNetwork;
import examples.experiment4.GeneRegulatoryNetworkFactory;
import ga.collections.Individual;
import ga.collections.Population;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.materials.SimpleMaterial;
import ga.operations.initializers.Initializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class GRNSimpleHaploidInitializer implements Initializer<SimpleHaploid> {

  private int[] target;
  private int maxCycle;
  private int edgeSize;
  GeneRegulatoryNetworkFactory grnFactory;

  private int size;

  public GRNSimpleHaploidInitializer(final int[] target, final int maxCycle, final int edgeSize, final int size) {
    this.target = target.clone();
    this.maxCycle = maxCycle;
    this.edgeSize = edgeSize;
    this.grnFactory = new GeneRegulatoryNetworkFactory(this.target.clone(), this.maxCycle, this.edgeSize);
    this.size = size;
  }

  private void filter(final int value) {
    if (value < 1)
      throw new IllegalArgumentException("Invalid value: Input must not be less than 1.");
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public void setSize(int size) {
    filter(size);
    this.size = size;
  }

  @Override
  public Population<SimpleHaploid> initialize() {
    Population<SimpleHaploid> population = new Population<>(size);
    for (int i = 0; i < size; i++) {
      population.addCandidate(generateIndividual());
    }
    population.nextGeneration();
    return population;
  }

  private Individual<SimpleHaploid> generateIndividual() {
    GeneRegulatoryNetwork grn = grnFactory.generateGeneRegulatoryNetwork();
    return new Individual<>(new SimpleHaploid(grn.copy()));
  }
}
