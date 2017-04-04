package examples.projectHotspots;

import com.sun.istack.internal.NotNull;
import ga.collections.Individual;
import ga.components.chromosomes.SimpleHaploid;
import ga.operations.mutators.Mutator;

import java.util.List;

/**
 * Created by Zhenyue Qin on 29/03/2017.
 * The Australian National University.
 */
public class ProjectHotspotsMutator implements Mutator<SimpleHaploid> {

  private double prob;

  @Override
  public void mutate(@NotNull List<Individual<SimpleHaploid>> individuals) {
      for (Individual<SimpleHaploid> h : individuals) {

      }
  }
}
