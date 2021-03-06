package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.components.chromosomes.SimpleHaploid;
import ga.components.genes.Gene;
import ga.components.materials.SimpleMaterial;
import ga.operations.mutators.Mutator;

import java.util.List;

/**
 * Created by Zhenyue Qin on 7/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkMutator implements Mutator<SimpleHaploid> {

  private double prob;

  public GeneRegulatoryNetworkMutator(final double prob) {
    if (prob < 0 || prob > 1)
      throw new IllegalArgumentException("Value out of bound");
    this.prob = prob;
  }

  public double getProbability() {
    return prob;
  }

  public void setProbability(final double prob) {
    this.prob = prob;
  }

  @Override
  public void mutate(@NotNull List<Individual<SimpleHaploid>> individuals) {
    for (Individual<SimpleHaploid> h : individuals) {
//      because totally there is only one material in this individual
      Individual<SimpleHaploid> tempH = h.copy();
      GeneRegulatoryNetwork dna = (GeneRegulatoryNetwork) tempH.getChromosome().getMaterialsView().get(0);
      dna.perturb(this.prob);
      if (h.getFitness() < tempH.getFitness()) {
        h = tempH.copy();
      }
    }
  }

  private boolean toFlip() {
    return prob < Math.random();
  }

}
