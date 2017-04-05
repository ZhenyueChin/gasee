package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.materials.SimpleMaterial;
import ga.operations.reproducers.Reproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp5Reproducer implements Reproducer<SimpleHaploid>{

    public Exp5Reproducer() {

    }

    @Override
    public List<SimpleHaploid> reproduce(@NotNull List<SimpleHaploid> mates) {
        List<SimpleHaploid> children = new ArrayList<>(2);

        SimpleHaploid child1 = mates.get(0).copy();
        SimpleHaploid child2 = mates.get(0).copy();

        SimpleMaterial dna1 = child1.getMaterialsView().get(0);
        SimpleMaterial dna2 = child2.getMaterialsView().get(0);

        final int length = child1.getLength();
        final int crossIndex = ThreadLocalRandom.current().nextInt(1, length-1);

        for (int i=crossIndex; i<length; i++) {
            final int i1 = (int) dna1.getGene(i).getValue();
            final int i2 = (int) dna1.getGene(i).getValue();
            dna1.getGene(i).setValue(i2);
            dna2.getGene(i).setValue(i1);
        }

        children.add(child1);
        children.add(child2);

        return children;
    }
}