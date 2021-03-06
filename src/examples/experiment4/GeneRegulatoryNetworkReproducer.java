package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.genes.Gene;
import ga.components.materials.SimpleMaterial;
import ga.operations.reproducers.Reproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Zhenyue Qin on 7/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkReproducer implements Reproducer<SimpleHaploid> {
  @Override
  public List<SimpleHaploid> reproduce(@NotNull List<SimpleHaploid> mates) {
    List<SimpleHaploid> children = new ArrayList<>(2);

    SimpleHaploid child1 = mates.get(0).copy();
    SimpleHaploid child2 = mates.get(1).copy();

    SimpleMaterial dna1 = child1.getMaterialsView().get(0);
    SimpleMaterial dna2 = child2.getMaterialsView().get(0);

    final int length = child1.getLength();
    final int crossIndex = ThreadLocalRandom.current().nextInt(0,length-1);

    return null;
  }
}
