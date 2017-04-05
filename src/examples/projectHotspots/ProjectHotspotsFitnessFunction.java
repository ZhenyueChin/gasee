//package examples.projectHotspots;
//
//import com.sun.istack.internal.NotNull;
//import ga.components.GRNs.EvolutionGene;
//import ga.components.materials.SimpleMaterial;
//import ga.operations.fitnessFunctions.FitnessFunction;
//
///**
// * Created by Zhenyue Qin on 26/03/2017.
// * The Australian National University.
// */
//
//public class ProjectHotspotsFitnessFunction implements FitnessFunction<SimpleMaterial> {
//    private final DataGene<Integer>[] target;
//
//    public ProjectHotspotsFitnessFunction(IntegerDataGene[] target) {
//        this.target = target;
//    }
//
//    @Override
//    public double evaluate(@NotNull SimpleMaterial phenotype) {
//        int fitness = 0;
//        EvolutionGene evolutionGene = (EvolutionGene) phenotype.getGene(0);
//        for (int i=0; i<target.length; i++) {
//            if (evolutionGene.getStates()[i].getValue() == this.target[i].getValue()) {
//                fitness += 1;
//            }
//        }
//        return fitness;
//    }
//
//    @Override
//    public void update() {
//
//    }
//}
