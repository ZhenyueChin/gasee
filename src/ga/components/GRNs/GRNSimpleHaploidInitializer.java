//package ga.components.GRNs;
//
//import ga.collections.Individual;
//import ga.collections.Population;
//import ga.components.chromosomes.SimpleHaploid;
//import ga.components.materials.SimpleMaterial;
//import ga.operations.initializers.Initializer;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Zhenyue Qin on 26/03/2017.
// * The Australian National University.
// */
//public class GRNSimpleHaploidInitializer implements Initializer<SimpleHaploid> {
//
//  private int nodeSize;
//  private int interactionSize;
//  private int size;
//
//  public GRNSimpleHaploidInitializer(final int nodeSize, final int interactionSize, final int size) {
//    this.nodeSize = nodeSize;
//    this.interactionSize = interactionSize;
//    this.size = size;
//  }
//
//  private void filter(final int value) {
//    if (value < 1)
//      throw new IllegalArgumentException("Invalid value: Input must not be less than 1.");
//  }
//
//  @Override
//  public int getSize() {
//    return this.size;
//  }
//
//  @Override
//  public void setSize(int size) {
//    filter(size);
//    this.size = size;
//  }
//
//  @Override
//  public Population<SimpleHaploid> initialize() {
//    Population<SimpleHaploid> population = new Population<>(size);
//    for (int i = 0; i < size; i++) {
//      population.addCandidate(generateIndividual());
//    }
//    population.nextGeneration();
//    return population;
//  }
//
//  private Individual<SimpleHaploid> generateIndividual() {
//    List<GeneRegulatoryNetwork> genes = new ArrayList<>(nodeSize);
//    for (int i = 0; i < nodeSize; i++) {
//      GeneRegulatoryNetworkFactory evolutionGeneFactory = new GeneRegulatoryNetworkFactory(nodeSize, interactionSize);
//      GeneRegulatoryNetwork evolutionGene = evolutionGeneFactory.generateGene();
//      genes.add(evolutionGene);
//    }
//    return new Individual<>(new SimpleHaploid(new SimpleMaterial(genes)));
//  }
//}
