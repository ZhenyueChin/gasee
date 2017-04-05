//package examples.projectHotspots;
//
//import ga.components.GRNs.EvolutionGene;
//import ga.components.GRNs.EvolutionGeneFactory;
//import ga.components.GRNs.GRNSimpleHaploidInitializer;
//import ga.components.chromosomes.SimpleHaploid;
//import ga.operations.fitnessFunctions.FitnessFunction;
//import ga.operations.initializers.Initializer;
//
///**
// * Created by Zhenyue Qin on 26/03/2017.
// * The Australian National University.
// */
//public class ProjectHotspotsMain {
//    private static final int nodeSize = 10;
//    private static final int interactionSize = 20;
//    private static IntegerDataGene[] target;
//    private static final int size = 200;
//    private static final int maxGen = 5;
//  private static final int numElites = 20;
//  private static final double mutationRate = 0.05;
//  private static final double crossoverRate = .8;
//  private static final double epsilon = .5;
//  private static final double maxFit = 32;
//  private static final String outfile = "ProjectHotspots.out";
//
//      public static void main(String[] args) {
//          EvolutionGeneFactory evolutionGeneFactory = new EvolutionGeneFactory(nodeSize, interactionSize);
//          EvolutionGene evolutionGene = evolutionGeneFactory.generateGene();
//          target = evolutionGeneFactory.getTarget();
//          FitnessFunction fitnessFunction = new ProjectHotspotsFitnessFunction(target);
//          Initializer<SimpleHaploid> initializer = new GRNSimpleHaploidInitializer(nodeSize, interactionSize, size);
//
//      }
//}
