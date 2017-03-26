package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp5FitnessFunction implements FitnessFunction<SimpleMaterial>{

    private final int target;
    private final String binaryTarget;

    public Exp5FitnessFunction(final int target) {
        this.target = target;
        this.binaryTarget = Integer.toBinaryString(target);
    }

    @Override
    public double evaluate(@NotNull SimpleMaterial phenotype) {
        int fitValue = 0;
        for (int i=0; i<phenotype.getSize(); i++) {
            int aGene = (Integer) phenotype.getGene(i).getValue();
            int relatingTarget;
            try {
                relatingTarget = Character.getNumericValue(this.binaryTarget.charAt(i));
            } catch (Exception e) {
                relatingTarget = 0;
            }
            if (aGene == relatingTarget) {
                fitValue += 1;
            }
        }
        return fitValue;
    }

    public int getTarget() {
        return this.target;
    }

    public String getBinaryTarget() {
        return this.binaryTarget;
    }

    @Override
    public void update() {

    }
}
