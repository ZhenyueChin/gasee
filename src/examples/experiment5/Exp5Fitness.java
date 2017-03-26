package examples.experiment5;

import com.sun.istack.internal.NotNull;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp5Fitness implements FitnessFunction<SimpleMaterial>{

    @Override
    public double evaluate(@NotNull SimpleMaterial phenotype) {
        int oneCount = 0;
        for (int i=0; i<phenotype.getSize(); i++) {
            if ((Integer) (phenotype.getGene(i).getValue()) == 1) {
                oneCount += 1;
            }
        }
        return oneCount;
    }

    @Override
    public void update() {

    }
}
