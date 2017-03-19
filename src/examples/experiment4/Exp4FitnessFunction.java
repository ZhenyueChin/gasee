package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.materials.SimpleMaterial;
import ga.operations.fitnessFunctions.FitnessFunction;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Chinyuer on 18/03/2017.
 */
public class Exp4FitnessFunction implements FitnessFunction<SimpleMaterial> {

    private int target;
    private final String targetBitString;

    public Exp4FitnessFunction() {
        this.target = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.targetBitString = Integer.toBinaryString(target);
    }

    public Exp4FitnessFunction(final int target) {
        this.target = target;
        this.targetBitString = Integer.toBinaryString(target);
    }

    public int getTarget() {
        return target;
    }

    public String getTargetBitString() {
        return targetBitString;
    }

    @Override
//    I don't understand this method
    public double evaluate(@NotNull final SimpleMaterial phenotype) {
        int num = 0;
        for (int i=0; i<phenotype.getSize(); i++) {
            num = (num << 1) | (Integer) phenotype.getGene(i).getValue();
        }
        final int comparison = ~(num ^ target);
        return Integer.bitCount(comparison);
    }

    @Override
    public void update() {

    }
}
