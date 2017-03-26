package examples.experiment5;

import ga.collections.Population;
import ga.components.chromosomes.SimpleDiploid;
import ga.operations.initializers.Initializer;
import genderGAWithHotspots.collections.GenderPopulation;
import genderGAWithHotspots.components.chromosomes.SimpleGenderDiploid;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp5Initializer implements Initializer<SimpleGenderDiploid<Integer>> {

    private int size;

    public Exp5Initializer(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Population<SimpleGenderDiploid<Integer>> initialize() {
        return null;
    }


}
