package examples.experiment4;

import ga.components.genes.DataGene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenyue Qin on 6/04/2017.
 * The Australian National University.
 */
public class GeneRegulatoryNetworkMain {
  private static final int[] target = {-1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
  private static final int size = 200;
  private static final int maxCycle = 20;
  private static final int edgeSize = 20;

  public static void main(String[] args) {
    GeneRegulatoryNetworkFactory grnFactory = new GeneRegulatoryNetworkFactory(target, maxCycle, edgeSize);
    GeneRegulatoryNetwork grn = grnFactory.generateGeneRegulatoryNetwork();
    grn.evaluate();
    System.out.println(grn.getFitness());
  }
}
