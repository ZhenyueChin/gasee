package examples.projectHotspots;

import com.sun.istack.internal.NotNull;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */

public class ProjectHotspotsFitnessFunction implements FitnessFunction<SimpleMaterial> {
    @Override
    public double evaluate(@NotNull SimpleMaterial phenotype) {
        return 0;
    }

    @Override
    public void update() {
        
    }
}
