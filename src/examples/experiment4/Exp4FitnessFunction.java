package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

/**
 * Created by Chinyuer on 18/03/2017.
 */
public class Exp4FitnessFunction implements FitnessFunction<SimpleMaterial> {



    @Override
    public double evaluate(@NotNull SimpleMaterial phenotype) {
        return 0;
    }

    @Override
    public void update() {

    }
}
